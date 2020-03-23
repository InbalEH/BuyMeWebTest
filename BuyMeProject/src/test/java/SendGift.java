import org.openqa.selenium.*;
import java.util.List;

class SendGift {

    public static void clickForSomeoneElseRadioButton(WebDriver driver){
        driver.findElement(By.xpath("//label[@data='forSomeone']")).click();
    }

    public static void enterForName(WebDriver driver){
        driver.findElement(By.xpath("//input[@data-parsley-required-message='מי הזוכה המאושר? יש להשלים את שם המקבל/ת']")).sendKeys(Constants.forName);
    }

    public static void enterFromName(WebDriver driver){
        driver.findElement(By.xpath("//input[@data-parsley-required-message='למי יגידו תודה? שכחת למלא את השם שלך']")).sendKeys(Constants.fromName);
    }

    public static void clickOccasionList(WebDriver driver){
        List<WebElement> chooseBusiness = driver.findElements(By.className("chosen-container"));
        chooseBusiness.get(6).click();
    }

    public static void enterTextGreetingBox(WebDriver driver){
        WebElement greetingTextBox = driver.findElement(By.xpath("//textarea[@rows='4']"));
        greetingTextBox.sendKeys(Keys.ENTER);
        greetingTextBox.sendKeys("באהבה,ענבל");
    }

    public static void uploadPhoto(WebDriver driver){
        driver.findElement(By.name("fileUpload")).sendKeys(Constants.photoLocation);
    }

    public static void clickSendNowRadioButton(WebDriver driver){
        driver.findElement(By.className("send-now")).click();
    }

    public static void clickSendByEmailButton(WebDriver driver){
        List<WebElement> sendOptions = driver.findElements(By.className("btn-send-option"));
        sendOptions.get(1).click();
    }

    public static void enterRecipientEmail(WebDriver driver){
        WebElement emailTextBox = driver.findElement(By.xpath("//input[@placeholder='כתובת המייל של מקבל/ת המתנה']"));
        emailTextBox.sendKeys(Constants.recipientEmail);
        emailTextBox.sendKeys(Keys.ENTER);
    }

    public static void clickSubmit(WebDriver driver){
        driver.findElement(By.xpath("//button[@data-toggle='modal']")).click();
    }

}
