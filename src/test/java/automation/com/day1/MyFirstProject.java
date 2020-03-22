package automation.com.day1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyFirstProject {

    public static void main(String[] args) throws Exception {

        WebDriverManager.chromedriver().setup();


        WebDriver driver = new ChromeDriver();

        driver.get("http://www.etsy.com");

        Thread.sleep(4000);


        WebElement search =driver.findElement(By.id("global-enhancements-search-query" ));
        search.sendKeys("Golden Earring");
        search.submit();
        Thread.sleep(4000);


        driver.quit();


    }
}
