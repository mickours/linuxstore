<div>
    <div class="pictureApp">
       <img width="128px" height="128px" src=${empty app.imagePath ? "Pictures/des.png"  : app.imagePath}>
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
        <a href="${app.filePath}">sdfdrgrg </a>
    </div>
</div>
