package tests;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class ArticleTests extends CoreTestCase
{
    @Test
    public void testCompareArticleTitle()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                 article_title
        );
    }
    @Test
    public void testSwipeArticle()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);;
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();


    }
//    @Test
//    public void checkTitleInTopic()  {
//        SearchPageObject SearchPageObject = new SearchPageObject(driver);
//
//        SearchPageObject.initSearchInput();
//        SearchPageObject.typeSearchLine("Java");
//        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
//
//        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
//        ArticlePageObject.presentTitleInArticle();
//    }
    private static final String name_of_folder = "Learning programming";
    @Test
    public void testCheckDeletedSecondTopicAndCheckTitleFirstTopic() throws InterruptedException
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        final String title = ArticlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()){
        ArticlePageObject.addArticleToMyList(name_of_folder);
    }else{
        ArticlePageObject.addArticlesToMySaved();
    }
        ArticlePageObject.closeArticle();

        if (Platform.getInstance().isAndroid())
        {
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("JavaScript");
            SearchPageObject.clickByArticleWithSubstring("Programming language");
            ArticlePageObject.addNewArticleToMyList(name_of_folder);
            ArticlePageObject.closeArticle();
        }else
            {
                SearchPageObject.clickByArticleWithSubstring("Programming language");
                ArticlePageObject.addArticlesToMySaved();
                ArticlePageObject.closeArticle();
                SearchPageObject.clickCancelSearch();
            }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isIos())
        {
            MyListPageObject.clickCloseSyncPopUp();
        }

        if (Platform.getInstance().isAndroid()){
            MyListPageObject.openFolderByName(name_of_folder);
        }
        String article_to_delete = "JavaScript";
        String article_to_save = "Java";

        MyListPageObject.swipeByArticleToDelete(article_to_delete);
        MyListPageObject.waitForArticleToAppearByTitle(article_to_save);

    }
}
