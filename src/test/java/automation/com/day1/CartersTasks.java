package automation.com.day1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CartersTasks {

    public static void main(String[] args) throws Exception {
        searchBox("dress");

    }

    public static void searchBox(String item) throws Exception {
        WebDriverManager.chromedriver().version("79.0").setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://www.etsy.com/");
//        Thread.sleep(2000);
//       driver.close();


        driver.findElement(By.id("global-enhancements-search-query")).sendKeys(item, Keys.ENTER);
        Thread.sleep(2000);
        //driver.findElement(By.className(""));

        driver.get("https://www.etsy.com/listing/464174637/boho-wedding-dress-white-lace-long?ga_order=most_relevant&ga_search_type=all&ga_view_type=gallery&ga_search_query=dress&ref=sc_gallery-1-2&plkey=9209f700f9b01bcb68edfac13020a70235568364%3A464174637&pro=1&frs=1&col=1");

        Thread.sleep(2000);
        driver.quit();
    }
}
