

function addProductToCart(id) {
    let element = document.getElementById(id)
    let productId = element.getAttribute("id")
    console.log(productId)
    sendProductToCart(productId)
    let response = getResponse("/cart")
    alert(response)
}

function countItemsInCart() {
    alert("chuj")
}

function sendProductToCart(productId) {
    let bodyContent = {
        productId: productId
    };
    postData('/cart', bodyContent);
}

function getBasket(){
    let response = getData("/cart");
    return response;
}

function getData(url) {
    let error = 'error'
    let response = fetch(url, {
        method: "GET",
    })
    if (response.status == 200) {
        let data = response.json()
        return data
    } else {
        return error
    }
}

function postData(url, bodyContent) {
    let response =fetch(url, {
        method: "post",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bodyContent),
    })
}

async function deleteData(url) {
    let response = await fetch(url, {
        method: "DELETE"
    })
}

async function putData(url, bodyContent) {
    let response = await fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body_content),
    })
}