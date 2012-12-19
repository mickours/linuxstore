<h1><fmt:message key='register'/></h1>
<form action="inscription" method="POST">
    <c:choose>
        <c:when test="${empty errorMessage}">
        </c:when>
        <c:otherwise>
            <div id="errorMessage">
                <fmt:message key="error"/> : <fmt:message key="${errorMessage}"/>.
            </div>
        </c:otherwise>
    </c:choose>
            <table  id="connection" >
        <tr>
            <td><fmt:message key='mail'/> :</td>
        <td><input type="text" class="input-medium" placeholder="Email" name="user" required></td>
        </tr>
        <tr>
            <td><fmt:message key='password'/> :</td>
        <td><input type="password" class="input-medium" placeholder="Password" name="password" required></td>
        </tr>
        <tr>
            <td><fmt:message key='re_password'/> :</td>
        <td><input type="password" class="input-medium" placeholder="Password" name="password2" required></td>
        </tr>
        <tr>
            <td></td>
            <td><input class="button_connexion btn" type="submit" value="<fmt:message key='button_inscription'/>"></td>
        </tr>
    </table>
</form>
<c:choose>
    <c:when test="${empty successMessage}">
    </c:when>
    <c:otherwise>
        <div id="successMessage">
        <fmt:message key="${successMessage}"/>.
        </div>
    </c:otherwise>
</c:choose>

