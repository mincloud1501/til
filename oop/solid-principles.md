객체 지향 설계 5원칙 - SOLID
-----

#### 1. SRP - Single Responsibility Priciple

> There should never be more than one reason for a class to change

'클래스가 하나 이상의 이유으로 인해 변경 되어서는 안된다' 라는 말은 아직도 뭔가 '아하!'스럽게
이해 되지 않는다.

> Separation of concerns

나는 그냥 이 원칙은 단순하게 생각한다. 바로 '관심사의 분리' 이다.
너무 많은 것을 신경써야 하는 클래스 디자인을 피하고, 되도록 하나의 역할 만 수행하도록 하는 것이다.

#### 2. OCP - Open Closed Princible

> You should be able to extend a classes behavior, without modifying it

변경에 대해서는 폐쇄되고, 확장에 대해서는 개방되어야 한다. 라고 어디에나 써있지만,
도대체 무슨 소리인가 싶다...

> 모듈 자체를 변경하지 않고도 그 모듈을 둘러싼 환경을 바꿀 수 있어야 한다

이 말이 좀 더 이해하고 적용하기 쉬운 것 같다.
자바의 경우 JDBC를 예로 들면 이해하기 쉽다. JDBC를 사용하는 구현은 변경할 필요 없이,
Oracle, MySQL, MS SQL등 JDBC를 구현한 jdbc connector를 사용하면, db를 교체할 수
있는 것을 생각하면 될 것 같다.

#### 3. LSP - Liskov Subtitution Priciple

> Functions that use pointers or references to base classes must be able to
> use objects of derived classes without knowing it.

이건 기반 클래스와 서브 클래스 간에 is kind of.. 의 원칙을 잘 지키라는 뜻으로 이해 했다.
기반 클래스에서 하위 클래스에 대해 알지 못하더라고, 메소드를 호출했을 때 문제 없이 동작해야 한다.


#### 4. ISP - Interface Segregation Principle

> Client should not be forced to depend upon interfaces that
> they do no use

클래스를 사용하는 클라이언트가, 사용하지 않는 인터페이스에 대해서 까지 종속적이어선 안된다.
즉, 클라이언트가 필요한 부분만 인터페이스하여 노출시키라는 것이다.

#### 5. DIP - Dependancy Inversion Principle

> A. High level modules should not depend upon low level modules.
>    Both should depend upon abstractions.
> B. Abstractions should not depend upon details.
>    Details should depend upon abstrations

스프링의 IoC, DI 컨셉이 여기에 해당한다.
