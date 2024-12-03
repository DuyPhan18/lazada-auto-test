package pages;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.concurrent.TimeUnit;

public class RegisterPage extends BasePage {
    public RegisterPage(WebDriver driver){
        super(driver);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @FindBy(how = How.ID, using = ("anonSignup"))
    private WebElement signUpBtn;
    @FindBy(how = How.XPATH, using = "//input[@placeholder='Please enter your phone number']")
    private WebElement phoneInput;
    @FindBy(how = How.XPATH, using = "//input[@placeholder='Minimum 6 characters with a number and a letter']")
    private WebElement passInput;
    @FindBy(how = How.XPATH, using = "//input[@placeholder='First Last']")
    private WebElement fullNameInput;
    @FindBy(how = How.XPATH, using = "//div[@class='mod-login-receive']")
    private WebElement checkBoxNewsLetter;
    @FindBy(how = How.ID, using = "month")
    private WebElement monthDropdown;
    @FindBy(how = How.ID, using = "day")
    private WebElement dayDropdown;
    @FindBy(how = How.ID, using = "year")
    private WebElement yearDropdown;
    @FindBy(how = How.ID, using = "gender")
    private WebElement genderDropdown;


    public void navigateToUrl(String url){
        driver.navigate().to(url);
    }
    public void navigateToRegisterPage(){
        signUpBtn.click();
    }
    public boolean isPhoneInputPresent(){
        return phoneInput.isDisplayed();
    }
    public boolean isPassInputPresent(){
        return passInput.isDisplayed();
    }
    public boolean isFullNameInputPresent(){
        return fullNameInput.isDisplayed();
    }
    public boolean isCheckBoxNewsLetterPresent(){
        return checkBoxNewsLetter.isDisplayed();
    }
    public boolean isMonthDropdownPresent(){
        return monthDropdown.isDisplayed();
    }
    public boolean isDayDropdownPresent(){
        return dayDropdown.isDisplayed();
    }
    public boolean isYearDropdownPresent(){
        return yearDropdown.isDisplayed();
    }
    public boolean isGenderDropdownPresent(){
        return genderDropdown.isDisplayed();
    }
}
