let user = {name: "Kang", location:{x: 10, y: 20}};
let {name: _name, location: {x: _x, y: _y}} = user;

console.log(_name, _x, _y); // "Kang, 10, 20"
