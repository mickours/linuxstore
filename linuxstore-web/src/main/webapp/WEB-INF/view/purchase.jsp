
<h1><fmt:message key="purchase"/></h1>
<div id="form_container">
    <form  method="post" action="purchase">

        <ul >

            <li>
                <label class="description" for="element_4"><fmt:message key="purchase_method"/> </label>
                <span>
                    <input id="element_4_1" name="element_4" class="element radio" type="radio" value="1" />
                    <label class="choice" for="element_4_1">VISA</label>
                    <input id="element_4_2" name="element_4" class="element radio" type="radio" value="2" />
                    <label class="choice" for="element_4_2">CB</label>
                    <input id="element_4_3" name="element_4" class="element radio" type="radio" value="3" />
                    <label class="choice" for="element_4_3">MasterCard</label>

                </span>
            </li>
            <li>
                <label class="description" for="element_1"><fmt:message key="card_number"/> </label>
                <div>
                    <input required id="element_1" name="element_1" class="element text medium" type="text" size="16" maxlength="16" value=""/>
                </div>
            </li>
            <li>
                <label class="description" for="element_2"><fmt:message key="expiration_date"/></label>
                <span>
                    <input required id="element_2_1" name="month" class="element text" size="2" maxlength="2" value="" type="text"> /
                    <label for="element_2_1">MM</label>
                </span>
                <span>
                    <input required id="element_2_3" name="year" class="element text" size="2" maxlength="2" value="" type="text">
                    <label for="element_2_3">AA</label>
                </span>
            </li>
            <li id="li_3" >
                <label class="description" for="element_3"><fmt:message key="security_code"/> </label>
                <div>
                    <input required id="element_3" name="securityCode" class="element text small" type="text" size="3" maxlength="3" value=""/>
                </div>
            </li>

            <li class="buttons">
                <input hidden name="ok" value="ok" />
                <input id="saveForm" class="button btn btn-small btn-primary" type="submit" name="submit" value="Submit" />
            </li>
        </ul>
    </form>
</div>