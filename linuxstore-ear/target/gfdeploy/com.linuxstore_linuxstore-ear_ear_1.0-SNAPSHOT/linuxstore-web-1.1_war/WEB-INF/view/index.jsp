<c:forEach var="app" items="${appList}" varStatus="iter">    
<div class="categoryBox">
        <a href="#">
            <div class="categoryLabelText">Top nouveautés</div>
            <div class="TitleApp">${app.name}</div>
        </a>
    </div>
</c:forEach>
    <div class="categoryBox">
        <a href="#">
            <span class="categoryLabelText">Top thèmes</span>
        </a>
    </div>
    <div class="categoryBox">
        <a href="#">
            <span class="categoryLabelText">Top jeux</span>
        </a>
    </div>
    <div class="categoryBox">
        <a href="#">
            <span class="categoryLabelText">Top widgets</span>
        </a>
    </div>