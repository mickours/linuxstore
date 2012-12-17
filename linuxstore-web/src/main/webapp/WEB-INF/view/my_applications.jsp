        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1><fmt:message key='my_applications'/></h1>
   <div class="titleCart"><h1><fmt:message key='your_applications'/></h1></div>
<table class="cartTable">
    <tr>
        <th class="thApplication"><fmt:message key='application'/></th>
        <th><fmt:message key='price'/></th>
        <th><fmt:message key='state'/></th>
    </tr>
    <c:forEach var="app" items="${applications}" varStatus="iter">
        <tr class="${((iter.index % 2) == 1) ? 'lightBlue' : 'white'}">
            <td>
                <div class="nameImageAppTab">
                               <img width="32px" height="32px" src=${empty app.imagePath ? "Pictures/des.png"  : app.imagePath}>
                               <span> ${app.name}</span>
                </div>
            </td>
            <td>${app.price}&euro;</td>
            <td>
            <c:choose>
            <c:when test="${app.validated}">
                <fmt:message key='online'/>
            </c:when>
            <c:otherwise>
                    <fmt:message key='waiting_for_validation'/>
            </c:otherwise>
        </c:choose>
            </td>
        </tr>

    </c:forEach>
</table>