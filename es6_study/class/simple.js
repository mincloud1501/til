class Shape {
    constructor (id, x, y) {
        this.id = id;
        this.move(x, y);
    }

    move (x, y) {
        console.log(`Shape.move(${x}, ${y})`)
        this.x = x;
        this.y = y;
    }

    toString() {
        return `Shape(id=${this.id}, x=${this.x}, y=${this.y})`
    }
}

class Rectangle extends Shape {
    constructor(id, x, y, width, height) {
        super(id, x, y);
        this.width = width;
        this.height = height;
    }

    move (x, y) {
        console.log(`Rectangle.move(${x}, ${y})`);
        super.move(x, y);
    }

    static defaultRectangle() {
        return new Rectangle("default", 0, 0, 100, 100);
    }
}

class Circle extends Shape {
    constructor(id, x, y, radius) {
        super(id, x, y);
        this.radius = radius;
    }
    toString() {
        return `Circle(radius=${this.radius}) > ` + super.toString();
    }
    static defaultCircle() {
        return new Circle("default", 0, 0, 100);
    }
}

let rect = new Rectangle("rect1", 10, 10, 100, 100);
console.log(`rectangle id=${rect.id} x=${rect.x} y=${rect.y} w=${rect.width} h=${rect.height}`)

let circle = new Circle('circle1', 10, 10, 45);
console.log(circle.toString())

console.log(Circle.defaultCircle());
console.log(Rectangle.defaultRectangle());
