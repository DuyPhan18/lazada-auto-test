package pages;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver){
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//div[@class='sku-prop'][1]")
    private WebElement colorOptions;
    @FindBy(how = How.XPATH, using = "//div[@class='sku-prop'][2]")
    private WebElement propOptions;
    @FindBy(how = How.XPATH, using = "//a[contains(@class, 'next-number-picker-handler-up')]")
    private WebElement qtyNumPickerUp;
    @FindBy(how = How.XPATH, using = "//a[contains(@class, 'next-number-picker-handler-down')]")
    private WebElement qtyNumPickerDown;

    public void navigateToUrl(String url){
        driver.navigate().to(url);
    }
    public boolean isColorOptionsEnable() {
        return colorOptions.isEnabled();
    }
    public boolean isPropOptionsEnable(){
        return propOptions.isEnabled();
    }
    public boolean isQtyNumberUpEnable(){
        return qtyNumPickerUp.isEnabled();
    }
    public boolean isQtyNumberDownEnable(){
        return qtyNumPickerDown.isEnabled();
    }
}
