<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${applicationsOwned.size() > 0}">
        <div class="titleCart"><h2><fmt:message key='your_applications'/></h2></div>
        <table class="cartTable">
            <tr>
                <th class="thApplication"><fmt:message key='application'/></th>
            <th><fmt:message key='state'/></th>
            <th><fmt:message key='actions'/></th>
            </tr>
            <c:forEach var="app" items="${applicationsOwned}" varStatus="iter">
                <tr class="${((iter.index % 2) == 1) ? 'lightBlue' : 'white'}">

                    <td>
                        <a href="<c:url value='infoApp?idapp=${app.id}' />">
                            <div class="nameImageAppTab">
                                <img width="32px" height="32px" src=${empty app.imagePath ? "Pictures/des.png"  : app.imagePath}>
                                <span> ${app.name}</span>
                            </div>
                        </a>
                    </td>
                    <td>
                <c:choose>
                    <c:when test="${app.validated==true}">
                        <fmt:message key='online'/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key='waiting_for_validation'/>
                    </c:otherwise>
                </c:choose>
                </td>
                <td>
                <c:choose>
                    <c:when test="${app.validated==true}">
                        <a class="button" href="${app.filePath}"><fmt:message key='download'/></a>
                    </c:when>
                    <c:otherwise>

                    </c:otherwise>
                </c:choose>


                </td>
                </tr>

            </c:forEach>
        </table>
    </c:when>
</c:choose>
<c:choose>
    <c:when test="${applicationsBuyed.size() > 0}">
        <div class="titleCart"><h2><fmt:message key='your_purchase'/></h2></div>
        <table class="cartTable">
            <tr>
                <th class="thApplication"><fmt:message key='application'/></th>
            <th><fmt:message key='actions'/></th>
            </tr>
            <c:forEach var="app" items="${applicationsBuyed}" varStatus="iter">
                <tr class="${((iter.index % 2) == 1) ? 'lightBlue' : 'white'}">

                    <td>
                        <a href="<c:url value='infoApp?idapp=${app.id}' />">
                            <div class="nameImageAppTab">
                                <img width="32px" height="32px" src=${empty app.imagePath ? "Pictures/des.png"  : app.imagePath}>
                                <span> ${app.name}</span>
                            </div>
                        </a>
                    </td>
                    <td>
                        <a class="button" href="${app.filePath}"><fmt:message key='download'/></a>
                    </td>
                </tr>

            </c:forEach>
        </table>
    </c:when>
</c:choose>
<c:choose>
    <c:when test="${applicationsOwned.size() == 0 && applicationsBuyed.size() == 0}">
        <h1><fmt:message key='your_applications'/></h1>
        <fmt:message key='you_have_no_applications'/>
    </c:when>
</c:choose>