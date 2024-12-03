package pages;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    private String errorMessage;
    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(how = How.XPATH, using = ("//a[text()='login']"))
    private WebElement logInBtn;
    public void navigateToLoginPage(){
        logInBtn.click();
    }
    @FindBy(how = How.XPATH, using = "//input[@type='text']")
    private WebElement phoneOrEmailInput;
    @FindBy(how = How.XPATH, using = "//input[@type='password']")
    private WebElement passInput;
    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    private WebElement logInSubmitBtn;
    @FindBy(how = How.XPATH, using = "//span[@id='nc_2_n1z']")
    private WebElement slideToLoginBtn;
    @FindBy(how = How.XPATH, using = "//div[@id='nc_2__scale_text']")
    private WebElement slideTrack;
    //Error MSG
    @FindBy(xpath = "//span[contains(text(), \"You can't leave this empty\")]")
    private WebElement errorBlankSpan;
    @FindBy(xpath = "//span[contains(text(), \"The length of the Phone or Email should be 6-60 characters.\")]")
    private WebElement errorNotEnoughCharSpan;
    @FindBy(how = How.XPATH, using = "//div[@class='next-feedback-content']")
    private WebElement errorInvalid;

    public void navigateToUrl(String url){
        driver.navigate().to(url);
    }
    public boolean isPhoneOrEmailInputPresent(){
        return phoneOrEmailInput.isDisplayed();
    }

    public boolean isPassInputPresent(){
        return passInput.isDisplayed();
    }
    public boolean isLogInSubmitBtnPresent(){
        return logInSubmitBtn.isDisplayed();
    }

    //Test Login With Invalid Email or Phone and Password
    public void sendKeyToLoginTest(String emailOrPhone, String pass){
        wait.until(ExpectedConditions.visibilityOf(phoneOrEmailInput)).sendKeys(emailOrPhone);
        wait.until(ExpectedConditions.visibilityOf(passInput)).sendKeys(pass);
    }
    public boolean isSlideToLoginBtnPresent() {
        try {
            wait.until(ExpectedConditions.visibilityOf(slideToLoginBtn));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void performSlideToLogin() {
        WebElement slider = slideToLoginBtn;
        WebElement sliderTrack = slideTrack;

        int sliderWidth = sliderTrack.getSize().width;
        Actions action = new Actions(driver);
        action.dragAndDropBy(slider, sliderWidth, 0).perform();
    }
    public void clickToLogin() {
        if (isSlideToLoginBtnPresent()) {
            performSlideToLogin();
        } else {
            logInSubmitBtn.click();
        }
    }
    public boolean clickToLogin2() throws InterruptedException {
        if (isSlideToLoginBtnPresent()) {
            performSlideToLogin();
            Thread.sleep(10000);
            errorMessage = wait.until(ExpectedConditions.visibilityOf(errorInvalid)).getText();
            System.out.println(errorMessage);
            return true;
        } else {
            logInSubmitBtn.click();
            Thread.sleep(10000);
            errorMessage = wait.until(ExpectedConditions.visibilityOf(errorInvalid)).getText();
            System.out.println(errorMessage);
            return true;
        }
    }


    //check error if email or phone or pass is bank
    public boolean isErrorBlankSpanDisplayed() {

        try {
            wait.until(ExpectedConditions.visibilityOf(errorBlankSpan));
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    //check error if email or phone or pass is not enough char
    public boolean isErrorNotEnoughCharDisplayed(){
        try {
            wait.until(ExpectedConditions.visibilityOf(errorNotEnoughCharSpan));
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    private void captureErrorMessage() {
        try {
            // Using a short wait to quickly capture the error before it disappears
            wait.until(ExpectedConditions.visibilityOf(errorInvalid));
            errorMessage = errorInvalid.getText();
        } catch (Exception e) {
            errorMessage = null;
        }
    }
    public boolean isErrorInvalidDisplayed(){
        try {
            wait.until(ExpectedConditions.visibilityOf(errorInvalid));
            System.out.println("invalid");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public String getErrorMessage() {
        if (isErrorBlankSpanDisplayed()) {
            return errorBlankSpan.getText();
        } else if (isErrorNotEnoughCharDisplayed()) {
            return errorNotEnoughCharSpan.getText();
        } else if (isErrorInvalidDisplayed()) {
            // Làm tương tự nếu cần thiết
            return errorInvalid.getText();
        }
        return "";
    }

    //login
    public void loginWithCredentials(String username, String password) {
        phoneOrEmailInput.sendKeys(username);
        passInput.sendKeys(password);
        logInSubmitBtn.click();
    }}
