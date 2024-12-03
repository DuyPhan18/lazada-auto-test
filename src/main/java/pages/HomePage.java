package pages;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver){
        super(driver);
    }
    @FindBy(how = How.CLASS_NAME, using = "p-mod-card-content")
    private WebElement categoryMenu;

    public void navigateToUrl(String url){
        driver.navigate().to(url);
    }
    public boolean isSearchBoxDisplayed(){
        return searchBox.isDisplayed();
    }
    public boolean isCategoryMenuDisplayed(){
        return categoryMenu.isDisplayed();
    }
    public boolean isCartIconDisplayed(){
        return cartIcon.isDisplayed();
    }

}
