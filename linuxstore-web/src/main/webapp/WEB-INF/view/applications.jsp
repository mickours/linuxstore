<c:forEach var="app" items="${appList}" varStatus="iter">
    <div class="categoryMiniBox">
         <a href="<c:url value='infoApp?idapp=${app.id}' />">
             <div class="ImgApp"><img width="48px" height="48px" src=${empty app.imagePath ? "Pictures/des.png"  : app.imagePath}></div>
        <div class="rightMiniBox">
            <div class="TitleApp">${app.name}</div>
            <div class="CategoryApp"> ${app.category}</div>
        </div>
        <div class="descriApp"> ${app.description}</div>
        <div class="bottomMiniBox">
            <span class="priceApp">${app.price} &euro;</span>
            <span class="buttonGetIT"><form action="addToCart" method="POST">
                    <input type="hidden" name="appToAddId" value="${app.id}">
                    <input type="submit" name="submitApp" value="Get It!">
                </form></span></div>
         </a>
    </div>
</c:forEach>
