<div class="CartPage">
    <div class="titleCart"><h1>Your Cart</h1></div>
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
                               <img width="32px" height="32px" src=${empty app.imagePath ? "Pictures/des.png"  : app.imagePath}>
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
       <form action="/applications">
                    <input type="button"
                           value="Continue shopping">
                </form>  
    </div>
     <div class="buttonProceedCheckoutg">
       <form action="purchase" >
                    <input type="button"
                           value="Proceed to checkout">
                </form>  
    </div>
</div>