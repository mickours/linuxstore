<c:forEach var="app" items="${appList}" varStatus="iter">
    <div class="categoryMiniBox">
        <div class="ImgApp"><img src="Pictures/des.png"></div>
        <div class="rightMiniBox">
            <div class="TitleApp">${app.name}</div>
            <div class="CategoryApp"> ${app.category}</div>
        </div>
        <div class="descriApp"> ${app.description}</div>
        <div class="bottomMiniBox">
            <span class="priceApp">${app.price}</span>
            <span class="buttonGetIT"><form action="addToCart" method="POST">
                    <input type="hidden" name="appToAddId" value="${app.id}">
                    <input type="submit" name="submitApp" value="Get It!">
                </form></span></div>
    </div>
</c:forEach>
