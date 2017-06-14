
Syntax
======
```
new Promise(/* executor */ function (resolve, reject) { ... });
```

* thenable : then 메서드가 있는 객체를 뜻 함.

Methods
=======
* Promise.all(iterable)
* Promise.race(iterable)
* Promise.reject(reason)
* Promise.resolve(value)

Prototype
=========
* Promise.prototype.constructor
* Promise.prototype.catch(onRejected)
* Promise.prototype.then(onFulfilled, onRejected)

Example
=======

```
'use strict';

function testPromise () {
    log.info('Started');

    var p1 = new Promise(
        function (resolve, reject) {
            log.info('Promise started');
            window.setTimeout(
                function () {
                    resolve(0);
                }, Math.random() * 2000 + 1000
            );
        }
    );

    p1.then(function (val) {
        log.info('Promise fulfilled');
    })
    .catch(function (reason) {
        log.info('Promise rejected ('+reason+') here');
    })l

    log.info('Promise made');
}

```

Result
======
```
Started
Promise started
Promise made
Promise fulfilled
```

Example 'using XMLHttpRequest'
==============================

```
'use strict';

var XMLHttpRequest = require('xmlhttprequest').XMLHttpRequest;

function $http (url) {
    var core = {
        ajax: function (method, url, args) {
            return  new Promise(function (resolve, reject) {
                var client = new XMLHttpRequest();
                var uri = url;

                if (args && (method === 'POST' || method === 'PUT')) {
                    uri += '?';
                    var argcount = 0;
                    for (var key in args) {
                        if (args.hasOwnProperty(key)) {
                            if (argcount++) {
                                uri += '&';
                            }
                            uri += encodeURIComponent(key) + '=' + encodeURIComponent(args[key]);
                        }
                    }
                }

                client.open(method, uri);
                client.send();

                client.onload = function () {
                    if (200 <= this.status && this.status < 300) {
                        resolve(this.response);
                    } else {
                        reject(this.statusText);
                    }
                };

                client.onerror = function () {
                    reject(this.statusText);
                };

            });
        }
    };

    return {
        'get': function (args) {
            return core.ajax('GET', url, args);
        },
        'post': function (args) {
            return core.ajax('POST', url, args);
        },
        'put': function (args) {
            return core.ajax('PUT', url, args);
        },
        'delete': function (args) {
            return core.ajax('DELETE', url, args);
        }
    };
}

var mdnAPI = 'https://developer.mozilla.org/en-US/search.json';
var payload = {
    'topic': 'js',
    'q': 'Promise'
};

var callback = {
    success: function (data) {
        console.log(1, 'success', JSON.parse(data));
    },
    error: function (data) {
        console.log(2, 'error', JSON.parse(data));
    }
};


$http(mdnAPI)
    .get(payload)
    .then(callback.success)
    .catch(callback.error);

```

Debugging in node.js
====================
그냥 `node <filename>` 으로 실행하면 Promise안에서 에러가 발생할 경우 `Unhandled promise rejection ...` 어쩌구 하면서 에러 메시지가 찍히는데, 에러 발생하는 부분이 출력되지 않아서 tracing이 안된다. 아래와 같이 node 실행 시 `--trace-warnings` option을 주면 에러가 발생한 부분의 stack trace가 출력된다.

`$ node --trace-warnings <filename>`


References
==========
* https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Promise
* https://stackoverflow.com/questions/20990955/how-do-i-debug-promise-based-code-in-node
