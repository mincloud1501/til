NETCONF(Network Configuration Protocol)
---

### Overview

* 네트워크 관리 프로토콜
* 네트워크 장비들의 설치, 운용, 제거를 위한 방법을 제공한다.
* 간단한 RPC를 통해서 수행 됨
* XML base

NETCONF protocol은 개념적으로 4개의 레이어로 나누어짐.

1. Content Layer - Configuration과 Notification data로 구성.
2. Operations Layer - Configuration data를 가져오고 설정하기 위한 기본 프로토콜 명령을 정의
3. Message Layer - RPC call과 Notiication의 Encoding mechanism을 제공
4. Secure Transport Layer - 클라이언트와 서버간의 안전하고 신뢰할 수 있는 메시지의 전송을 제공


### Protocol Layers

#### 1. Content

* Well-formed XML

#### 2. Operations

* `<get>` - Running configuration 과 장치의 상태 정보
* `<get-config>` - 전체 혹은 특정 일부의 설정 저장소 정보 얻기
* `<edit-config>` - 생성, 삭제, 병합, 교체등을 통해 설정 저장소 정보 변경
* `<copy-config>` - 전체 설정 정보를 다른 설정 저장소로 복사함
* `<delete-config>` - 설정 저장소를 삭제
* `<lock>` - 한 장치의 전체 설정 저장소를 lock
* `<unlock>` - `<lock>`을 통해 이전에 획득한 lock을 release
* `<close-session>` - NETCONF session의 graceful 종료 요청
* `<kill-session>` - NETCONF session의 강제 종료

#### 3. Message

- `<rpc>` - RPC 호출
- `<rpc-result>` - RCP 호출에 대한 결과
- `<notification>` - 이벤트 알림
- 모든 메시지는 Well-formed XML
- Pipelining 가능 - 다음 메시지 전달을 위해 이전 메시지의 결과를 기다릴 필요 없음.

#### 4. Secure Transport

* 기본적으로 SSH 사용
* 이후에 SOAP, BEEP등을 사용하는 전송방법 추가 됨

### 참고
* https://en.wikipedia.org/wiki/NETCONF

