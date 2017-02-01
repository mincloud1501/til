let x3DPoint = {x:20, y:15, z:1 };

// full notation
let {x: _x, y: _y, z: _z} = x3DPoint;
console.log(_x, _y, _z);

// simple notation
let {x, y, z} = x3DPoint;
console.log(x, y, z);
