Create DOM element from HTML
============================

jQuery 사용할 때는 아래와 같이 DOM element를 동적으로 편하게 생성할 수 있었다.

```
$('<div id="container"><h1>Hello World!!</h1></div>');
```

Vanilla JS로 같은 일을 어떻게 하면 효과적으로 할 수 있을까를 최근 고민하다가, 그냥 최상위 element는 document.createElement()로 항상 만들고, 그 안의 내용을 `innerHTML`로 삽입하는 방법을 사용했었다. 위 코드를 표현해보면 아래와 같다.

```
let container = document.createElement('div');
container.id = 'container';
container.innerHTML = '<h1>Hello World!</h1>'
```

어떻게 보면, 같은 해법일 수 있는 무릎을 탁치게 만드는 방법이 있었다. 그냥 안쓰는 `<div>` tag를 하나 만들어서 innertHTML로 내가 원하는 string을 삽입한 후, `firstChild`만 사용하는 방법이다.

```
let div = document.createElement('div');
div.innerHTML = '<div id="container"><h1>Hello World!!</h1></div>';
let result = div.firstChild;
```

이런 방식이면, 최상위 노드를 따로 구분하여 만들어 줄 필요도 없고, 아래와 처럼 일반화하여 string html을 DOM element로 만들어 주는 유틸리티도 구현이 가능하다.

```
function elementFromString (text) {
    let div = document.createElement('div');
    div.innerHTML = text;
    return div.firstChild;
}
```




