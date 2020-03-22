package automation.com.day1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NestedFrame {
    public static void main(String[] args) throws Exception{

        WebDriverManager.chromedriver().version("79").setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://practice.cybertekschool.com/nested_frames");
        Thread.sleep(2000);
        driver.switchTo().frame("frame-top");//parent frame
        driver.switchTo().frame("frame-right");//child frame
        Thread.sleep(2000);

        WebElement right = driver.findElement(By.tagName("body"));//content inside a child frame
        System.out.println(right.getText());

        Thread.sleep(2000);

        Thread.sleep(2000);
        driver.quit();
    }
/**
 * WebElement content = driver.findElement(By.id("content"));//content inside a child frame
 *         System.out.println(content.getText());
 *         driver.switchTo().parentFrame();//go to the top frame
 *         driver.switchTo().frame("frame-right");//switch to the right frame
 *         WebElement body = driver.findElement(By.tagName("body"));
 *         System.out.println(body.getText());
 *         //to go to the bottom frame, you need to switch to the default content
 *         //because, top frame is a sibling for bottom frame, but not a parent
 *         driver.switchTo().defaultContent();
 *         driver.switchTo().frame("frame-bottom");
 *         System.out.println(driver.findElement(By.tagName("body")).getText());
 */


}
