  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="categoryBox">
    <a href="<c:url value='infoApp?idapp=${appTopGame.id}' />">
            <div class="categoryLabelText"><fmt:message key="top_game"/></div>
        <div class="ImgApp"><img src=${empty appTopGame.imagePath ? "Pictures/des.png"  : appTopMulti.imagePath}></div>
        <div class="rightMiniBox">
            <div class="TitleApp">${appTopGame.name}</div>
            <div class="CategoryApp"> ${appTopGame.category}</div>
        </div>
        <div class="descriApp"> ${appTopGame.description}</div>
        <div class="bottomMiniBox">
            <span class="priceApp">${appTopGame.price} &euro;</span>
            <span class="buttonGetIT"><form action="addToCart" method="POST">
                    <input type="hidden" name="appToAddId" value="${appTopGame.id}">
                    <input type="submit" class="btn btn-small btn-primary" name="submitApp" value="Get It!">
                </form></span></div>
        </a>
    </div>
    <div class="categoryBox">
         <a href="<c:url value='infoApp?idapp=${appTopTheme.id}' />">
            <div class="categoryLabelText"><fmt:message key="top_theme"/></div>
        <div class="ImgApp"><img src=${empty appTopTheme.imagePath ? "Pictures/des.png"  : appTopMulti.imagePath}></div>
        <div class="rightMiniBox">
            <div class="TitleApp">${appTopTheme.name}</div>
            <div class="CategoryApp"> ${appTopTheme.category}</div>
        </div>
        <div class="descriApp"> ${appTopTheme.description}</div>
        <div class="bottomMiniBox">
            <span class="priceApp">${appTopTheme.price} &euro;</span>
            <span class="buttonGetIT"><form action="addToCart" method="POST">
                    <input type="hidden" name="appToAddId" value="${appTopMulti.id}">
                    <input type="submit" class="btn btn-small btn-primary" name="submitApp" value="Get It!">
                </form></span></div>
        </a>
    </div>
    <div class="categoryBox">
         <a href="<c:url value='infoApp?idapp=${appTopAccessoire.id}' />">
            <div class="categoryLabelText"><fmt:message key="top_accessory"/></div>
        <div class="ImgApp"><img src=${empty appTopAccessoire.imagePath ? "Pictures/des.png"  : appTopMulti.imagePath}></div>
        <div class="rightMiniBox">
            <div class="TitleApp">${appTopAccessoire.name}</div>
            <div class="CategoryApp"> ${appTopAccessoire.category}</div>
        </div>
        <div class="descriApp"> ${appTopAccessoire.description}</div>
        <div class="bottomMiniBox">
            <span class="priceApp">${appTopAccessoire.price} &euro;</span>
            <span class="buttonGetIT"><form action="addToCart" method="POST">
                    <input type="hidden" name="appToAddId" value="${appTopAccessoire.id}">
                    <input type="submit" class="btn btn-small btn-primary" name="submitApp" value="Get It!">
                </form></span></div>
        </a>
    </div>
    <div class="categoryBox">
     <a href="<c:url value='infoApp?idapp=${appTopMulti.id}' />">
            <div class="categoryLabelText"><fmt:message key="top_multimedia"/></div>
            
            <div class="ImgApp"><img src=${empty appTopMulti.imagePath ? "Pictures/des.png"  : appTopMulti.imagePath}></div>
        <div class="rightMiniBox">
            <div class="TitleApp">${appTopMulti.name}</div>
            <div class="CategoryApp"> ${appTopMulti.category}</div>
        </div>
        <div class="descriApp"> ${appTopMulti.description}</div>
        <div class="bottomMiniBox">
            <span class="priceApp">${appTopMulti.price} &euro;</span>
            <span class="buttonGetIT"><form action="addToCart" method="POST">
                    <input type="hidden" name="appToAddId" value="${appTopMulti.id}">
                    <input type="submit" class="btn btn-small btn-primary" name="submitApp" value="Get It!">
                </form></span></div>
        </a>
    </div>