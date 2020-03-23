import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

import static org.junit.Assert.assertEquals;


class SearchResults {

    public static void assertSearchURLChange(WebDriver driver){
        assertEquals(Constants.searchResultsURL, driver.getCurrentUrl());
    }


    public static void clickFirstBusiness(WebDriver driver){
        List<WebElement> chooseBusiness = driver.findElements(By.className("supplier-logo"));
        chooseBusiness.get(0).click();
    }

    public static void clickFirstGiftCard(WebDriver driver){
        List<WebElement> chooseCard = driver.findElements(By.className("card"));
        chooseCard.get(0).click();
    }

}
