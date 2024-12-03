package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private static final int TIMEOUT = 15;
    private static final int POLLING = 5;

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT), Duration.ofSeconds(POLLING));
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "q")
    protected WebElement searchBox;
    @FindBy(how = How.CLASS_NAME, using = "lzd-nav-cart")
    protected WebElement cartIcon;
    @FindBy(how = How.ID, using = "topActionSwitchLang")
    protected WebElement switchLangBtn;
    @FindBy(how = How.XPATH, using = "//div[@data-lang='en']")
    protected WebElement switchToEnLangBtn;
    @FindBy(how = How.XPATH, using = ("//a[text()='login']"))
    protected WebElement logInBtn;
    public void navigateToLoginPage(){
        logInBtn.click();
    }

    //change language
    public void clickToSwitchLang(){
        wait.until(ExpectedConditions.elementToBeClickable(switchLangBtn)).click();
        String classAttribute = switchToEnLangBtn.getAttribute("class");
        boolean isSelected = classAttribute.contains("currentSelected");
        if (!isSelected) {
            // Click để chọn ngôn ngữ tiếng Anh
            wait.until(ExpectedConditions.elementToBeClickable(switchToEnLangBtn)).click();
            System.out.println("Đã chọn ngôn ngữ tiếng Anh.");
        } else {
            System.out.println("Ngôn ngữ tiếng Anh đã được chọn.");
        }

    }
    public void clickToSwitchToEnLang(){
        String classAttribute = switchToEnLangBtn.getAttribute("class");
        boolean isSelected = classAttribute.contains("currentSelected");
        if (!isSelected) {
            // Click để chọn ngôn ngữ tiếng Anh
            wait.until(ExpectedConditions.elementToBeClickable(switchToEnLangBtn)).click();
            System.out.println("English Language is chosen");
        } else {
            System.out.println("English Language is chosen");
        }
    }
}
