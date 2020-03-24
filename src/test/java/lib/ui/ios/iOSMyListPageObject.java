package lib.ui.ios;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";
        //ARTICLE_TITLE_IN_LIST_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='{TITLE_TEXT}']";
        CLOSE_BUTTON_SYNC_POPUP = "id:Close";
    }

    public iOSMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
