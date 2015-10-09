Django command usage
----

### 프로젝트 생성
아래의 명령어를 실행하면 django project를 구성하는 기본 template이 생성된다.
```bash
$ django-admin startproject <project-name>
```

### 마이그레이션
마이그레이션은 아래의 상황에서 수행한다.
* 새로 프로젝트를 생성하거나 저장소로 부터 내려 받은 경우
* 모델에 변경이 발생하여 makemigrations로 적용할 patchset이 생성 된 경우.
```bash
$ python manage.py migrate
```

### 테스트 서버 실행
그냥 실행하면 8000 port로 실행된다.
```bash
$ python manager.py runserver
```
Production mode에서 사용하면 안되지만, 외부에서 접속해야하는 경우 `0:<port-number>`를 인자로 준다.
```bash
$ python manage.py runserver 0:8080
```

### App 생성
App은 하나의 project내에 여러개 생성할 수 있다. 아래 명령어를 실행하면 app template이 생성된다.
```bash
$ python manage.py startapp my_app
```
새로 생성한 App을 실제로 적용하려면 `<project-root>/<project-name>/settings.py`의 `INSTALLED_APPS`에 해당 App을 추가해야 한다.

### Migration code 생성
Model을 새로 추가하거나, 변경이 발생한 경우 db table을 새로 생성하거나, scheme가 수정되야 하는데, django에서는 이런 상황이 발생할 때 해당 patchset을 code로 생성한다.
```bash
$ python manage.py makemigrations <app-name>
```
이 patchset은 source code와 함께 repository에 관리 되어, 이 앱이 배포되는 서버에서도 변경을 순서대로 migration할 수 있도록 한다.

### python shell 실행
```bash
$ python manage.py <app-name>
```
Python shell을 사용하여 application code를 테스트해 볼 수 있다.
