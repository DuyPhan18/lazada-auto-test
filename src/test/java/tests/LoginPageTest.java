package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import core.BaseTest;
import core.ExcelUtils;
import core.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;

public class LoginPageTest extends BaseTest {
    @BeforeClass
    public void setup() {
        super.setup();
    }
    @Test(dataProvider = "loginPageTestData")
    public void loginPageTest(String testCaseId, String url){
        // Khởi tạo một đối tượng ExtentTest cho từng bài kiểm tra
//        ExtentTest testLoginPage = createTest("Login Page Test - " + testCaseId);
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.navigateToUrl(url);
        loginPage.navigateToLoginPage();
        //String currentUrl = getDriver().getCurrentUrl();
        //testLoginPage.info("Navigated to URL: " + currentUrl);

        boolean isPhoneOrEmailInputPresent = loginPage.isPhoneOrEmailInputPresent();
        boolean isPassInputPresent = loginPage.isPassInputPresent();
        boolean isLogInSubmitBtnPresent = loginPage.isLogInSubmitBtnPresent();


        Assert.assertTrue(isPhoneOrEmailInputPresent, "False");
        Assert.assertTrue(isPassInputPresent, "False");
        Assert.assertTrue(isLogInSubmitBtnPresent, "False");


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

    @DataProvider(name = "loginPageTestData")
    public Object[][] getData(){
        return ExcelUtils.getTableArray("data\\test-data.xlsx", "LoginPageTest",false);
    }
}
