<div>
    <div class="pictureApp">
        <img src="Pictures/Cardgame.png">
    </div>
    <div class="titleApp">
        Name of application
    </div>
    <div class="descInfoApp">
        Surveillez votre r�seau efficacement et agissez en temps r�el. 
        Gr�ce � son syst�me d&rsquo;alarme performant, cette application vous informe
        en tant direct en cas d&rsquo;�changes r�seaux que vous n&rsquo;avez pas 
        explicitement souhait�. Gr�ce � son outils de statistique puissant, 
        analysez quelles sont les applications sollicitant le plus votre 
        connexion internet.
    </div>
    <div class="priceInfoApp">99$</div>
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
