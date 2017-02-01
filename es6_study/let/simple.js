(function() {
    'use strict';

    let localvar = '1';

    function test_func() {
        let localvar = '2';
        console.log("in test_func 2==" + localvar);
    }

    test_func();
    console.log("outside, 1==" + localvar);
}());
