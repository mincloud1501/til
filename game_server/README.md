게임 서버 개발 관련 자료
===

Protocol 
---
- [Protocol Buffer][1]
  - Server/Client간 주고 받을 데이터 구조를 IDL로 정의
  - 다양한 Language(C#,C++,Go,Java)로 Skeleton,Stub 생성
  - Data protocol및 serialize/deserialze부분만을 포함
  - 송수신부는 포함하지 않음 
  - [Unity3D에서 Node.js로 데이터를 protobuf를 사용해서 Websocket으로 전송하기][12]

Redis
---
- [redisgate - cluster][8]
  - Redis관련 solution 업체인 redisgate의 사이트인데 레디스 관련 학습자료가 풍부함.

C# / .NET
---
- [C# 프로그래밍 배우기][9]

Go
---
- [redigo][10] : Redis client library
- [websocket example][11] : websocket server 구현 예제

Theory
---
- [C10K Problmen][2]
  - Linux 기반 서버에서 1만이상의 Client connection을 받아낼 수 있는까에 대한 의문에서 시작된 실험 내용.
  - [Wikipedia 페이지][3]
  - [GpgStudy 관련 토론 내용][4]

- [멀티스레드 환경에서의 Lock-free 알고리즘 소개][7]
  - 멀티스레드 환경에서 Lock이 성능을 저하시키는 큰 요소이다라는 전제 하에 시작되는 개발론
  - Lock을 사용하지 않고 동기화 문제를 해결하는 방법에 대한 이론
  - 동기화 문제가 발생하는 부분에서 Lock을 걸지말고, CAS(Check And Swap) 방식으로 처리.
  - CAS는 100% 이해는 안되었지만, Race condition이 발생했느지를 체크하는 조건을 두고, 내가 Atomic하게 처리해야 하는 일을 하는 도중에 다른 스레드가 개입하여 무결성을 깼다면 내가 처리한 모든것을 무효화 하고 포기하는 식으로 동기화 문제를 피해가는 것..

Project
---
- [Pomelo][5] 
  - NetEase라는 중국 내 2위 정도의 게임 회사에서 개발한 게임 서버 솔루션
  - node.js(socket.io, websocket) 기반
- [EpServerEngine][6]
  - C#,IOCP 기반의 Server/client code template

[1]:https://developers.google.com/protocol-buffers/
[2]:http://www.webcitation.org/6ICibHuyd
[3]:https://en.wikipedia.org/wiki/C10k_problem
[4]:http://www.gpgstudy.com/forum/viewtopic.php?t=2097
[5]:https://github.com/NetEase/pomelo/wiki/Pomelo-%EC%86%8C%EA%B0%9C
[6]:http://www.codeproject.com/Articles/832818/EpServerEngine-cs-A-lightweight-IOCP-TCP-Template
[7]:http://www.slideshare.net/zzapuno/ndc2014-2
[8]:http://www.redisgate.com/redis/cluster/cluster.php
[9]:http://www.csharpstudy.com/
[10]:https://github.com/garyburd/redigo
[11]:https://github.com/golang-samples/websocket
[12]:http://yakolla.tistory.com/52