package automation.utilities;


import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BrowserUtil {

    public static void wait(int seconds){
        try{
            Thread.sleep(seconds*1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }

    public static List<String> printWebElements(List<WebElement> elements){
        List<String> webElementText = new ArrayList<>();
        for (WebElement element : elements) {
          webElementText.add(element.getText());
        }
       return webElementText;
    }
}
