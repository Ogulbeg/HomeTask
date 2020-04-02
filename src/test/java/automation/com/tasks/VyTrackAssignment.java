package automation.com.tasks;

import automation.utilities.BrowserUtil;
import automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class VyTrackAssignment {
    private WebDriver driver;
    private String URL = "https://qa2.vytrack.com/user/login";
    private By userNameBy = By.id("prependedInput");
    private By passwordBy = By.id("prependedInput2");
    private By loginButtonBy = By.id("_submit");

    private By vehicleBy = By.linkText("Vehicles");
    private By allCarsBy = By.className("oro-subtitle");

    @BeforeTest
    public void setup() {
        driver = DriverFactory.createDriver("chrome");
        driver.manage().window().maximize();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void test1() {
        driver.findElement(userNameBy).sendKeys("storemanager71");
        driver.findElement(passwordBy).sendKeys("UserUser123");
        driver.findElement(loginButtonBy).click();
        Actions actions = new Actions(driver);
        WebElement fleet = driver.findElement(By.xpath("//span[@class='title title-level-1' and contains(text(),'Fleet')]"));
        BrowserUtil.wait(4);
        actions.moveToElement(fleet).perform();
        BrowserUtil.wait(4);
        String[] expectedVehicleNames = {"Vehicles", "Vehicle Odometer", "Vehicle Costs", "Vehicle Contracts",
                "Vehicles Fuel Logs", "Vehicle Services Logs", "Vehicles Model"};
        List<WebElement> allOptions = driver.findElements(By.partialLinkText("Vehicle"));
        // allOptions.forEach(each-> System.out.println("each = " + each.getText().trim()));

        for (int i = 0; i < allOptions.size(); i++) {

            System.out.println("allOption = " + allOptions.get(i).getText().trim());
            String actual = allOptions.get(i).getText().trim();
            Assert.assertEquals(actual, expectedVehicleNames[i]);
        }


        driver.findElement(vehicleBy).click();
        BrowserUtil.wait(4);
        String actual = driver.findElement(allCarsBy).getText();
        Assert.assertEquals(actual, "All Cars");
        BrowserUtil.wait(4);
//        String[] expectedNames = {"LICENSE PLATE", "TAGS", "DRIVER", "LOCATION", "CHASSIS NUMBER", "MODEL YEAR",
//                "LAST ODOMETER", "IMMATRICULATION DATE", "FIRST CONTRACT DATE", "CVVI", "SEATS NUMBER",
//                "DOORS NUMBER", "COLOR", "TRANSMISSION", "FUEL TYPE", "CO2 EMISSIONS", "HORSEPOWER",
//                "HORSEPOWER TAXATION", "POWER (KW)","CVVI"};
        List<WebElement> allNamesInVehiclePage = driver.findElements(By.cssSelector("[class='grid-header-cell__label']"));
        for (int i = 0; i < allNamesInVehiclePage.size(); i++) {
            if (allNamesInVehiclePage.get(i).getText().isEmpty()) {
                continue;
            }
//            String actualNames=allNamesInVehiclePage.get(i).getText();
//            Assert.assertEquals(actualNames, expectedNames[i]);
            System.out.println("eachName = " + allNamesInVehiclePage.get(i).getText().trim());
        }

    }


    @AfterMethod
    public void tearDown() {
        if (driver != null)
            driver.quit();
        driver = null;
    }
}
