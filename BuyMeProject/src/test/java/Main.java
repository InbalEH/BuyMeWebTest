import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Main {

    private static ExtentReports extent = new ExtentReports();
    private static ExtentTest test = extent.createTest(ReportConstants.testName, ReportConstants.testDescription);

    private static WebDriver driver;

    private static String getData (String keyName) throws ParserConfigurationException, IOException, SAXException {
        File configXmlFile = new File(Constants.xmlPathName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        dBuilder = dbFactory.newDocumentBuilder();

        Document doc = null;

        assert dBuilder != null;
        doc = dBuilder.parse(configXmlFile);

        if (doc != null) {
            doc.getDocumentElement().normalize();
        }
        assert doc != null;
        return doc.getElementsByTagName(keyName).item(0).getTextContent();
    }


    @BeforeClass
    public static void beforeClass() throws IOException, SAXException, ParserConfigurationException {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Constants.reportPath);
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo(ReportConstants.sysInfoK,ReportConstants.sysInfoV);
        test.log(Status.INFO, ReportConstants.beforeClassInfo);


        try {
            //Open browser according to XML file
            String browserType = getData("browserType");
            if (browserType.equals("Chrome")) {
                System.setProperty(Constants.chromeWebDriver, Constants.chromeDriverPath);
                driver = new ChromeDriver();
            }

            if (browserType.equals("Firefox")) {
                driver = new FirefoxDriver();
                System.setProperty(Constants.firefoxWebDriver, Constants.firefoxDriverPath);
            }
            test.log(Status.PASS, ReportConstants.beforeClassPass);
        }catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, ReportConstants.beforeClassFail);
        }

        driver.manage().window().maximize();
        driver.get(getData("URL"));
    }

    @Test
    public void test01_userRegistration() throws IOException {

        //Implicit wait
        Constants.implicitWait(driver);

        test.pass(ReportConstants.frontPageScrnSht, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.imageFolderPath + ReportConstants.frontPagePath + Constants.currentTime)).build());
        test.log(Status.INFO, ReportConstants.testOneInfo);

        //Click on login/sign up icon on front page
        HomeScreen.clickLogIn(driver);


        //Switch to sign up option
        RegistrationScreen.changeToSignUp(driver);
        test.pass(ReportConstants.registrationScrnSht, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.imageFolderPath + ReportConstants.registrationPath + Constants.currentTime)).build());

        //Enter name in sign up form
        RegistrationScreen.enterName(driver);

        //Enter email in sign up form
        RegistrationScreen.enterEmail(driver);

        //Enter password
        RegistrationScreen.enterPassword(driver);

        //Enter password verification and submit form
        RegistrationScreen.enterPasswordVerification(driver);

        try {
            //Check if "Logged in" icon is present
            HomeScreen.findLoggedInButton(driver);
            test.log(Status.PASS, ReportConstants.signUpPass);
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, ReportConstants.signUpFail);
            test.fail(ReportConstants.scrnsht, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.imageFolderPath + ReportConstants.signUpFailPath + Constants.currentTime)).build());
        }

    }

    @Test
    public void test02_searchGift() throws IOException {

        //Implicit wait
        Constants.implicitWait(driver);

        test.log(Status.INFO, ReportConstants.testTwoInfo);

        try{
            //Choose a price range for the gift card
            HomeScreen.clickPriceList(driver);
            Constants.chooseDropdownIndex(driver, 3);
            test.log(Status.PASS, ReportConstants.priceRangePass);
        }catch(Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, ReportConstants.priceRangeFail);
            test.fail(ReportConstants.scrnsht, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.imageFolderPath + ReportConstants.priceRangeFailPath + Constants.currentTime)).build());
        }

        try{
            //Choose the area for the gift card
            HomeScreen.clickAreaList(driver);
            Constants.chooseDropdownIndex(driver, 5);
            test.log(Status.PASS, ReportConstants.areaPass);
        }catch(Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, ReportConstants.areaFail);
            test.fail(ReportConstants.scrnsht, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.imageFolderPath + ReportConstants.areaFailPath + Constants.currentTime)).build());
        }

        try{
            //Choose the category of the gift card
            HomeScreen.clickCategoryList(driver);
            Constants.chooseDropdownIndex(driver, 6);
            test.log(Status.PASS, ReportConstants.categoryPass);
        }catch(Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, ReportConstants.categoryFail);
            test.fail(ReportConstants.scrnsht, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.imageFolderPath + ReportConstants.categoryFailPath + Constants.currentTime)).build());
        }


        try {
            //Click search for gift card
            HomeScreen.clickSearch(driver);
            test.log(Status.PASS, ReportConstants.searchPass);
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, ReportConstants.searchFail);
            test.fail(ReportConstants.scrnsht, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.imageFolderPath + ReportConstants.searchFailPath + Constants.currentTime)).build());
        }

    }

    @Test
    public void test03_chooseGiftCard() throws IOException {

        //Implicit wait
        Constants.implicitWait(driver);

        test.log(Status.INFO, ReportConstants.testThreeInfo);

        //Assert that the URL changed after clicking search
        SearchResults.assertSearchURLChange(driver);

        try {
            //Click on the first business card in the search results
            SearchResults.clickFirstBusiness(driver);
            test.log(Status.PASS, ReportConstants.businessPass);
            test.pass(ReportConstants.businessPageScrnSht, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.imageFolderPath + ReportConstants.businessPagePath + Constants.currentTime)).build());
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, ReportConstants.businessFail);
            test.fail(ReportConstants.scrnsht, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.imageFolderPath + ReportConstants.businessFailPath + Constants.currentTime)).build());
        }

        try {
            //Click on the first gift card of that business
            SearchResults.clickFirstGiftCard(driver);
            test.log(Status.PASS, ReportConstants.giftCardPass);
            test.pass(ReportConstants.giftCardPageScrnSht, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.imageFolderPath + ReportConstants.giftCardPagePath + Constants.currentTime)).build());
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, ReportConstants.giftCardFail);
            test.fail(ReportConstants.scrnsht, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.imageFolderPath + ReportConstants.giftCardFailPath + Constants.currentTime)).build());
        }

    }

    @Test
    public void test04_sendGift() throws IOException {

        //Implicit wait
        Constants.implicitWait(driver);

        test.log(Status.INFO, ReportConstants.testFourInfo);
        test.pass(ReportConstants.sendGiftPageScrnSht, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.imageFolderPath + ReportConstants.sendGiftPagePath + Constants.currentTime)).build());


        //Click on the "For someone else" radio button in "Who to send to" section
        SendGift.clickForSomeoneElseRadioButton(driver);

        //Enter the name of the gift recipient
        SendGift.enterForName(driver);

        //Enter the name of the gift sender
        SendGift.enterFromName(driver);

        //Choose gift card occasion
        SendGift.clickOccasionList(driver);
        Constants.chooseDropdownIndex(driver, 3);

        //Add a personal greeting to gift card
        SendGift.enterTextGreetingBox(driver);

        //Upload a photo to gift card
        SendGift.uploadPhoto(driver);

        //Click on "send now" radio button
        SendGift.clickSendNowRadioButton(driver);

        //Choose to send by email and enter the recipients email
        SendGift.clickSendByEmailButton(driver);
        SendGift.enterRecipientEmail(driver);

        try {
            //Submit the "send gift" form, and proceed to payment page
            SendGift.clickSubmit(driver);
            test.log(Status.PASS, ReportConstants.giftInfoPass);
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, ReportConstants.giftInfoFail);
            test.fail(ReportConstants.scrnsht, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.imageFolderPath + ReportConstants.giftInfoFailPath + Constants.currentTime)).build());
        }

    }


    @AfterClass
    public static void afterClass() {
        test.log(Status.INFO, ReportConstants.afterClassInfo);
        extent.flush();
    }


    private static String takeScreenShot(String ImagesPath) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath + ".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath + ".png";
    }
}
