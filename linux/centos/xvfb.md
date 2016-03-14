XVFB(X windows system virtual framebuffer) 설치
---
- Date: 2016-03-10

요즘 진행 중인 프로젝트에서 MySQL Workbench를 모델링 툴로 사용하고 있다.

모델 파일인 MWB을 Workbench 에서 열어서 DB 생성용 SQL script로 만들어 내는 것은 당연히 가능하다.
또한 Workbench runtime에서 기능을 Python script로 작성해서 control하는 것이 가능하기 때문에,
위와 같은 배치성 작업을 자동으로 해내는 것이 가능했다.

자동화 용도로 MWB에서 SQL을 바로 추출해 내고 싶은데, 문제는 Python script를 실행시 인자로 넘긴다 하더라도, UI가 실행되어야 한다는 것이다.
X윈도우가 없는 서버환경에서 REST API로 VCS에 있는 MWB파일을 바로 SQL로 변환해서 response로 넘겨주고 싶은데,
저 X윈도우가 없어서 할 수가 없었다.

아 정말.. 왜 Workbench는 저렇게 만들었을까.. 저런건 GUI랑 분리 좀 해놓지..

그래서 이리저리 찾아보다가 궁여지책으로 찾은 방법이 가상 X window framebuffer (xvfb)를 사용하는 것이다.

어차피 Workbench는 떴다 사라지기 때문에 뭔가 화면에 나올 필요가 없었다.

아래와 같은 방법으로 CentOS7에 xvfb를 설치하고 실행 할 수 있다.

```
$ sudo yum install -y xorg-x11-server-Xvfb
$ /usr/bin/Xvfb :1 -screen 0 1920x1080x24 &
$ export DISPLAY=:1
```

아래와 같이 systemd service로 등록해서 사용할 수도 있다.

```
[Unit]
Description=Virtual Frame Buffer X Server
After=network.target

[Service]
ExecStart=/usr/bin/Xvfb :99 -screen 0 1024x768x24

[Install]
WantedBy=multi-user.target
```

Reference
---

- [How to install and configure Xvfb in Linux/Centos](http://ithubinfo.blogspot.kr/2013/11/how-to-install-and-configure-xvfb-in.html)
