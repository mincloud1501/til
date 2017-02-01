let square = (x) => x * x;
console.log(square(4)); // 16

(function() {
    let current = 0;
    let users = ["jhkang", "shin0oh"];
    let getUser = () => users[current];
    console.log(getUser());
}());

(function() {
    let users = [{name: "Mark", age: 28}, {name: "Sarah", age: 26}];
    users.forEach((user) => { if (user.age > 2) console.log(user.name, user.age) });
}());

