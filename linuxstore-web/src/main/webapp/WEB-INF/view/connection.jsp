<h1><fmt:message key='connect'/></h1>

<div>
    <form action="connection" method="POST">
        <c:choose>
            <c:when test="${empty errorMessage}">
            </c:when>
            <c:otherwise>
                <div id="errorMessage">
                    <!--<fmt:message key="error"/> :--> 
                    <fmt:message key="${errorMessage}"/>.
                </div>
            </c:otherwise>
        </c:choose>
        <table id="connection">
            <tr>
                <td><fmt:message key='user'/> :</td>
            <td><input type="text" class="input-small" placeholder="Email" name="user" required></td>
            </tr>
            <tr>
                <td><fmt:message key='password'/> :</td>
            <td><input type="password" class="input-small" placeholder="Password" name="password" required></td>
            </tr>
            <tr>
                <td></td>
                <td><input class="button_connexion btn" type="submit" value="<fmt:message key='button_connexion'/>" ></td>
            </tr>
        </table>
    </form>
    <p><fmt:message key='link_inscription1'/> <a href="inscription"><fmt:message key='link_inscription2'/></a> <fmt:message key='link_inscription3'/></p>
</div>

