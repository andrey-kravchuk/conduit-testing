package conduit;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class StartTest {
    public static WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
