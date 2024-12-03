package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import core.BaseTest;
import core.ExcelUtils;
import core.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ProductPage;

public class ProductPageTest extends BaseTest {
    @BeforeClass
    public void setup() {
        super.setup();
    }
    @Test(dataProvider = "productPageTestData")
    public void productPageTest(String testCaseId, String url){
        //ExtentTest test = createTest("Product page Test - " + testCaseId);
        ProductPage productPage = new ProductPage(getDriver());
        productPage.navigateToUrl(url);

        //String currentUrl = getDriver().getCurrentUrl();
        //test.info("Navigated to URL: " + currentUrl);

        boolean isColorOptionsEnable = productPage.isColorOptionsEnable();
        boolean isPropOptionsEnable = productPage.isPropOptionsEnable();
        boolean isQtyNumberUpEnable = productPage.isQtyNumberUpEnable();
        boolean isQtyNumberDownEnable = productPage.isQtyNumberDownEnable();
        boolean isQtyNumEnable;
        isQtyNumEnable = isQtyNumberUpEnable && isQtyNumberDownEnable;

//        if (isColorOptionsEnable) {
//            test.pass("Color options are enabled");
//        } else {
//            test.fail("Color options are not enabled");
//        }
//
//        if (isPropOptionsEnable) {
//            test.pass("Product options are enabled");
//        } else {
//            test.fail("Product options are not enabled");
//        }
//
//        if (isQtyNumEnable) {
//            test.pass("Quantity controls are enabled");
//        } else {
//            test.fail("Quantity controls are not enabled");
//        }

        Assert.assertTrue(isColorOptionsEnable, "Color options are not enabled");
        Assert.assertTrue(isPropOptionsEnable, "Product options are not enabled");
        Assert.assertTrue(isQtyNumEnable, "Quantity controls are not enabled");

    }
//    @AfterMethod
//    public void logTestResults() {
//        extent.flush();
//    }
//
//    @AfterClass
//    public void tearDown() {
//        // Flush the report
//        extent.flush();
//    }
    @AfterMethod
    public void closeBrowser(){
        getDriver().close();
    }

    @DataProvider(name = "productPageTestData")
    public Object[][] getData() throws Exception{
        return ExcelUtils.getTableArray("data\\test-data.xlsx", "ProductPageTest",false);
    }
}
