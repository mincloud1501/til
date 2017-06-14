node.js 어플리케이션 heap dump
==============================

### 사용 방법
1. heapdump package를 설치한다.
1. heapdump 가 필요한 순간 `heapdump.writeSnapshot(filepath)`를 호출하여 heap dump를 만든다
1. Chrome 개발자 도구 'Memory' 탭의 Profile에서 Load하여 heap를 loading한다

Reference
=========
* http://bcho.tistory.com/1097
