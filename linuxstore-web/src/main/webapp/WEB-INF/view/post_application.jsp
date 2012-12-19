<div>
    <c:choose>
            <c:when test="${empty errorMessage}">
            </c:when>
            <c:otherwise>
                <div id="errorMessage">
                    <!--<fmt:message key="error"/> :-->
                    ${errorMessage}
                </div>
            </c:otherwise>
    </c:choose>
    <div class="onglets">
        <div class="onglet_y onglet"><a href="post_application"><fmt:message key="post_application"/></a></div>
        <div class="onglet_n onglet"><a href="post_update"><fmt:message key="post_update"/></a></div>
    </div>
    <div class="contenu">
        <form action="post_application" enctype="multipart/form-data" method="post" title=<fmt:message key="post_application"/>>
            <table>
                <tr>
                    <td class="labelTd"><label for="namePostApp"><fmt:message key="title"/> :</label></td>
                    <td class="fieldTd"><input required type='text' name='name' id="namePostApp"></td>
                    <td class="labelFileTd"><label for="filePostApp" id="labelFile"><fmt:message key="file"/> :</label></td>
                    <td class="fieldTd"> <input required type='file' name='file' id="filePostApp">${fileTypes}</td>
                </tr>
                <tr>
                    <td class="labelTd"> <label for="descPostApp"><fmt:message key="description"/> :</label></td>
                    <td class="fieldTd"><textarea required name='description' id="descPostApp"></textarea></td>
                    <td class="labelFileTd"> </td>
                    <td class="fieldTd"></td>
                </tr>
                <tr>
                    <td class="labelTd"> <label for="pricePostApp"><fmt:message key="price"/> :</label></td>
                    <td class="fieldTd"> <input required type='text' name='price' id="pricePostApp"></td>
                    <td class="labelFileTd"> <label for="fileIconePostApp" id="labelFileIcone"><fmt:message key="icon"/> :</label></td>
                    <td class="fieldTd"><input type='file' name='icon' id="fileIconePostApp">${imgTypes}</td>
                </tr>
                <tr>
                    <td class="labelTd">  <label for="categPostApp"><fmt:message key="categories"/> :</label></td>
                    <td class="fieldTd">
                        <select name='category' id="categPostApp">
                            <c:forEach var="category" items="${categories}" varStatus="iter">
                                <option value="${category}"><fmt:message key="${category}" /></option>
                            </c:forEach>
                        </select></td>
                    <td class="labelFileTd"> </td>
                    <td class="fieldTd"></td>
                </tr>
            </table>

            <input type='submit' class="btn btn-small btn-primary" value="<fmt:message key='submit'/>" id="submitPostApp"><br/>
        </form>
    </div>
</div>

