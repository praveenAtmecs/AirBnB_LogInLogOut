import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class trail {
    //objects
    public static WebDriver driver;
    public static WebDriverWait wait;
    //Xpaths
    public static final String url = "https://www.airbnb.co.in/";
    public static final String homePageWhere = "//div[text()='Where']";
    public static final String homePageUserIcon = "//div[@class='_167wsvl']//button[@type='button']";
    public static final String loginButton = "//div[text()='Log in']";
    public static final String userNamePageValidator = "//h2[text()='Welcome to Airbnb']";
    public static final String loginUsingEmail = "//div[text()='Continue with email']";
    public static final String userNameInputField = "//input[@inputmode='email']";
    public static final String continueButton = "//button[@data-testid='signup-login-submit-btn']";
    public static final String passwordPageValidator = "//button[@data-testid='forgot-password-link']";
    public static final String passwordInputField = "//input[@name='user[password]']";
    public static final String loginToHomePageButton = "//button[@data-testid='signup-login-submit-btn']";
    public static final String logoutButton = "//div[text()='Log out']";
    public static final String userMessages = "//div[text()='Messages']";

    public static void main(String[] args) throws InterruptedException, IOException {
        launchURLandvalidateHomePage();
        loginToUserAccount();
        takeScreenShot();
        logoutUserAccount();
        driver.quit();
    }

    public static void clickElement(String xpath){
        WebElement inputElement = driver.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.visibilityOf(inputElement));
        inputElement.click();
    }

    public static void isDisplayedEnterText(String xpath, String sendKey){
        WebElement inputElement = driver.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.visibilityOf(inputElement));
        inputElement.clear();
        inputElement.sendKeys(sendKey);
    }

    public static void pageValidation(String xpath, String message){
        WebElement pageElement = driver.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.visibilityOf(pageElement));
        if(pageElement.isDisplayed()){
                System.out.println("Navigated to: "+message);
        }else{
                System.out.println("Failed to navigate: "+message);
        }
    }

    public static void launchURLandvalidateHomePage(){
        System.setProperty("webdriver.chrome.driver",".//chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        pageValidation(homePageWhere, "HomePage");
    }

    public static void loginToUserAccount() throws InterruptedException {
        clickElement(homePageUserIcon);
        clickElement(loginButton);
        pageValidation(userNamePageValidator,"userName Page");
        clickElement(loginUsingEmail);
        isDisplayedEnterText(userNameInputField, "r.praveenkumar95@outlook.in");
        clickElement(continueButton);
        pageValidation(passwordPageValidator,"Password Page");
        isDisplayedEnterText(passwordInputField,"Atmecs@123");
        clickElement(loginToHomePageButton);
        Thread.sleep(5000);
        clickElement(homePageUserIcon);
        pageValidation(userMessages,"user's Page");
        clickElement(homePageUserIcon);
    }

    public static void takeScreenShot() throws IOException {
        TakesScreenshot tcs = (TakesScreenshot) driver;
        File src = tcs.getScreenshotAs(OutputType.FILE);
        File des = new File(".//sc2.jpeg");
        FileUtils.copyFile(src,des);
    }

    public static void logoutUserAccount() throws InterruptedException {
        clickElement(homePageUserIcon);
        clickElement(logoutButton);
        Thread.sleep(5000);
        clickElement(homePageUserIcon);
        pageValidation(loginButton,"Logout");
    }

}
