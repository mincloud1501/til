
EC2 인스턴스의 Timezone 변경
---

이 내용은 Amazon Linux를 사용하는 경우에만 유효하다. 다른 배포판을 사용하는 경우 그 배포판에 맞는 방법을 적용해야 한다.

1. `/usr/share/zoneinfo` 디렉토리 내에서 적절한 지역에 해당하는 파일을 찾는다. 우리나라의 경우는 `/usr/share/zoneinfo/Asia/Seoul` 이다.
2. `/etc/sysconfig` 를 열어서 아래와 같이 수정한다.

    ```
    ZONE="Asia/Seoul"
    UTC=false
    ```

3. /etc/localtime 파일을 1.에서 찾은 파일에 대한 심볼릭 링크로 만들어 준다.

    ```bash
    $ ln -sf /etc/localtime /usr/share/zoneinfo/Asia/Seoul
    ```
 

Chef DSL
---

위 내용을 Chef DSL code로 표현하면 아래와 같다.

```ruby
# Change timezone to 'Asia/Seoul'
file '/etc/sysconfig/clock' do
    content <<-EOF
ZONE="Asia/Seoul"
UTC=false
EOF
    action :create
end

link "/etc/localtime" do
    to "/usr/share/zoneinfo/Asia/Seoul"
end
```


Reference
---

- [AWS User Guide - Linux 인스턴스의 시간 설정](http://docs.aws.amazon.com/ko_kr/AWSEC2/latest/UserGuide/set-time.html)
