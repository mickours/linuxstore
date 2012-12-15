<div class="CartPage">
    <div class="titleCart"><h1>Your Cart</h1></div>
     <div class="searchBarCart">
                <span class="titleSearchCart">Search : </span>
                <span class="fieldSearchCart">
                     <form>
                        <input type="text" name="search">
                     </form></span>
    </div>
<table class="cartTable">
    <tr>
        <th class="thApplication">Application</th>
        <th>Price</th>
        <th>Remove</th>
    </tr>
    <c:forEach var="app" items="${cart.appList}" varStatus="iter">
        <tr class="${((iter.index % 2) == 1) ? 'lightBlue' : 'white'}">
            <td>
                <div class="nameImageAppTab">
                    <span><img width="8%" src="${initParam.appImagePath}${app.imagePath}.png"
                               alt="${app.name}"></span>
                    <span> ${app.name}</span>
                </div>
            </td>
            <td>${app.price}&euro;</td>
            <td>
                <form action="removeFromCart" method="POST">
                    <input src="Pictures/delete.png"
                           alt="supprimer ${app.name} de votre panier"
                           type="image"
                           value="submit">
                    <input type="hidden" name="appToRemoveId" value="${app.id}">
                </form>
            </td>
        </tr>

    </c:forEach>
</table>
                
     <div class="buttonContinueShopping">
       <form action="ContinuPurchase" method="POST">
                    <input type="button"
                           value="Continue shopping">
                </form>  
    </div>
     <div class="buttonProceedCheckoutg">
       <form action="ContinuPurchase" method="POST">
                    <input type="button"
                           value="Proceed to checkout">
                </form>  
    </div>
</div>