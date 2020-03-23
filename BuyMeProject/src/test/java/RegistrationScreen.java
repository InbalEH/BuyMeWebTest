import org.openqa.selenium.*;

class RegistrationScreen {


    public static void changeToSignUp(WebDriver driver) {
        driver.findElement(By.className("text-btn")).click();
    }

    public static void enterName(WebDriver driver) {
        driver.findElement(By.xpath("//input[@placeholder='שם פרטי']")).sendKeys(Constants.name);
    }

    public static void enterEmail(WebDriver driver) {
        driver.findElement(By.xpath("//input[@placeholder='מייל']")).sendKeys(Constants.email);
    }

    public static void enterPassword(WebDriver driver) {
        driver.findElement(By.xpath("//input[@placeholder='סיסמה']")).sendKeys(Constants.password);
    }

    public static void enterPasswordVerification(WebDriver driver) {
        WebElement passVerification= driver.findElement(By.xpath("//input[@placeholder='אימות סיסמה']"));
        passVerification.sendKeys(Constants.password);
        passVerification.sendKeys(Keys.ENTER);
    }

}
