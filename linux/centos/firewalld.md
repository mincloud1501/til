Using firewalld
---

Date: 2015-10-09

centos7 기반에 nginx를 직접 빌드해서 사용하는 형태로 서버 구성을 하고 있는데, yum으로 설치 했을 때와는 다르게 잘 동작하지 않는 문제가 발생하였다.

centos에는 netstat도 없어서.. (T-T...) 찾아보니 ss라는 유틸리티를 대신 사용할 수 있었다.
어쨌든 ss로 확인해보니 nginx가 문제 없이 80번 포트를 listening 하고 있었음.

이리 저리 원인을 찾아보던 중, wget으로 테스트 해보니 서버 내부에서는 요청에 대한 응답이 제대로 오는 것을 확인 할 수 있었다.

그리하여, iptables --list를 쳐보니, 방화벽 설정이 되어 있었다.
iptables로 80번에 대한 요청을 ACCEPT 하는 룰을 추가하니 nginx가 잘 동작한다.
이렇게 해결하는 건 아닐 것 같아 용규에게 물어보니 firewall-cmd 라는 명령어로 설정해야 한다고 했다.

이 명령어로 좀 더 찾아보니, 아래와 같은 내용을 찾을 수 있었다.
> https://access.redhat.com/documentation/en-US/Red_Hat_Enterprise_Linux/7/html/Security_Guide/sec-Using_Firewalls.html


Essentials
---
* Redhat 계열 Linux에는 default로 설치 된다.
* iptables(service layer)를 대체한다.
* firewall-config 라는 gui를 가진 tool도 있다.
* firewall-cmd라는 command line tool도 제공한다.

Usage
---
http(tcp/80) 요청을 허용하는 방법은 아래와 같다.
```bash
$ firewall-cmd --permanent --zone=public --add-port=80/tcp
```

firewalld service를 stop/start 하는 방법은 아래와 같다.
```bash
$ systemctl stop firewalld
$ systemctl start firewalld
```


