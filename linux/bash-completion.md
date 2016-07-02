Variables
---------
- COMPREPLY : Bash가 현재 완성 가능한 단어들을 얻어 갈 수 있는 Array
- COMP_WORDS : 현재까지 완성 된 단어를 담고 있는 Array
- COMP_CWORD : 현재 단어의 COMP_WORDS 상의 Index

compgen
-------
```
$ compgen <opt> [<candidate-words>] [--] <current-word> 

OPTION:
  -f : file 이름들 중 현재 단어로 완성 가능한에 것들을 반환
  -A : hostname 중 현재 단어로 완성 가능한 것들을 반환
  -W : words 주어진 단어들 중, 현재 단어로 완성 가능한 것들을 반환
```

Example
-------

```
_foo() 
{
    local cur prev opts
    COMPREPLY=()
    cur="${COMP_WORDS[COMP_CWORD]}"
    prev="${COMP_WORDS[COMP_CWORD-1]}"
    opts="file hostname"
 
    case "${prev}" in
        file)
            COMPREPLY=( $(compgen -f ${cur}) )
            return 0
            ;;
        hostname)
            COMPREPLY=( $(compgen -A hostname ${cur}) )
            return 0
            ;;
        *)
        ;;
    esac

    COMPREPLY=( $(compgen -W "${opts}" -- ${cur}) )
}
complete -F _foo foo

```


Reference
---------
- debian-administration.org - [An introduction to bash completion: part 2](https://debian-administration.org/article/317/An_introduction_to_bash_completion_part_2)
