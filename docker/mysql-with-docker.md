Docker로 MySQL 인스턴스 실행하기
---
Date: 2016-03-18

### Environment Variables

- MYSQL_ROOT_PASSWORD : root 비밀번호
- (optional) MYSQL_DATABASE : 초기에 생성할 DB가 있다면 이름을 적어준다.
- (optional) MYSQL_USER : root외 새로운 계정을 생성하고 싶다면 이름을 적는다. 이 계정은 위 MYSQL_DATABASE에 명시된 DB에 대한 super user 권한을 갖는다.
- (optional) MYSQL_PASSWORD : MYSQL_USER의 Password.
- (optional) MYSQL_ALLOW_EMPTY_PASSWORD : `yes`로 설정하면 root패스워드가 없는 상태로 컨테이너를 실행 함. 비추. 

### Run

```bash
$ docker run --name mysql \
  -e MYSQL_ROOT_PASSWORD=<your-root-password> \
  -e MYSQL_USER=<your-user-name> \
  -e MYSQL_PASSWORD=<your-user-password> \
  -d mysql:latest
```

`docker ps` 를 실행하면 컨테이너가 실행 중인 것을 확인할 수 있다.

실행 중인 instance의 IP address를 알려면 아래의 command를 실행하면 된다.

```bash
$ docker inspect --format='{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' mysql
172.17.0.2
```

Reference
---
- [Docker Hub - MySQL offical](https://hub.docker.com/_/mysql/)
- [Docker Engine - inspect](https://docs.docker.com/engine/reference/commandline/inspect/)