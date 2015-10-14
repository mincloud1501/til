Change hostname for root access
---

테스트 환경을 위해 /etc/hosts에 임의의 hostname을 설정하고, MySQL에 root로 접속하려면,
아래와 같이 mysql.user table의 host field의 값을 변경해야 한다.

```sql
mysql> use mysql;
mysql> update user set Host = '%' where User = 'root' and Host = 'localhost';
mysql> flush privileges;
```
