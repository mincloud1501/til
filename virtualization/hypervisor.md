### Hypervisor

단일 시스템을 논리적으로 분할 해 주고, 분할한 시스템이 서로 충돌하지 않도록 하는 역할

#### Type 1(native or bare metal)

* Hardward 위에 직접 Hypervisor가 실행되고 그 위에 OS가 실행되는 방식
* Hyper-V, ESX, Xen, KVM 등

##### Microkernelized Hypervisor

* 드라이버와 메모리 관리등의 가상 머신이 필요로하는 아주 최소한의 소프트웨어 스택만을 포함
* MS의 Hyper-V

##### Monolithic Hypervisor

* Microkernelized 방식에 비해 좀 더 많은 소프트웨어와 관리 인터페이스를 포함
* 예를 들면, 네트워크나 디스크 드라이버등을 VM layer가 아닌 Hypervisor layer에 포함하는 것
* VMWare의 ESX

#### Type 2(hosted)

* OS위에 Hypervisor가 어플리케이션 처럼 실행되고 그 위에 또 다른 OS를 실행하는 방식
* Virtualbox, VMWare 등이 여기에 해당 됨

### 참조

* [Vietstack - Introduction of virtualization](https://vietstack.wordpress.com/2014/10/03/introduction-of-virtualization/)
