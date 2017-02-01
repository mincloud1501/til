function format(pattern, ...rest) {
    var items = rest.filter(function (x) { return x > 1});
    return pattern.replace("%v", items);
}

let result = format("scores: %v", 1, 5, 3); // 5 3
console.log(result);
