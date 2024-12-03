package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import core.BaseTest;
import core.ExcelUtils;
import core.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;

public class HomePageTest extends BaseTest {

    @BeforeClass
    public void setup() {
        super.setup();
    }

    @Test(dataProvider = "homePageTestData")
    public void homePageTest(String testCaseId, String url){
        // Khởi tạo một đối tượng ExtentTest cho từng bài kiểm tra
        //ExtentTest testHomePage = createTest("Home Page Test - " + testCaseId);

        HomePage homePage = new HomePage(getDriver());
        homePage.navigateToUrl(url);
        //testHomePage.info("Navigated to URL: " + url);

        boolean isSearchBoxDisplayed = homePage.isSearchBoxDisplayed();
        boolean isCategoryDisplayed = homePage.isCategoryMenuDisplayed();
        boolean isCartIconDisplayed = homePage.isCartIconDisplayed();

//        if (isSearchBoxDisplayed) {
//            testHomePage.pass("Search box is displayed");
//        } else {
//            testHomePage.fail("Search box is not displayed");
//        }
//
//        if (isCategoryDisplayed) {
//            testHomePage.pass("Category menu is displayed");
//        } else {
//            testHomePage.fail("Category menu is not displayed");
//        }
//
//        if (isCartIconDisplayed) {
//            testHomePage.pass("Cart icon is displayed");
//        } else {
//            testHomePage.fail("Cart icon is not displayed");
//        }

        Assert.assertTrue(isSearchBoxDisplayed, "False");
        Assert.assertTrue(isCategoryDisplayed, "False");
        Assert.assertTrue(isCartIconDisplayed, "False");

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

    @DataProvider(name = "homePageTestData")
    public Object[][] getData() throws Exception{
        return ExcelUtils.getTableArray("data\\test-data.xlsx", "HomePageTest",false);
    }
}
