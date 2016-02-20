### 가상화의 종류

x86 아키텍쳐는 특권의 방식(manner of privileges)에 따라 4개의 링으로 나누어 진다.
특권은 사용자 어플리케이션이 실행되는 ring3에서 부터 OS가 실행되는 ring0까지 아래로 갈 수록 축소되는 형태이다.

핵심 문제는 Guest OS가 요청한 특권 명령을 (privileged commands)을 어떻게 처리하는 지에 달려 있다.

#### 전가상화 (Full Virtualization)

* 특권 명령을 trap하여 Hypervisor에서 수행 하고, Binary Translation을 통해 특권 명령을 Hyper call로 동적으로 변경함.
* 장점 : Guest OS에 수정이 불필요
* 단점 : Dynamic binary translation에 따르는 overhead

#### 반가상화 (Para Virtualization)

* 특권 명령을 하이퍼 콜(하이퍼 바이저가 제공하는 abstracted layer의 command)형태로 Hypervisor에 직접 요청함
* 단점 : Guest OS의 kernel이 특권 명령을 요청할 때 하이퍼 콜을 사용하도록 수정이 필요함
* 장점 : Dynamic binary translation이 필요없어지므로 이에 따른 오버헤드가 없어짐.
* Xen

### Hardware assisted virtualization

Ring 0 밑에 하나의 Layer(VMM)를 더 두고 가상 머신의 Privileged Instruction을 수행을 하드웨어가 도와 주는 형태이다.
Software Layer에서 Dynamic Binary Translation을 할 필요가 없어지므로 이에 따른 오버헤드가 사라진다.
Intel의 VT-x와 AMD의 AMD-V가 이에 해당 된다.

### 참조

* https://vietstack.wordpress.com/2014/10/03/introduction-of-virtualization/
