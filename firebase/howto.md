Firebase Tips
=============

### Child의 개수를 전체 데이터 로드 없이 셀 수 있는 방법이 없다

> [stack overflow:In firebase is there a way to get the number of children of a node without load?][0]

추천하는 방법은 count property를 따로 관리하라는 건데, 이게 가능한지 좀 이해가 안간다.
race condition으로 인해 lock을 걸지 않으면 count property는 정확한 값을 갖기 어려울 것 같다.


### auto increment가 없다

> [stack overflow:auto increment a value in firebase with javascript][1]

보통 RDBMS에서 auto increment로 PK용 Unique ID를 생성하는 경우가 많은데, Firebase에는 그런 기능이 없다.
대신 push-id라는 unique-id를 사용한다.


### array를 사용하지 않는다

> [firebase blog: Best practice arrays in firebase][2]

기본적으로 Array는 없다. 모든 것이 object 형태로 변환된다.
* array는 분산환경에서 많은 문제를 야기시킨다고 설명하고 있다. 그 이유는 array는 각 원소에 index로 접근하는 데 여러 사용자가 동시에 접근하고, 그에 대한 변경이 발생할 경우 element의 index는 수시로 변경되기 때문이다.
* 따라서 각 element에 id로 접근하는 것이 안전하다. 따라서 element 고유의 id 를 생성하는 것 역시 필요하게 된다.
* firebase에서는 이를 push-id라고 부른다. ref.push() 라는 메서드를 호출하면 childRef 가 생성되고 childRef.key()하면 고유의 push-id를 알아낼 수 있다.

Array 대신 아래와 같은 방법을 취하는 코드들을 많이 볼 수 있다.

```
let participants = {
    'my-room-id': {
        'jhkang': true,
        'shin0oh': true,
        'sckwon': true,
        'sskwon': true,
    }
}
``` 

[0]:https://stackoverflow.com/questions/15148803/in-firebase-is-there-a-way-to-get-the-number-of-children-of-a-node-without-load
[1]:https://stackoverflow.com/questions/39065786/auto-increment-a-value-in-firebase-with-javascript
[2]:https://firebase.googleblog.com/2014/04/best-practices-arrays-in-firebase.html
