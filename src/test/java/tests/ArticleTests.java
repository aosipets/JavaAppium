package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

        assertEquals(
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
    private static final String
            login = "kosipec",
            password = "s1g2l936u";
    @Test
    public void testCheckDeletedSecondTopicAndCheckTitleFirstTopic() throws InterruptedException
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        final String title = ArticlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
            ArticlePageObject.closeArticle();
        }else if (Platform.getInstance().isIos()){
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeArticle();
        }else{
            ArticlePageObject.addArticlesToMySaved();
        }
        if(Platform.getInstance().isMW())
        {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login,password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            assertEquals("We are not on the same page after login",
                    title,
                    ArticlePageObject.getArticleTitle());

            ArticlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isAndroid())
        {
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("JavaScript");
            SearchPageObject.clickByArticleWithSubstring("Programming language");
            ArticlePageObject.addNewArticleToMyList(name_of_folder);
            ArticlePageObject.closeArticle();
        }else if (Platform.getInstance().isIos()){
            SearchPageObject.clickByArticleWithSubstring("Programming language");
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeArticle();
            SearchPageObject.clickCancelSearch();
        }else{
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("JavaScript");
            SearchPageObject.clickByArticleWithSubstring("Programming language");
            ArticlePageObject.addArticlesToMySaved();

        }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
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
