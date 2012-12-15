<div>
    <div class="pictureApp">
        <img src="Pictures/Cardgame.png">
    </div>
    <div class="titleApp">
       ${app.name}
    </div>
    <div class="descInfoApp">
        ${app.description}
    </div>
        <div class="priceInfoApp">${app.price}&euro;</div>
    <div  class="buttonGetITInfoApp">
       <form action="addToCart" method="POST">
                    <input type="hidden" name="appToAddId" value="${app.id}">
                    <input type="submit" name="submitApp" value="Get It!">
                </form> 
    </div>
    <div  class="buttonDownloadITInfoApp">
       <form action="download" method="POST">
                    <input type="hidden" name="appToAddId" value="${app.id}">
                    <input type="submit" name="submitApp" value="Download It!">
                </form> 
    </div>
</div>
