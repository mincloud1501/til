function showUser(name, age, weight) {
    return name + ": " + age + " " + weight;
}

let user = ["Alex", 28, 130];
let result = showUser(...user); // new feature - spread

console.log(result); // Alex: 28 130

