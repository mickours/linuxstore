<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="CartPage">
    <div class="titleCart"><h1><fmt:message key="your_cart"/></h1></div>
<table class="cartTable">
    <tr>
        <th class="thApplication"><fmt:message key="selected_app"/></th>
<th><fmt:message key="price"/></th>
        <th><fmt:message key="remove"/></th>
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
         <form  action="applications">
                    <input class="button" type="submit"
                           value="<fmt:message key='continu'/>">
                </form>  
    </div>
     <div class="buttonProceedCheckoutg">
       <form  action="purchase" >
                    <input class="button" type="submit"
                           value="<fmt:message key='purchase'/>">
                </form>  
    </div>
</div>