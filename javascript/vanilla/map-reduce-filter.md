map() and reduce(), filter() of Array
=====================================


map()
-----

```
[1,2,3,4,5].map(n => n * n);

(result) [1, 4, 9, 16, 25] 
```

reduce()
--------

```
[1,2,3,4,5].reduce((x,y) => x + y);

(result) 15


[1,2,3,4,5].reduce((x,y) => (x>y)?x:y);

(result) 5

[1,2,3,4,5].reduce((x,y) => x*y);

(result) 120
```

filter()
--------

```
[1,2,3,4,5].filter(x => x > 3);

(result) [4, 5]
```


