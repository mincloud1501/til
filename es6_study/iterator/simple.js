let fibonacci = {
    [Symbol.iterator]() {
        let pre = 0, cur = 1;
        return {
            next () {
                [ pre, cur ] = [ cur, pre + cur ]
                return { done: (cur > 1000), value: cur }
            }
        }
    }
}

for (let n of fibonacci) {
    console.log(n);
}
