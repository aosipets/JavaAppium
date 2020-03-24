package lib.ui.ios;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:view-in-browser-footer-link";
        OPTIONS_AND_TO_MY_LIST_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        TITLE_IN_ARTICLE = "xpath://android.widget.TextView[@resource-id=\"org.wikipedia:id/view_page_title_text\"]";
        MY_LIST_NAME_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_container']//*[@text='{LIST_NAME}']";
    }

    public iOSArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
