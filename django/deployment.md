Django project deployment
===

## Overview
uWSGI와 nginx를 사용하여 django project를 deploy하는 방법을 정리한다.
구체적인 설명에 앞서 WSGI와 uWSGI, 그리고 NginX 에 대해서 간략히 설명하면 아래와 같다.

**Nginx**

Apache와 같은 역할을 담당하는 web server software이다. 

**WSGI(Web Server Gateway Interface)**

Web server와 python으로 작성 된 웹 어플리케이션 간에 interface에 대한 표준이다.
WSGI 는 server side interface와 application side interface 두 가지가 존재한다.

**uWSGI**

WSGI server 중 하나다. 즉, WSGI service interface의 구현체이다.


## Installation

### nginx

* ubuntu
``` 
$ sudo apt-get install nginx
```

* centos
```
$ sudo yum -y install nginx
```

### uWSGI
```
$ sudo pip install uwsgi
```

## Configuration

### nginx config 작성

```
upstream django {
    # Unix 소켓과 IP 소켓 방식 모두 사용 가능하지만, Unix 소켓 방식이 당연히 더 빠르다
    server unix:///var/run/myapp.sock;
}

server {
    listen 80;
    server_name my.site.com;
    charset utf-8;

    # Media data는 WSGI에 요청하는 대신 직접 전달하도록 한다.
    location /media {
        alias /path/to/mysite/media;
    }

    # Static data(js,css,images)는 WSGI에 요청하는 대신 직접 웹서버에서 전달한다
    localtion /static {
        alias /path/to/mysite/static;
    }

    location / {
        uwsgi_pass django;
        include uwsgi_params;
    }
}
```

### INI 작성

```
[uwsgi]
chdir = /path/to/mysite
module = mysite.wsgi
master = true
processes = 5

uid = user
socket = /run/uwsgi/myapp.sock
chown = user:nginx
chmod = 660
vacuum = true

die-on-term = true
```

### uWSGI service 등록

**/etc/systemd/system/uwsgi.service**
```
[Unit]
Description=uWSGI

[Service]
ExecStartPre=-/usr/bin/bash -c 'mkdir -p /run/uwsgi; chown user:nginx /run/uwsgi'
ExecStart=/usr/bin/bash -c 'cd /path/to/mysite; uwsgi --ini /etc/uwsgi/app-enabled/myapp.ini'

[Install]
WantedBy=multi-user.target
```

## Reference
* https://ko.wikipedia.org/wiki/Nginx
* https://docs.djangoproject.com/en/1.8/howto/deployment/wsgi/
* http://uwsgi-docs.readthedocs.org/en/latest/tutorials/Django_and_nginx.html
* https://www.digitalocean.com/community/tutorials/how-to-set-up-uwsgi-and-nginx-to-serve-python-apps-on-centos-7




