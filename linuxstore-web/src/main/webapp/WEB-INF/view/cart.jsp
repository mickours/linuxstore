<div class="CartPage">
    <div class="titleCart"><h1><fmt:message key="your_cart"/></h1></div>
    <table class="cartTable table-hover">
        <thead>
        <tr>
            <th class="thApplication"><fmt:message key="selected_app"/></th>
        <th><fmt:message key="price"/></th>
        <th><fmt:message key="remove"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="app" items="${cart.appList}" varStatus="iter">
            <tr class="${((iter.index % 2) == 1) ? 'lightBlue' : 'white'}">

                <td>
                    <a href="<c:url value='infoApp?idapp=${app.id}' />">
                    <div class="nameImageAppTab">
                        <img width="32px" height="32px" src=${empty app.imagePath ? "Pictures/des.png"  : app.imagePath}>
                        <span> ${app.name}</span>
                    </div>
                    </a>
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
        </tbody>

        </c:forEach>
    </table>

    <div class="buttonContinueShopping">
        <form  action="applications">
            <input class="button btn btn-small btn-primary" type="submit"
                   value="<fmt:message key='continu'/>">
        </form>
    </div>

    <c:choose>
        <c:when test="${cart.numberOfApp != 0}">
            <div class="buttonProceedCheckoutg">
                <form  action="purchase" >
                    <input class="button btn btn-small btn-primary" type="submit"
                           value="<fmt:message key='purchase'/>">
                </form>
            </div>
        </c:when>
        <c:otherwise>
        </c:otherwise>
    </c:choose>


</div>