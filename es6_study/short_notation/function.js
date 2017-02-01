'use strict';

// casual function
let result = [1, 2, 3].map(function(x) { return x * x; });
console.log(result);

// arrow function
let result2 = [1, 2, 3].map((x) => x * x);
console.log(result2);
