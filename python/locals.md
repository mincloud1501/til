built-in function locals()
---------------

local()는 built-in 함수로서, 로컬 변수들을  map으로 반환한다.
아래와 같이 사용할 수 있다.

```python

def any_function():
	name = "Jihoon"
	age = 34
	print("My name is %(name)s. I'm %(age)d years old." % locals())

```

출력 결과는 아래와 같다
> My name is Jihoon. I'm 34years old.


지역 변수들에 대해서만 동작하고, 클래스 멤버 변수들에 대해서는 동작하지 않는다.
아래 예제 코드와 그 실행 결과를 참고..

```python
def test_locals_in_function_scope():
    name = "Jihoon"
    age = 34
    print("My name is %(name)s I'm %(age)d years old" % locals())

def test_locals_with_parameters(name, age):
    print("My name is %(name)s I'm %(age)d years old" % locals())

class Person():
    def __init__(self, name, age):
        self.name = name
        self.age = age

    def __init__(self):
        self.name = None
        self.age = None

    def test_locals_in_method(self):
        print("My name is %(name)s I'm %(age)d years old" % locals())

    def test_locals_with_local_vars_in_method(self):
        name = "Eunwoo"
        age = 2
        print("My name is %(name)s I'm %(age)d years old" % locals())

if __name__ == "__main__":
    test_locals_in_function_scope()
    test_locals_with_paremeters("Sungeun", 34)

    eunwoo = Person()
    eunwoo.test_locals_with_local_vars_in_method()

    jiyul = Person(name = "Jiyul", age = 4)
    jiyul.test_locals_in_method()
```

출력 결과
```
My name is Jihoon I'm 34 years old
My name is Sungeun I'm 34 years old
My name is Eunwoo I'm 2 years old
Traceback (most recent call last):
  File "locals-example.py", line 24, in <module>
    jiyul.test_locals_in_method()
  File "locals-example.py", line 11, in test_locals_in_method
    print("My name is %(name)s I'm %(age)d years old" % locals())
KeyError: 'name'
```




