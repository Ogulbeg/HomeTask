package automation.com;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VyTrack {
    private WebDriver driver;
    private By usernameBy = By.cssSelector("prependedInput");
    private By passwordBy = By.cssSelector("prependedInput2");

    @Test
    public void verifySales(){

    }





    @BeforeMethod
    public void setup() throws Exception {
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get("https://qa2.vytrack.com/user/login");
        driver.manage().window().maximize();
        Thread.sleep(400);
driver.findElement(usernameBy).sendKeys("storemanager85");
driver.findElement(passwordBy).sendKeys("UserUser123", Keys.ENTER);
Thread.sleep(5000);


    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
