import {dataHandler} from "./DataHandler";


function addProductToCart(id) {

    let element = document.getElementById(id)
    let productId = element.getAttribute("id")
    dataHandler.sendProductToCart(productId)
    dataHandler.getResponse("/cart/")

}

function countItemsInCart() {

}
