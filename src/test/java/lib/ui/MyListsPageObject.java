package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject{

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            ARTICLE_TITLE_IN_LIST_TPL,
            CLOSE_BUTTON_SYNC_POPUP,
            REMOVE_FROM_SAVED_BUTTON;

    /*TEMPLATES METHODS*/
    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    private static String getSaveArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    private static String getArticleTitleXpathByName(String title_article_in_list)
    {
        return ARTICLE_TITLE_IN_LIST_TPL.replace("{TITLE_TEXT}", title_article_in_list);
    }
    /*TEMPLATES METHODS*/

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name"+ name_of_folder,
                5
        );
    }
    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getSaveArticleXpathByTitle(article_title);
        this.waitForElementPresent(article_xpath, "Cannot find save article by title" + article_title, 15);
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getSaveArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(article_xpath, "Saved article still present with title" + article_title, 15);
    }
    public void swipeByArticleToDelete(String article_title)
    {

            this.waitForArticleToAppearByTitle(article_title);
            String article_xpath = getSaveArticleXpathByTitle(article_title);
        if (Platform.getInstance().isIos() || Platform.getInstance().isAndroid()) {
            this.swipedElementToLeft(
                    article_xpath,
                    "Cannot delete saved article"
            );
        }else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(remove_locator,"Cannot click button to remove article from saved",10);
        }

        if(Platform.getInstance().isIos()){
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }
        this.waitForArticleToDisappearByTitle(article_title);
        if (Platform.getInstance().isMW()){
            driver.navigate().refresh();
        }
    }
    public void checkArticleTitleInMyListAndClick(String title_article_in_list)
    {
        String title_article_in_list_xpath = getArticleTitleXpathByName(title_article_in_list);
        this.waitForElementAndClick(title_article_in_list_xpath,"Cannot find title article in list " + title_article_in_list_xpath,5);

    }
    public void clickCloseSyncPopUp()
    {
        this.waitForElementAndClick(CLOSE_BUTTON_SYNC_POPUP, "Cannot find and click cancel search button",5);
    }
    private static String getRemoveButtonByTitle(String article_title)
    {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}",article_title);
    }

}
