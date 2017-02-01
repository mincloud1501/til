
function Panel({title: title, pos: [x, y]}) {
    console.log(title, x, y); // Users 10 15
    return title + x + y;
}

let config = {title: "Users", pos:[10, 15]};

new Panel(config);

