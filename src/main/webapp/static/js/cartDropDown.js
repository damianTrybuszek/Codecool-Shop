
$("#basket-dropdown").dropdown({
    offset: 'auto'
})

function getProduct() {

    // let item = document.getElementsByClassName("shopping-cart")
    // let $item = $(".shopping-cart")
    // if ($item.hasClass("active")) {
    //     $item.removeClass("active");
    //
    // }
    //
    // $(".shopping-cart").each(function() {
    //     let delay = $(this).index() * 50 + 'ms';
    //     $(this).css({
    //         '-webkit-transition-delay': delay,
    //         '-moz-transition-delay': delay,
    //         '-o-transition-delay': delay,
    //         'transition-delay': delay
    //     });
    //
    //     $(".icon-basket-1").click(function(e) {
    //         e.stopPropagation()
    //         $(".shopping-cart").toggleClass("active")
    //     })
    // });
    getData("/cart-items").then(productList =>{
        getData("/cart").then(numberProductCart => {
            let productListJson = JSON.stringify(productList);
            let productParse = JSON.parse(productListJson);

            let numberInCart = JSON.parse(JSON.stringify(numberProductCart));

            let sum = sumPrice(productParse);
            console.log(numberInCart)
            $("#numberProdDrop").html(numberInCart);
            $("#sum-cart").html("$" + sum);
            for (let prod in productParse){
                $("#li-cart").remove()
            }
            console.log(productParse)
            for (let i = 0; i < productParse.length; i++) {

                $("#product-cart").append(buildItemsInCart(productParse, i))





                //<li class="clearfix" id="li-cart"
                // $("#li-cart").append("<img id=\"img-to-cart\" th:src=\"/static/img/product_" +parseInt(productParse[i]["id"])+".jpg\">")
                // $("#li-cart").append("<span class=\"item-name\">"+ productParse[i]['name'] +"</span>")
                // $("#li-cart").append("<span class=\"item-detail\">"+ productParse[i]["supplier"] +"</span>")
                // $("#li-cart").append("<span class=\"item-price\">"+ productParse[i]["defaultPrice"] +"</span>")
                // $("#li-cart").append("<span class=\"quantity\">"+ productParse[i]["quantity"] +"</span></li>")



                // '<li class="clearfix" id="li-cart">' +
                //     '<img id="img-to-cart" th:src="/static/img/product_'+parseInt(productParse[i]["id"])+'.jpg">' +
                //     '<span class="item-name">'+ productParse[i]['name'] +'</span>' +
                //     '<span class="item-detail">'+ productParse[i]["supplier"] +'</span>' +
                //     '<span class="item-price">'+ productParse[i]["defaultPrice"] +'</span>' +
                //     '<span class="quantity">'+ productParse[i]["quantity"] +'</span>' +
                //     '</li>'

            }
        }, err => console.log(err));
    });

}


    getData("/cart-items").then(productList =>{
        // let productListJson = JSON.parse(productList)
        let sum = sumPrice(productList)
        makeDropCart()
        addSumPrice(sum)
        addItemDrop(productList)
    })



function setVariableShopHeader(sum, numberProd, productList){
    $("#numberProdDrop").innerText = numberProd;
    $("#sum-cart").innerHTML = sum
    $("#product-cart").each(productList => {
        buildItemsInCart(productList)
    })
}

function makeDropCart(productsInCart, sum, numberProd) {
    let basket = document.getElementById("dropCart");
    basket.insertAdjacentHTML("beforeend", buildDropDownBasket());
    let add = document.getElementById("product-cart");
    for (let i = 0; i <= productsInCart; i++) {
        add.insertAdjacentHTML("beforeend", buildItemsInCart(productsInCart[i]));
    }
}

function addSumPrice(sumPrice){
    let sum = document.getElementById("sum-cart");
    sum.innerHTML = sumPrice;
}

function addItemDrop(productsInCart){
    let add = document.getElementById("product-cart");
    for (let i = 0; i <= productsInCart; i++) {
        add.insertAdjacentHTML("beforeend", buildItemsInCart(productsInCart[i]));

    }
}

function sumPrice(productList) {
    let count = 0;

    for (let i = 0; i < productList.length; i++) {
        count = Math.ceil(count + parseInt(productList[i]["defaultPrice"]) * parseInt(productList[i]["quantity"]));
    }
    console.log(count)
    return count.toString();
}



function buildDropDownBasket(sum, numberProd) {
    console.log()
    return ''
    // return '<div class="shopping-cart">' +
    //             '<div class="shopping-cart-header">' +
    //                 '<i id="basket-i" class="icon-basket-1"></i><span class="badge">'+ (parseInt(numberProd) + 1).toString() +'</span>' +
    //                 '<div class="shopping-cart-total">' +
    //                     '<span class="Lighter-text">Total:</span>' +
    //                     '<span class="main-color-text total" id="sum-cart">'+ sum +'</span>' +
    //                 '</div>' +
    //             '</div>' +
    //     '<ul class="shopping-cart-items" id="product-cart"></ul>' +
    //     '<a href="/toConfirm" class="button">Checkout<i id="button-confirm" class="fa fa-chevron-right"></i></a>' +
    //     '</div>' +
    //     '</div>'

}

function buildItemsInCart(product, i) {
    return '<li class="clearfix" id="li-cart">' +
        '<img id="img-to-cart" src="/static/img/product_'+parseInt(product[i]["id"])+'.jpg">' +
        '<span class="item-name">'+ product[i]['name'] +'</span>' +
        '<span class="item-detail">'+ product[i]["supplier"] +'</span>' +
        '<span class="item-price">Price $'+ product[i]["defaultPrice"] +'</span>' +
        '<span class="quantity">Quan:'+ product[i]["quantity"] +'</span>' +
        '</li>'
}

async function getData(url) {
    let error = 'error'
    let response = await fetch(url, {
        method: "GET",
    })
    if (response.status == 200) {
        let data = await response.json()
        console.log(data)
        return data
    } else {
        return error
    }
}