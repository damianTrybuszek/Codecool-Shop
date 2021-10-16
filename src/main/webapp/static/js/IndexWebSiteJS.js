setInterval(() => {
    getData("/cart").then(numberProductCart => {
        addRealNumber(numberProductCart)
    })
}, 200)

function addProductToCart(id) {
    let element = document.getElementById(id)
    let productId = element.getAttribute("id")

    sendProductToCart(productId).then(number => {
        // addRealNumber(number)
        addAlertDiv()
        setTimeout(function () {
            let empty = document.getElementById("alert-position");
            empty.remove();
        }, 1500);
    })
}

function getProduct() {
    getData("/cart-items").then(productList =>{
        let sum = sumPrice(productList)
        makeDropCart()
        addSumPrice(sum)
        addItemDrop(productList)
    })

}

// function showProdNumber() {
//     let response = getData("/cart")
//     let resp = JSON.parse(response)
//     console.log(resp["numberProd"])
//     addRealNumber(resp)
// }

function sendProductToCart(productId) {
    let bodyContent = {
        productId: productId
    };
    return postData('/cart', bodyContent);

}

// function getData(){
//     let response = getData("/cart");
//     return response;
// }

async function getData(url) {
    let error = 'error'
    let response = await fetch(url, {
        method: "GET",
    })
    if (response.status === 200) {
        let data = await response.json()
        console.log(data)
        return data
    } else {
        return error
    }
}

async function postData(url, bodyContent) {
    let response = await fetch(url, {
        method: "post",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bodyContent),
    });
    const data = await response.json();
    return data;
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

// function toJSon(response){
//     return JSON.stringify(response.)
// }

function addAlertDiv() {
    // let responseJson = JSON.stringify(response, "add")
    let space = document.getElementById("space-alert");
    space.insertAdjacentHTML("beforeend", buildAlert())
    let hMessage = document.getElementById("message").innerHTML;
}

function buildAlert() {
    return ' <div class="alert alert-success" role="alert" id="alert-position">' +
      ' <strong></strong><p id="message">Product succesfully added!</p></strong> ' +
      '</div> '
}

function addRealNumber(cartNumber) {
    let element = document.getElementById("badge-web");
    element.innerHTML = cartNumber;


}

function makeDropCart() {
    let basket = document.getElementById("dropCart");
    basket.insertAdjacentHTML("beforeend", buildDropDownBasket());

}

function addSumPrice(sumPrice){
    let sum = document.getElementById("sum-cart");
    sum.innerHTML = sumPrice;
}

function addItemDrop(productInCart){
    let add = document.getElementById("product-cart");
    for (let product in productInCart) {
        add.insertAdjacentHTML("beforeend", buildItemsInCart(product));

    }
}

function sumPrice(productList) {
    let count = 0;
    for (prod in productList) {
        count = Math.ceil(parseInt(prod["defaultPrice"]) * parseInt(prod["quantity"]));
    }
    return count;
}

function buildDropDownBasket() {
    return '<div class="shopping-cart">' +
        '<div class="shopping-cart-header">' +
        '<i class="icon-basket-1"></i>' +
        '<span class="badge"></span>' +
        '<div class="shopping-cart-total">' +
        '<span class="Lighter-text">Total:</span>' +
        '<span class="main-color-text total" id="sum-cart"></span>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '<ul class="shopping-cart-items" id="product-cart"></ul>' +
        '<a href="#" class="cofrim-shopping">Go</a>'
}

function buildItemsInCart(product) {
    return '<li class="clearfix" id="li-cart">' +
        '<img id="img-to-cart" src="/static/img/product_"' + product["id"] + '".jpg">' +
        '<span class="item-name">'+ product["name"] +'</span>' +
        '<span class="item-detail">'+ product["supplier"] +'</span>' +
        '<span class="item-price">'+ product["defaultPrice"] +'</span>' +
        '<span class="quantity">'+ product["quantity"] +'</span>' +
        '</li>'
}