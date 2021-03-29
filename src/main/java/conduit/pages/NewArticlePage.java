package conduit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class NewArticlePage extends PageObject{
    public NewArticlePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[contains(@placeholder, 'Article Title')]")
    private WebElement articleTitle;

    @FindBy(xpath = "//*[contains(@ng-model, '$ctrl.article.description')]")
    private WebElement articleDescription;

    @FindBy(xpath = "//*[contains(@placeholder, 'Write your article (in markdown)')]")
    private WebElement articleBody;

    @FindBy(xpath = "//*[contains(@placeholder, 'Enter tags')]")
    private WebElement tagInput;

    @FindBy(xpath = "//*[contains(@class, 'tag-list')]")
    private WebElement tagsList;

    @FindBy(xpath = "//*[contains(@class, 'btn') and contains(text(), 'Publish Article')]")
    private WebElement publishArticle;

    @FindBy(xpath = "html/body/div/app-header/nav/div/ul[2]/li[1]/a")
    private WebElement home;


    public NewArticlePage enterArticleTitle(String articleTitle) {
        this.articleTitle.clear();
        this.articleTitle.sendKeys(articleTitle);
        return this;
    }

    public NewArticlePage enterArticleDescription(String articleDescription) {
        this.articleDescription.clear();
        this.articleDescription.sendKeys(articleDescription);
        return this;
    }

    public NewArticlePage enterArticleBody(String articleBody) {
        this.articleBody.clear();
        this.articleBody.sendKeys(articleBody);
        return this;
    }

    public NewArticlePage enterTag(String tagName) {
        this.tagInput.sendKeys(tagName);
        this.tagInput.sendKeys(Keys.ENTER);
        return this;
    }

    public NewArticlePage checkEnteredTag(String tagName, int tagIndex) {
        Assert.assertEquals(this.tagsList.findElement(By.xpath("//span[" + tagIndex + "]")).getText(), tagName);
        return this;
    }

    public void publishArticle() throws InterruptedException {
        publishArticle.click();
        Thread.sleep(5000);
    }

    public void clickHome() {
        home.click();
    }
}
