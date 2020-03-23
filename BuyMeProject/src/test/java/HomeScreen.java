import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

class HomeScreen {

    public static void clickLogIn(WebDriver driver) {
        driver.findElement(By.className("seperator-link")).click();
    }

    public static void clickPriceList(WebDriver driver) {
        List<WebElement> category = driver.findElements(By.className("chosen-container"));
        category.get(0).click();
    }


    public static void clickAreaList(WebDriver driver) {
        List<WebElement> category = driver.findElements(By.className("chosen-container"));
        category.get(1).click();
    }


    public static void clickCategoryList(WebDriver driver) {
        List<WebElement> category = driver.findElements(By.className("chosen-container"));
        category.get(2).click();
    }


    public static void clickSearch(WebDriver driver) {
        driver.findElement(By.className("search")).click();
    }

    public static void findLoggedInButton(WebDriver driver) {
        driver.findElement(By.className("arrow"));
    }

}
