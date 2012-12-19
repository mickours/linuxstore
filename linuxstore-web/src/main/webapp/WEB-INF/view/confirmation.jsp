<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p><fmt:message key="${confirmationMessage}"/></p>
<c:choose>
    <c:when test="${dealOk == true}">
        <p><fmt:message key="confirmation_1"/><a href="my_applications"><fmt:message key="here"/></a><fmt:message key="confirmation_2"/></p>
    </c:when>
</c:choose>