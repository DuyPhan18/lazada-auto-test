package pages;

import core.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(how = How.ID, using = "myAccountTrigger")
    private WebElement myAccIsLogined;
    @FindBy(how = How.XPATH, using = "//div[./span[text()='Facebook']]")
    private WebElement fbLoginBtnFromPopup;
    @FindBy(how = How.XPATH, using = "//button[./span[text()='Facebook']]")
    private WebElement fbLoginBtn;
    @FindBy(how = How.ID, using = "email")
    private WebElement emailFBLoginInput;
    @FindBy(how = How.ID, using = "pass")
    private WebElement passFBLoginInput;
    @FindBy(how = How.XPATH, using = "//label[@id=\"loginbutton\"]")
    private WebElement facebookLoginSubmit;
    @FindBy(how = How.XPATH, using = "//button[contains(@class, 'add-to-cart-buy-now-btn')][2]")
    private WebElement addToCartBtn;
    @FindBy(how = How.XPATH, using = "//span[text()='Added to cart successfully!']")
    private WebElement addSuccessMsg;
    @FindBy(how = How.XPATH, using = "//a[@class='next-dialog-close']")
    private WebElement closePopupAddedSuccess;
    @FindBy(how = How.ID, using = "topActionCartNumber")
    private WebElement cartItemCount;


    public void navigateToUrl(String url){
        driver.navigate().to(url);
    }

    public boolean isLogIned(){
        try {
            return myAccIsLogined.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public void loginWithFacebook(String email, String password) {

        String mainWindow = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        try {
            if (fbLoginBtnFromPopup.isDisplayed()) {
                fbLoginBtnFromPopup.click();
            }
        } catch (NoSuchElementException e) {
            fbLoginBtn.click();
        }

        // Chuyển đến cửa sổ popup Facebook
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        emailFBLoginInput.sendKeys(email);
        passFBLoginInput.sendKeys(password);
        facebookLoginSubmit.click();

        // Chuyển lại cửa sổ chính
        driver.switchTo().window(mainWindow);
    }
    public void waitForLoginSuccess() {
        wait.until(ExpectedConditions.visibilityOf(myAccIsLogined));
    }

    public boolean addProductsToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
        return true;
    }

    public boolean verifyAddToCartDone(){
        String mainWindow = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        if (wait.until(ExpectedConditions.visibilityOf(addSuccessMsg)).isDisplayed()){
            closePopupAddedSuccess.click();
            driver.switchTo().window(mainWindow);
            return true;
        }
        return false;
    }
    public int getCartItemCount() {
        wait.until(ExpectedConditions.visibilityOf(cartItemCount));
        String itemCountText = cartItemCount.getText().trim();
        try {
            return Integer.parseInt(itemCountText);
        } catch (NumberFormatException e) {
            return 0; // Trường hợp không thể chuyển đổi thành số, trả về 0
        }
    }

}

