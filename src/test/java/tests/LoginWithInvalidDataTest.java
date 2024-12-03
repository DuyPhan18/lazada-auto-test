package tests;

import com.aventstack.extentreports.ExtentTest;
import core.BaseTest;
import core.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;

public class LoginWithInvalidDataTest extends BaseTest {

    @BeforeClass
    public void setup() {
        super.setup();
    }

    @Test(dataProvider = "loginWithInvalidDataTest")
    public void loginPageTest(String testCaseId, String url, String emailOrPhone, String pass, String description) {
        // Khởi tạo một đối tượng ExtentTest cho từng bài kiểm tra
        ExtentTest test = createTest("Login With Invalid Data Test - " + testCaseId);
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.navigateToUrl(url);
        loginPage.clickToSwitchLang();


        String currentUrl = getDriver().getCurrentUrl();
        test.info("Navigated to URL: " + currentUrl);
        String errorMessage = "";
        String status = "Fail"; // Default status is Fail

        loginPage.sendKeyToLoginTest(emailOrPhone, pass);
        loginPage.clickToLogin();
        // Check for the errors in the order of priority
        if (loginPage.isErrorInvalidDisplayed()) {
            errorMessage = loginPage.getErrorMessage();
            test.pass("Pass");
            status = "Pass";
        } else if (loginPage.isErrorNotEnoughCharDisplayed()) {
            errorMessage = loginPage.getErrorMessage();
            test.pass("Pass");
            status = "Pass";
        } else if (loginPage.isErrorBlankSpanDisplayed()) {
            errorMessage = loginPage.getErrorMessage();
            test.pass("Pass");
            status = "Pass";
        } else {
            test.fail("Error message is not found!");
            status = "Fail";
        }
        Assert.assertEquals(status, "Pass", "The test case failed: " + description);
//        String currentUrl = getDriver().getCurrentUrl();
//
//        String[] data = {testCaseId, currentUrl, description, errorMessage, status};
//        String[] headers = {"TestCaseID","URL","Description", "Error Message", "Status"};
//        String[][] allHeaders = {headers};
//
//        ExcelUtils.writeDataToExcel("data/test-data-result.xlsx", "LoginWithInvalidDataPageResult", data, allHeaders);

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



    @DataProvider(name = "loginWithInvalidDataTest")
    public Object[][] getData(){
        return ExcelUtils.getTableArray("data\\test-data.xlsx", "InvalidEmailOrPassTest",false);
    }
}
