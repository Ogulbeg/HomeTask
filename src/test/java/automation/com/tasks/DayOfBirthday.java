package automation.com.tasks;

import automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DayOfBirthday {
    private WebDriver driver;
    private String URL="http://practice.cybertekschool.com";
    private By dropDownBy=By.linkText("Dropdown");

    @BeforeMethod
    public void setup(){
        driver= DriverFactory.createDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
        driver.findElement(dropDownBy).click();
    }

    @DataProvider(name="testData")
    public static Object[][] testCase(){
Calendar cal = Calendar.getInstance();
String currentYear = String.valueOf(cal.get(Calendar.YEAR));
String currentMonth = String.valueOf(Month.of(cal.get(Calendar.MONTH)+1));
String currentDay = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        return new Object[][]{{"1",currentYear},{"2",currentMonth},{"3",currentDay}};
    }

    @Test(dataProvider = "testData")
    public void dropDownDOB(String index,String expected){
        Select select = new Select(driver.findElement(By.xpath("//h6[text()='Select your date of birth']/following-sibling::select["+index+"]")));
String actual = select.getFirstSelectedOption().getText().toUpperCase();
assertEquals(actual,expected);
    }

    /**
     * TODAYS DATE
     * 1. go to http://practice.cybertekschool.com/dropdown
     * 2. verify that dropdowns under Select your date of birth display current year, month, day
     */


    @Test
    public void todays_date(){
        driver.get("http://practice.cybertekschool.com/dropdown");
        WebElement year = driver.findElement(By.id("year"));
        WebElement month = driver.findElement(By.id("month"));
        WebElement day = driver.findElement(By.id("day"));
        Select y = new Select(year);
        Select m = new Select(month);
        Select d = new Select(day);
        String year_value = y.getFirstSelectedOption().getText();
        String month_value = m.getFirstSelectedOption().getText();
        String day_value = d.getFirstSelectedOption().getText();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMMMdd");
        Assert.assertEquals(year_value+month_value+day_value, sf.format(new Date()));
    }

    /**
     * YEARS, MONTHS, DAYS
     * 1. go to http://practice.cybertekschool.com/dropdown
     * 2. select a random year under Select your date of birth
     * 3. select month January
     * 4. verify that days dropdown has current number of days
     * 5. repeat steps 3, 4 for all the months
     * NOTE: if you randomly select a leap year, verify February has 29 days
     */

    @Test
    public void years_months_days(){
        driver.get("http://practice.cybertekschool.com/dropdown");
        WebElement year = driver.findElement(By.id("year"));
        WebElement month = driver.findElement(By.id("month"));
        WebElement day = driver.findElement(By.id("day"));
        Select y = new Select(year);
        Select m = new Select(month);
        Select d = new Select(day);
        Random r = new Random();
        int index = r.nextInt(y.getOptions().size());
        y.selectByIndex(index);
        List<String> months31 = new ArrayList<>(Arrays.asList(new String[]{"January", "March", "May", "July", "August", "October", "December"}));
        int febDays;
        int yearValue = Integer.parseInt(y.getFirstSelectedOption().getText());
        if(yearValue %400==0 || (yearValue%4 ==0 && yearValue %100!=0)){
            febDays=29;
        }else{
            febDays=28;
        }
        for(int i =0; i<12; i++){
            m.selectByIndex(i);
            if(months31.contains(m.getFirstSelectedOption().getText())){
                Assert.assertEquals(d.getOptions().size(), 31);
            }else if(m.getFirstSelectedOption().getText().equals("February")){
                Assert.assertEquals(d.getOptions().size(), febDays);
            }else{
                Assert.assertEquals(d.getOptions().size(), 30);
            }
        }
    }


    /**
     * DEPARTMENTS SORT
     * 1. go to https://www.amazon.com
     * 2. verify that default value of the All departments dropdown is All
     * 3. verify that options in the All departments dropdown are not sorted alphabetically
     */


    @Test
    public void department_sort(){
        driver.get("https://www.amazon.com");
        Assert.assertEquals(driver.findElement(By.className("nav-search-label")).getText(), "All");
        List<WebElement> l1 =new Select(driver.findElement(By.id("searchDropdownBox"))).getOptions();
        boolean notAlphOrder = false;
        for( int i =0; i<l1.size()-1; i++){
            if(l1.get(i).getText().compareTo(l1.get(i+1).getText())>0){
                notAlphOrder=true;
                break;
            }
        }
        Assert.assertTrue(notAlphOrder);
    }







    /**
     * MAIN DEPARTMENTS
     * 1. go to https://www.amazon.com/gp/site-directory
     * 2. verify that every main department name (indicated by blue rectangles in the picture) is
     * present in the All departments dropdown (indicated by green rectangle in the picture)
     */

    @Test
    public void main_departments() {
        driver.get("https://www.amazon.com/gp/site-directory");
        List<WebElement> mainDep = driver.findElements(By.tagName("h2"));
        List<WebElement> allDep = new Select(driver.findElement(By.id("searchDropdownBox"))).getOptions();
        Set<String> mainDepS = new HashSet<>();
        Set<String> allDepS = new HashSet<>();
        for (WebElement each : mainDep) {
            mainDepS.add(each.getText());
        }
        for (WebElement each : allDep) {
            allDepS.add(each.getText());
        }
        for (String each : mainDepS) {
            if (!allDepS.contains(each)) {
                System.out.println(each);
                System.out.println("This main dep is not in All departments list");
            }
        }
        Assert.assertTrue(allDep.containsAll(mainDepS));

    }

    @AfterMethod
    public void tearDown(){

            driver.quit();
    }
}
