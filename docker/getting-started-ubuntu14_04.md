도커 시작하기
---
- Date : 2016-03-18


`curl http://get.docker.io | sh` 이런 까리한 한방 설치 스크립트가 있었는데, Ubuntu 설치 공식 가이드 문서에서 사라졌다.
아마도 최근 배포 버전을 스크립트가 지원할 수 없는 상황인것 같다.

억울하지만 차근차근 따라가며 설치하도록 하자.

Ubuntu 14.04 기본 이미지에 docker를 provisioning하는 [Vagrantfile](vagrant/Vagrantfile)도 작성해 두었으니 참고하도록 한다.

설치
---

```bash
$ sudo -i
$ apt-get update
$ apt-get install -y apt-transport-https ca-certificates
$ apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 \
    --recv-keys 58118E89F3A912897C070ADBF76221572C52609D
$ echo 'deb https://apt.dockerproject.org/repo ubuntu-trusty main' \
    > /etc/ apt/sources.list.d/docker.list
$ apt-get update
$ apt-get purge lxc-docker
$ apt-get install -y linux-image-extra-$(uname -r)
$ apt-get install -y apparmor
$ apt-get update
$ apt-get install -y docker-engine
$ usermod -aG docker vagrant
$ sed -i 's/GRUB_CMDLINE_LINUX=""/GRUB_CMDLINE_LINUX="cgroup_enable=memory  swapaccount=1"/g' \
    /etc/default/grub
$ update-grub
$ sed -i 's/DEFAULT_FORWARD_POLICY="DROP"/DEFAULT_FORWARD_POLICY="ACCEPT"/ g' \
    /etc/default/ufw
$ ufw reload
$ ufw allow 2375/tcp
$ sed -i 's/#DOCKER_OPTS="--dns 8.8.8.8 --dns 8.8.4.4"/DOCKER_OPTS="--dns
8.8.8.8 --dns 8.8.4.4"/g' /etc/default/docker
$ restart docker
$ systemctl enable docker
```



참고
---
- [Ubuntu 를 위한 Docker 공식 설치 가이드](https://docs.docker.com/engine/installation/linux/ubuntulinux/)
