
// export  let dataHandler = {
   function getBasket(){
    let response = getData("/basket/");
    return response;
    }
function sendProductToCart(productId) {
        let bodyContent = {
            "productId": productId
        };
        postData('/cart/', bodyContent);
    }
    function getResponse(url) {
        let response = getData();
        return response;
    }


async function getData(url) {
    let error = 'error'
    let response = await fetch(url, {
        method: "GET",
    })
    if (response.status == 200) {
        let data = response.json()
        return data
    } else {
        return error
    }
}

async function postData(url, bodyContent) {
    let response = await fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body_content),
    })
}

function deleteData(url) {
    let response = fetch(url, {
        method: "DELETE"
    })
}

function putData(url, bodyContent) {
    let response = fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body_content),
    })
}