package lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
        TITLE,
        FOOTER_ELEMENT,
        OPTION_BUTTON,
        OPTIONS_AND_TO_MY_LIST_BUTTON,
        ADD_TO_MY_LIST_OVERLAY,
        MY_LIST_NAME_INPUT,
        MY_LIST_OK_BUTTON,
        CLOSE_ARTICLE_BUTTON,
        TITLE_IN_ARTICLE,
        MY_LIST_NAME_TPL,
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON;



    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /*TEMPLATES METHODS*/
    private static String getFolderXpathByName(String name_of_folder)
    {
        return MY_LIST_NAME_TPL.replace("{LIST_NAME}", name_of_folder);
    }


    /*TEMPLATES METHODS*/


    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }
    public String  getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()){
        return title_element.getAttribute("text");
        }else if (Platform.getInstance().isIos()){
            return title_element.getAttribute("name");
        }else
            {
                return title_element.getText();
            }
    }
    public void swipeToFooter()
    {
        if(Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find the end of article",
                40
            );
        }else if (Platform.getInstance().isIos()){
            this.swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "cannot find the end element",
                    40);
        }else{this.swipeUpTillElementAppear(
                FOOTER_ELEMENT,
                "cannot find the end element",
                40);
        }
    }
    public void addArticleToMyList(String name_of_folder) throws InterruptedException
    {
        this.waitForElementAndClick(
                OPTION_BUTTON,
                "Cannot find button to open article option",
                5
        );
        Thread.sleep(2000);
        this.waitForElementAndClick(
                OPTIONS_AND_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                2
        );
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'got it' tip overlay",
                5
        );
        this.waitForElementForClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles to reading list",
                5
        );
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }
    public void closeArticle()
    {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X link",
                5
        );
    }
    public void presentTitleInArticle()
    {
        this.assertElementPresent(
                TITLE_IN_ARTICLE,
                "No title on screen article. Number of titles: ");
    }
    public void addNewArticleToMyList(String name_of_folder ) throws InterruptedException {
        waitForElementAndClick(
                OPTION_BUTTON,
                "Cannot find button to open article option",
                5
        );
        Thread.sleep(2000);
        waitForElementAndClick(
                OPTIONS_AND_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );
        Thread.sleep(2000);
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        waitForElementAndClick(
                folder_name_xpath,
                "Cannot find search string",
                10
        );

    }
    public  void addArticlesToMySaved()
    {

        if (Platform.getInstance().isMW()) {
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(OPTIONS_AND_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list",5);
    }
    public void removeArticleFromSavedIfItAdded(){
        if(this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    1);
            this.waitForElementPresent(OPTIONS_AND_TO_MY_LIST_BUTTON, "Cannot find button to add an article to saved list after removing it from this list before");
        }
    }

}
