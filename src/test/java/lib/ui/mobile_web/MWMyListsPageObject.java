package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]";
        //ARTICLE_TITLE_IN_LIST_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='{TITLE_TEXT}']";
        CLOSE_BUTTON_SYNC_POPUP = "id:Close";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]/../../div[contains(@class,'watched')]";
    }


    public MWMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
