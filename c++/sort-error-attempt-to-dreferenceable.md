### Why does std::sort throw a segmentation fault on this code?
> https://stackoverflow.com/questions/6978201/why-does-stdsort-throw-a-segmentation-fault-on-this-code

위 문제를 경험하여 기록을 남긴다.

`std::sort()`는 마지막 optional argument로 compare function 혹은  compare function을 가지는 객체를 받는다.
이 compare function 이 제대로 구현되지 않은 경우, 아래의 메시지를 출력하고 segmentation fault가 발생하며 프로세스가 죽어 버린다.

>/usr/local/lib/gcc/i686-pc-linux-gnu/4.1.2/../../../../include/c++/4.1.2/debug/safe_iterator.h:240:
>    error: attempt to decrement a dereferenceable (start-of-sequence)     
>    iterator.

아래 코드는 이 문제를 야기 시킨 문제의 코드이다.

```
static bool compare(shared_ptr<Event> x, shared_ptr<Event> y)
{
    boolean result = (x->getType() < y->getType());
    if (result == false) {
        result = (x->getTime() < y->getTime());
    }
    return result;
}
```

위 compare 함수 구현은 일단 종류 별로 정렬한 후에 event 순서대로 정렬하려고 작성한 코드이다.
하지만 아래와 같은 상황에서 논리적인 오류가 발생한다.

x와 y의 필드들의 값이 각각 아래와 같을 때 
```
Event x { .type=1, .time=0 };
Event y { .type=0, .time=1 };
```

아래와 같이 `compare()` 가 항상 `true`를 반환한다.
이렇게 되면 `std::sort()` 는 둘 중에 어떤 값이 더 큰 값인지 판단할 수 없게 된다.

```
assert(!compare(x, y)); // 실패! 
assert(compare(y, x));
```

위의 `compare()` 구현을 아래와 같이 수정하면 문제는 해결 된다.

```
static bool compare(shared_ptr<Event> x, shared_ptr<Event> y)
{
    if (x->getType() == y->getType()) {
        return x->getTime() < y->getTime();
    }
    return x->getType() < y->getType();
}
```

