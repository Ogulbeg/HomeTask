package automation.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

    public static WebDriver createDriver(String browser){
        browser=browser.toLowerCase();

        switch (browser){
            case "chrome":
                WebDriverManager.chromedriver().version("79").setup();
                ChromeOptions option = new ChromeOptions();
                option.setHeadless(true);
            return new ChromeDriver(option);

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();

            case "safari":
                if(checkOperationalSystem("windows")){
                    return null;
                }else {
                    return new SafariDriver();
                }
            case"edge":
                if(checkOperationalSystem("mac")){
                    return null;

                }else if(checkOperationalSystem("windows")){
                    WebDriverManager.edgedriver().setup();
                    return new EdgeDriver();

                }
        }
        return null;
    }
    public static boolean checkOperationalSystem(String system){
        return System.getProperty("os.name").equalsIgnoreCase(system);

    }




}
