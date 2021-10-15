// addProductToCart();
/*searchPhrase();*/

// function addProductToCart() {
//   let buttons = document.getElementsByClassName("btn-success");
//   for (let button of buttons) {
//     button.addEventListener("click", async function () {
//       let productId = button.getAttribute("product-id");
//       await addProductToCartRequest({productId: productId});
//     })
//   }
// }

async function addProductToCartRequest(productId) {
  return await apiPost('/#', productId);
}

async function apiPost(url, payload) {
  let response = await fetch(url, {
    headers: {
      'Content-Type': 'application/json'
    },
    body: payload,
    method: "POST"
  });
  if (response.status === 200) {
    // ? .json() ?
    return response;
  }
}

// function searchPhrase(){
//   let searchInputArea = document.getElementById("search-input");
//   let searchSubmitButton = document.getElementById("search-submit")
//
//   searchSubmitButton.addEventListener("click", function () {
//     let searchPhrase = "searchPhrase=" + searchInputArea.value;
//     apiPost("/search/", searchPhrase);
//   })
// }