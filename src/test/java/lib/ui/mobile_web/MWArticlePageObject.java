package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "css: #section_0 h1";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_AND_TO_MY_LIST_BUTTON = "css:#page-actions-watch li#ca-watch button";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        TITLE_IN_ARTICLE = "xpath://android.widget.TextView[@resource-id=\"org.wikipedia:id/view_page_title_text\"]";
        MY_LIST_NAME_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_container']//*[@text='{LIST_NAME}']";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions-watch li#ca-watch.mw-ui-icon-wikimedia-unStar-progressive watched button";
    }
    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
