addProductToCart();

function addProductToCart() {
    let buttons = document.getElementsByClassName("btn-success");
    for (let button of buttons) {
        button.addEventListener("click", async function () {
            let productId = button.getAttribute("product-id")
            await addProductToCartRequest({productId: productId});
        })
    }
}

async function addProductToCartRequest(productId){
     // todo add/cart
    return await apiPost('/cart', productId);
}

async function apiPost(url, payload) {
    let response = await fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: payload
    });
    if (response.status === 200){
        // ? .json() ?
        return response;
    }
}