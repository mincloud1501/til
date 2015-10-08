def test_locals_in_function_scope():
    name = "Jihoon"
    age = 34
    print("My name is %(name)s I'm %(age)d years old" % locals())

class Person():
    def __init__(self, name, age):
        self.name = name
        self.age = age
    def test_locals_in_method(self):
        print("My name is %(name)s I'm %(age)d years old" % locals())
    def test_locals_with_local_vars_in_method(self):
        name = "Eunwoo"
        age = 2
        print("My name is %(name)s I'm %(age)d years old" % locals())

if __name__ == "__main__":
    test_locals_in_function_scope()

    eunwoo = Person(None, None)
    eunwoo.test_locals_with_local_vars_in_method()

    jiyul = Person(name = "Jiyul", age = 4)
    jiyul.test_locals_in_method()


