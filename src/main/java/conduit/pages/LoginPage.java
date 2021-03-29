package conduit.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObject{

    @FindBy(xpath="//*[contains(@placeholder, 'Email')]")
    private WebElement email;
    @FindBy(xpath="//*[contains(@placeholder, 'Password')]")
    private WebElement password;
    @FindBy(xpath="//*[contains(@class, 'btn') and text() = 'Sign in']")
    private WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage enterEmail(String email) {
        this.email.clear();
        this.email.sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        this.password.clear();
        this.password.sendKeys(password);
        return this;
    }

    public LoginPage clickSubmitButton() {
        submitButton.click();
        return this;
    }
}
