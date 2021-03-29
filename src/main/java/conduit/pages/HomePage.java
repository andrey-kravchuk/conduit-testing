package conduit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static org.testng.FileAssert.fail;

public class HomePage extends PageObject{
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//*[contains(@ui-sref, 'app.editor')]")
    private WebElement newArticleLink;

    @FindBy(xpath="//*[contains(@class, 'nav-link') and contains(text(), 'Global Feed')]")
    private WebElement globalFeed;

    @FindBy(xpath="//*[contains(@class, 'sidebar')]")
    private WebElement popularTagsList;

    @FindBy(xpath="//*[contains(@class, 'row')]")
    private WebElement articleList;

    @FindBy(xpath="/html/body/div/div/div/div[2]/div/div[1]/article-list/article-preview[1]/div/a")
    private WebElement testArticle;


    public void clickNewArticleLink() {
        newArticleLink.click();
    }

    public HomePage clickGlobalFeed() throws InterruptedException {
        globalFeed.click();
        Thread.sleep(5000);
        return this;
    }

    public HomePage clickOnPopularTag(String tagName) throws InterruptedException {
        this.popularTagsList.findElement(By.xpath("//*[contains(@class, 'sidebar')]//a[contains(@class, 'tag-pill') and contains(text(), '" + tagName + "')]")).click();
        Thread.sleep(5000);
        return this;
    }

    public WebElement articleRow(String articleTitle) {
        return this.articleList.findElement(By.xpath("//*[contains(@class, 'ng-binding') and contains(text(), '" + articleTitle + "')]"));
    }

    public HomePage checkArticleRowPresent(String articleTitle) {
        Assert.assertEquals(articleRow(articleTitle).isDisplayed(), true);
        return this;
    }

    public HomePage checkArticleRowIsNotPresent(String articleTitle) {
        try {
            articleRow(articleTitle).isDisplayed();
            fail("Element should not have been displayed but it was!");
        }
        catch (NoSuchElementException e) {}
        return this;
    }
}
