addProductToCart();

function addProductToCart() {
  let buttons = document.getElementsByClassName("btn-success");
  for (let button of buttons) {
    button.addEventListener("click", async function () {
      console.debug("listener works");
      let productId = button.getAttribute("product-id");
      console.debug("get attribute (product id) works: " + productId);
      // await addProductToCartRequest({productId: productId});
    })
  }
}

// async function addProductToCartRequest(productId) {
//   return await apiPost('/cart', productId);
// }
//
// async function apiPost(url, payload) {
//   let response = await fetch(url, {
//     method: "POST",
//     headers: {
//       'Content-Type': 'application/json'
//     },
//     body: payload
//   });
//   if (response.status === 200) {
//     // ? .json() ?
//     return response;
//   }
// }