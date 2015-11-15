AnnotationConfigContextLoader를 사용하여 Autowired되는 bean을 spying 하기
------

### 모든 테스트의 시나리오에 대해서 Stub을 만들기는 지쳐요
요즘 SpringJUnit4ClassRunner와 Mockito 조합으로 대부분의 단위테스트를 작성하고 있다.
Service 객체의 business logic을 검증할 때 로직이 단순하여 mock이나 stub 정도로
충분히 검증히 가능한 경우가 대부분이지만, 그렇지 않은 경우도 있기 마련이다.

특히 대상 클래스가 DAO가 아닌 다른 컴포넌트 객체를 사용하는 경우, 해당 컴포넌트를 mock이나
stub으로 교체하여 테스트하게 되면 테스트 자체가 지나치게 단순해지고 로직 자체를 충분히 검증하기
어려워지는 문제가 발생한다.

이런 경우에 spy()를 사용하여 실제 구현 클래스를 사용하고, 해당 클래스의 일부분만 mocking하는
식으로 사용한다.

### spy()는 좋은데 .. 스프링 환경에서 써먹기가 쉽지않아...
실제로 사용하는 객체를 데코레이션하는 spy()가 효과적일 수 있는데, 스프링 사용 시,
@Autowired 어노테이션을 사용하여 DI 받는 경우에 테스트 대상 인터페이스에 spying할 객체를
다시 얻어와서 다시 주입할 getter/setter가 존재하지 않는 경우 spying 하기가 불가능하다.

보통 @Autowired를 사용하는 경우 해당 멤버를 위한 별도의 getter/setter를 두지 않는다.
테스트를 위해서 getter/setter 를 만들고 싶진 않다.

처음 시도한 것은 인터페이스에는 getter/setter를 두지 않고 테스트 용도로
실제 구현 클래스에만 해당 필드에 대한 getter/setter method를 두는 것이었는데,
@Transactional등의 annotation을 사용하는 경우 해당 필드가 weaving되어 실제 내가
작성한 클래스가 아닌 Proxy객체로 변경되어 있는 문제가 발생하였다.

### 내가 찾은 방법은 ... 
이 문제를 해결하는 방법으로 찾은 것이 AnnotationConfigContextLoader와 @Configuration + @Primary annotation의 조합이다.

나는 테스트 작성 시 아래와 같이 실제 개발 환경에서 사용하는 XML 기반의 Context configuration을 테스트 시에도 로딩해서 사용하고 있다.

```java

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-*.xml")
public class UserServiceTests {
    @Autowired
    private UserService userService;
...

```

저렇게 @Autowired 어노테이션으로 DI 받는 빈을 spying 된 개체로 받기 위해서는
결국 실제로 전달되는 빈을 spy()로 데코레이션 해서 전달해야 한다.

이 처리를 위해 해당 테스트 만을 위한 별도의 Context configuration을 특히 테스트 마다
매번 새로운 XML파일을 만들어 내야하는 번거로움을 피하기 위해서 소스코드 기반의 Configuration을
사용하는 방법을 찾아야 했다.

결론적으로 아래와 같은 방법으로 목적을 달성할 수 있었다.

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class UserServiceTests {

	@Configuration
    @ImportResource("classpath*:spring-*.xml")
    static class ContextConfiguration {
    	@Bean
    	@Primary
        public UserService userService(UserService userService) {
        	return spy(userService);
        }
    }
    
    @Autowired
    private UserService userService;
    
    @After
    public void tearDown() {
    	reset(userService);
    }
    
    @Test
    public void getUserInfoTest() {
        long userId = 1L;
		
        when(userService.getUserInfo(userId))
                .thenReturn(null)
                .thenCallRealMethod();
        ....

```

@ContextConfiguration을 AnnotationConfigContextLoader로 지정하면 @Configuration 어노테이션이 붙어 있는 class를 찾는다. 찾는 순서와 범위가 어떻게 되는지는 아직 모르겠지만, 위와 같이
static class로 만들어 두면 높은 우선 순위를 갖는 것 으로 추정 된다 :)

이제 spying 을 처리할 ContextConfiguration의 구현을 살펴 보도록 하겠다.

* @ImportResource를 사용하여 원래 사용하던 XML 기반의 configuration을 가져온다.
이렇게 하면 원래 적용되는 모든 설정을 그대로 사용할 수 있다.

* Bean에 대한 설정이므로 @Bean을 붙였다.

* @Primary를 붙이면 같은 컨텍스트 설정 내에 같은 타입을 갖는 빈이 여러개 존재하는 경우 @Autowired 로 빈이 주입될 때 어떤 빈을 주입할 지 결정해야 할 때 가장 높은 우선 순위를 갖게 된다.

* 포인트는 빈 설정 메서드의 파라미터 인 것 같다. 컨텍스트에 이미 동일한 Type을 갖는 빈이 존재하는 경우 파라미터로 넘겨 받을 수 있다. 이렇게 넘겨 받은 빈을 spy()로 감싸는 것이 핵심!

Finally! 어마무시한 스텁 작성으로 부터 어느 정도 자유로워 질 수 있게 되었다.




