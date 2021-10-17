
// setInterval(() => {
//     getData("/cart").then(numberProductCart => {
//         addRealNumber(numberProductCart)
//     })
// }, 200)






function addProductToCart(id) {
    let element = document.getElementById(id)
    let productId = element.getAttribute("id")

    sendProductToCart(productId).then(response => {
        addRealNumber(response)
        addAlertDiv()
        setTimeout(function () {
            let empty = document.getElementById("alert-position");
            empty.remove();
        }, 1000);
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
    if (response.status == 200) {
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
        ' <strong></strong><p id="message">Product Added</p></strong> ' +
        '</div> '
}

function addRealNumber(cartNumber) {
    let element = document.getElementById("dropCart");
    let span = document.getElementById("badge-web");
    span.remove();
    const newSpan = document.createElement("span");
    newSpan.innerHTML = '<span class="badge" id="badge-web" th:text="${numProd}">'+ cartNumber[1] +'</span>'
    element.parentNode.appendChild(newSpan);


}
