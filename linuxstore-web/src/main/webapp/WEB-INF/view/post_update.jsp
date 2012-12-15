<div>
    <div class="onglets">
        <div class="onglet_n onglet"><a href="post_application"><fmt:message key="post_application"/></a></div>
        <div class="onglet_y onglet"><a href="post_update"><fmt:message key="post_update"/></a></div>
    </div>
    <div class="contenu">
        <form action="PostMyUpdate" title="postUpdate">
            <table>
                <tr>
                    <td class="labelTd"><label for="namePostUpdate"> Title:</label></td>
                    <td class="fieldTd"><input type='text' name='name' id="namePostUpdate"></td>
                    <td class="labelFileTd"><label for="filePostUpdate" id="labelFileUpdate">File: </label></td>
                    <td class="fieldTd"> <input type='file' name='file' id="filePostUpdate"></td>
                </tr>
                <tr>
                    <td class="labelTd"> <label for="descPostUpdate">Description:</label></td>
                    <td class="fieldTd"><textarea name='description' id="descPostUpdate"></textarea></td>
                    <td class="labelFileTd"> </td>
                    <td class="fieldTd"></td>
                </tr>
                <tr>
                    <td class="labelTd"> <label for="pricePostUpdate">Price: </label></td>
                    <td class="fieldTd"> <input type='text' name='price' id="pricePostUpdate"></td>
                    <td class="labelFileTd"> <label for="fileIconePostUpdate" id="labelFileIconeUpdate">Icone: </label></td>
                    <td class="fieldTd"><input type='file' name='fileIcone' id="fileIconePostUpdate"></td>
                </tr>
                <tr>
                    <td class="labelTd">  <label for="categPostUpdate">Catégorie : </label></td>
                    <td class="fieldTd"> <select name='category' id="categPostUpdate">
                            <c:forEach var="category" items="${categories}" varStatus="iter">
                                <option value="${category}">${category}</option>
                            </c:forEach>
                        </select></td>
                    <td class="labelFileTd"> </td>
                    <td class="fieldTd"></td>
                </tr>
            </table>

                            <input type='submit' value="submit "id="submitPostUpdate"><br/>
        </form>
    </div>
</div>

