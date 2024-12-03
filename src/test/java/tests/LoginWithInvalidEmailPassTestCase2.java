package tests;

import com.aventstack.extentreports.ExtentTest;
import core.BaseTest;
import core.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;

public class LoginWithInvalidEmailPassTestCase2 extends BaseTest{

    @BeforeClass
    public void setup() {
        super.setup();
    }

    @Test(dataProvider = "loginWithInvalidTest")
    public void loginPageTest(String testCaseId, String url, String emailOrPhone, String pass, String description) throws InterruptedException {
        // Khởi tạo một đối tượng ExtentTest cho từng bài kiểm tra
        ExtentTest test = createTest("Login With Invalid Data Test - " + testCaseId);
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.navigateToUrl(url);
        loginPage.clickToSwitchLang();
        String currentUrl = getDriver().getCurrentUrl();
        test.info("Navigated to URL: " + currentUrl);
        String status = "Fail";

        loginPage.sendKeyToLoginTest(emailOrPhone, pass);

        if(loginPage.clickToLogin2()){
            status = "pass";
            test.pass("pass");
        }else {
          test.fail("fail");
        }

        Assert.assertEquals(status, "pass", "The test case failed: " + description);
    }
    @AfterMethod
    public void logTestResults() {
        extent.flush();
    }

    @AfterClass
    public void tearDown() {
        // Flush the report
        extent.flush();
    }



    @DataProvider(name = "loginWithInvalidTest")
    public Object[][] getData(){
        return ExcelUtils.getTableArray("data\\test-data.xlsx", "InvalidTest2",false);
    }
}
