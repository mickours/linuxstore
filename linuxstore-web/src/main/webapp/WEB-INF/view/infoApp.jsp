<div>
    <div class="pictureApp">
       <img width="128px" height="128px" src=${empty app.imagePath ? "Pictures/des.png"  : app.imagePath}>
    </div>
    <div class="titleApp">
       ${app.name}
    </div>
    <div class="descInfoApp">
        <p>${app.description}</p>
        <p>Poster par ${app.owner.loginMail}</p>
    </div>
        <div class="priceInfoApp">${app.price}&euro;</div>
    <div  class="buttonGetITInfoApp">
       <form action="addToCart" method="POST">
                    <input type="hidden" name="appToAddId" value="${app.id}">
                    <input type="submit" name="submitApp" value="Get It!">
                </form> 
    </div>
    <div  class="buttonDownloadITInfoApp">
        <a class="button" href="${app.filePath}">Download</a>
    </div>
</div>
