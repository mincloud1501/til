(function() {
    function handleRequest(data, method) {
        method = method || "GET";

        console.log("handleRequest, method: " + method);
    }

    function handleRequestInMannerOfEs6(data, method = "POST") {
        console.log("handleRequestInMannerOfEs6, method : " + method);
    }

    handleRequest();
    handleRequestInMannerOfEs6();
}());
