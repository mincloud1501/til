
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

### A

```
'use strict';

function $http (url) {
    var core = {
        ajax: function (method, url, args) {
            var promise = new Promise(function (resolve, reject) {
                var client = new XMLHttpRequest();
                var uri = url;

                if (args && (method === 'POST' || method === 'PUT')) {
                    uri += '?';
                    var argcount = 0;
                    for (var key in args) {
                        if (args.hasOwnProperty(key)) {
                            uri += '&';
                        }
                        uri += encodeURIComponent(key) + '=' + encodeURIComponent(args[key]);
                    }
                }

                client.open(method, url);
                client.send();

                client.onload = function () {
                    if (this.status >= 200 && this.status < 300) {
                        resolve(this.response);
                    } else {
                        reject(this.statusText);
                    }
                };
                client.onerror = function () {
                    reject(this.statusText);
                };
            });
            return promise;
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
}
```

### B

```
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
```

### C

```
// 메서드 호출
$http(mdnAPI)
  .get(payload)
  .then(callback.success)
  .catch(callback.error);

// 메서드 호출을 실행하지만, 거부 경우를 다루는 다른 방법(1)
$http(mdnAPI)
  .get(payload)
  .then(callback.success, callback.error);

// 메서드 호출을 실행하지만, 거부 경우를 다루는 다른 방법(2)
$http(mdnAPI)
  .get(payload)
  .then(callback.success)
  .then(undefined, callback.error);
```

References
==========
* https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Promise
