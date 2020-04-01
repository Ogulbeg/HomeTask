package automation.com.tasks;

import automation.utilities.BrowserUtil;
import automation.utilities.DriverFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeUnit;

public class WebOrdersTest {
    private WebDriver driver;
    private String URL="http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx";
    private By usernameBy= By.id("ctl00_MainContent_username");
    private By passwordBy= By.id("ctl00_MainContent_password");


    /**
     *     Go to web orders page
     *     Verify that Steve Johns zip code is 21233
     *     Then update his zip code to 20002
     *     Then verify that Steve Johns zip code is 20002
     */
    @DataProvider(name = "testData")
    public static Object[][] nameSearch(){
        return new Object[][]{{"Paul Brown","748"},{"Mark Smith","76743"},
                {"Steve Johns","21233"},{"Charles Dodgeson","23233"},
                {"Susan McLaren","21444"},{"Bob Feather","81734"},
                {"Samuel Clemens","53665"},{"Clare Jefferson","63325"}};
    }
    @Test (dataProvider = "testData")
    public void checkZipAndUpdate(String name, String zipcode){
        String actual=driver.findElement(By.xpath("//td[text()='"+name+"']/following-sibling::td[7]")).getText();
        String expected = zipcode;
        Assert.assertEquals(actual,expected);
    }
    @Test
    public void checkZipAndUpdate() {
        List<String> expected = new ArrayList<>(Arrays.asList("748", "76743", "21233", "23233", "21444", "81734", "53665", "63325"));
        List<WebElement> actual = driver.findElements(By.xpath("//td[9]"));
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertEquals(actual.get(i).getText(), expected.get(i));
        }
    }
@Test
public void editZip(){
   List<WebElement> editBtns = driver.findElements(By.xpath("//td//input"));
   WebDriverWait wait = new WebDriverWait(driver,5);
   for(WebElement eachBtn: editBtns){
       eachBtn.click();
       wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h2")));
       BrowserUtil.wait(4);
       WebElement zipcode = driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5"));
BrowserUtil.wait(3);
       zipcode.clear();


  zipcode.sendKeys("12345");

  List<WebElement> radioBtns = driver.findElements(By.name("ctl00$MainContent$fmwOrder$cardList"));

  for(WebElement radioBtn: radioBtns){
      if(!radioBtn.isSelected()){
          radioBtn.click();
          break;
      }
  }
  BrowserUtil.wait(3);
  driver.findElement(By.id("ctl00_MainContent_fmwOrder_UpdateButton")).click();

   }
}
    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createDriver("chrome");
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
        driver.manage().window().maximize();
        driver.findElement(usernameBy).sendKeys("Tester");
        driver.findElement(passwordBy).sendKeys("test", Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    @AfterMethod
    public void teardown(){
        if (driver!=null) {
            driver.quit();
            driver = null;
        }
    }
}
