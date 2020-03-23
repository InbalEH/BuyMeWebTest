import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

class Constants {

    public static final String name = "ענבל עץ הדר";
    public static final String email = Constants.generateString()+"@gmail.com";
    public static final String password = "abcABC123";
    public static final String searchResultsURL = "https://buyme.co.il/search?budget=3&category=68&region=14";
    public static final String forName = "שמואל שמואלי";
    public static final String fromName = "ענבל עץ-הדר";
    public static final String photoLocation = "C:\\Users\\Inbal\\Desktop\\QA\\CouchMolly.jpeg";
    public static final String recipientEmail = "shmuel@gmail.com";
    public static final String currentTime = String.valueOf(System.currentTimeMillis());
    public static final By dropdownOption = By.className("active-result");


    //Paths constants
    public static final String reportPath = "C:\\Users\\Inbal\\Desktop\\QA\\BuyMeReport.html";
    public static final String imageFolderPath = "C:\\Users\\Inbal\\Desktop\\QA\\ReportImages\\";
    public static final String xmlPathName = "C:\\Users\\Inbal\\Desktop\\URL.xml";

    //Driver constants
    public static final String chromeDriverPath = "C:\\Users\\Inbal\\Desktop\\Programs\\QA\\chromedriver.exe";
    public static final String chromeWebDriver = "webdriver.chrome.driver";
    public static final String firefoxDriverPath = "C:\\Users\\Inbal\\Desktop\\Programs\\QA\\geckodriver.exe";
    public static final String firefoxWebDriver = "webdriver.gecko.driver";


    //Generate random string
    public static String generateString() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    //Implicit wait
    public static void implicitWait(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    //Choose an option from any dropdown by list index number
    public static void chooseDropdownIndex(WebDriver driver, int indexNumber) {
        List<WebElement> category = driver.findElements(dropdownOption);
        category.get(indexNumber).click();
    }


}
