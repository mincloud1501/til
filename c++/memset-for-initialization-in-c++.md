C++ instance 초기화를 위해 `memset()`을 써도 될까?
==================================================

결론 부터 말하면 안된다. 아래는 관련 Stack Overflow case들에 대한 link이다.
> https://stackoverflow.com/questions/1998752/memset-or-value-initialization-to-zero-out-a-struct
> https://stackoverflow.com/questions/2481654/memset-for-initialization-in-c


C++에 어울리지 않는 C-style을 억지로 가져오는 것이며, non-POD(Plain Old Object[^1])에 사용할 경우 crash가 발생할 수 있다.

아래와 같이 멤버로 std::string 을 갖는 인스턴스를 memset을 사용하여 초기화 할 경우 crash가 발생한다.

```
struct MyType {
    int number;
    std::string text;
}

...

MyType myType;
memset(&myType, 0, sizeof(myType));
```

그렇다면 옳바른 초기화 방법은?
==============================

non-POD type인 경우엔 생성자에서 멤버들을 잘 초기화 해주는 것이 당연하지만, POD type인 경우엔 필드들을 수동으로 초기화 해줘야 한다.
위 stack overflow 페이지를 보면 `{}`를 멤버 초기화 시에 사용할 것을 권하고 있다.

아래의 테스트 코드를 확인 해 보자

```
#include <iostream>                                                                                                                                                                      
                                                                                                                                                                                         
using namespace std;                                                                                                                                                                     

int main(int argc, char *argv[])                                                                                                                                                         
{                                                                                                                                                                                        
    struct MyType {                                                                                                                                                                      
        int num;                                                                                                                                                                         
        string text;                                                                                                                                                                     
    };                                                                                                                                                                                   

    MyType myType1;                                                                                                                                                                      
    cout << "num:" << myType1.num << " text:" << myType1.text << endl;                                                                                                                   

    MyType myType2 = {};                                                                                                                                                                 
    cout << "num:" << myType2.num << " text:" << myType2.text << endl;                                                                                                                   

    return 0;                                                                                                                                                                            
}

```

위 코드를 실행 해보면, `{}`를 사용해서 초기화 해줬을 때와 안했을 때 아래와 같이 결과가 다른 것을 확인할 수 있다.

```
jhkang@jhkang-laptop:g4$ g++ test.cpp
jhkang@jhkang-laptop:g4$ ./a.out 
num:6299112 text:
num:0 text:

```

string인 member의 경우 차이가 없는데, string의 경우엔  생성자에서 잘 초기화가 되었기 때문일거라고 생각한다.
오늘 다른 사람이 짠 코드에서 crash가 발생하여 이 문제에 대해 파보게 되었다.
이런 것도 모르고 있었다니.. 난 역시 C++ 뉴비였어.. 

[^1]: https://stackoverflow.com/questions/146452/what-are-pod-types-in-c

