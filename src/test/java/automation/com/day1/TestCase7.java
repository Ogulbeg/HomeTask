package automation.com.day1;

import automation.utilities.BrowserUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TestCase7 {
    private WebDriver driver;
private String URL="https://practice-cybertekschool.herokuapp.com/";
private By fileUploadedBy=By.xpath("//a[text()='File Upload']");
private By uploadBy =By.id("file-submit");
private By uploadedMsgBy=By.xpath("//h3[text()='File Uploaded!']");
private By chooseFileBy=By.cssSelector("input[id='file-upload']");



     @Test(description = " Verify that uploaded file name is displayed")
     public void testCase7(){

         driver.findElement(fileUploadedBy).click();
         driver.findElement(chooseFileBy).sendKeys("/Users/olyaa/Desktop/What Is Agile Methodology?.txt");
     BrowserUtil.wait(4);
     driver.findElement(uploadBy).click();
     BrowserUtil.wait(5);
         System.out.println("driver.findElement(uploadedMsgBy).isDisplayed() = " + driver.findElement(uploadedMsgBy).isDisplayed());
         String expected="File Uploaded!";
     String actual=driver.findElement(uploadedMsgBy).getText();

     assertEquals(actual,expected);

     }

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        BrowserUtil.wait(4);
    }


    @AfterMethod
    public void tearDown(){
      driver.quit();
//      if(driver!=null){
//          driver.quit();
//          driver=null;
//      }
    }

}
