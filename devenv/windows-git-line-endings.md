#### 윈도우즈 GIT의 개행문자를 CRLF로 설정하는 방법

##### msys-git 설치 시 Line endings 를 아래와 같이 설정
> 'Checkout as-is, commit as-is' 

##### diff시 ^M를 안보이게 하기 위해 아래와 같이 config 추가

```bash
$ git config --global core-whitespace cr-at-eol
```

#### Reference
* https://lostechies.com/keithdahlby/2011/04/06/windows-git-tip-hide-carriage-return-in-diff/
