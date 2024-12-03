package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import core.BaseTest;
import core.ExcelUtils;
import core.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.RegisterPage;

public class RegisterPageTest extends BaseTest {
    @BeforeClass
    public void setup() {
        super.setup();
    }
    @Test(dataProvider = "registerPageTestData")
    public void registerPageTest(String testCaseId, String url){
        //ExtentTest test = createTest("Register page Test - " + testCaseId);

        RegisterPage registerPage = new RegisterPage(getDriver());
        registerPage.navigateToUrl(url);
        registerPage.navigateToRegisterPage();

        // currentUrl = getDriver().getCurrentUrl();
        //test.info("Navigated to URL: " + currentUrl);

        boolean isPhoneInputPresent = registerPage.isPhoneInputPresent();
        boolean isFullNameInputPresent = registerPage.isFullNameInputPresent();
        boolean isPassInputPresent = registerPage.isPassInputPresent();
        boolean isDayDropdownPresent = registerPage.isDayDropdownPresent();
        boolean isMonthDropdownPresent = registerPage.isMonthDropdownPresent();
        boolean isYearDropdownPresent = registerPage.isYearDropdownPresent();
        boolean isGenderDropdownPresent = registerPage.isGenderDropdownPresent();
        boolean isCheckBoxNewsLetterPresent = registerPage.isCheckBoxNewsLetterPresent();

//        if (isFullNameInputPresent) {
//            test.pass("Full Name input is present");
//        } else {
//            test.fail("Full Name input is not present");
//        }
//
//        if (isPhoneInputPresent) {
//            test.pass("Phone input is present");
//        } else {
//            test.fail("Phone input is not present");
//        }
//
//        if (isPassInputPresent) {
//            test.pass("Password input is present");
//        } else {
//            test.fail("Password input is not present");
//        }
//
//        if (isDayDropdownPresent) {
//            test.pass("Day dropdown is present");
//        } else {
//            test.fail("Day dropdown is not present");
//        }
//
//        if (isMonthDropdownPresent) {
//            test.pass("Month dropdown is present");
//        } else {
//            test.fail("Month dropdown is not present");
//        }
//
//        if (isYearDropdownPresent) {
//            test.pass("Year dropdown is present");
//        } else {
//            test.fail("Year dropdown is not present");
//        }
//
//        if (isGenderDropdownPresent) {
//            test.pass("Gender dropdown is present");
//        } else {
//            test.fail("Gender dropdown is not present");
//        }
//
//        if (isCheckBoxNewsLetterPresent) {
//            test.pass("Checkbox Newsletter is present");
//        } else {
//            test.fail("Checkbox Newsletter is not present");
//        }
        Assert.assertTrue(isFullNameInputPresent,"False");
        Assert.assertTrue(isPhoneInputPresent,"False");
        Assert.assertTrue(isPassInputPresent,"False");
        Assert.assertTrue(isDayDropdownPresent,"False");
        Assert.assertTrue(isMonthDropdownPresent,"False");
        Assert.assertTrue(isYearDropdownPresent,"False");
        Assert.assertTrue(isGenderDropdownPresent,"False");
        Assert.assertTrue(isCheckBoxNewsLetterPresent,"False");
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

    @DataProvider(name = "registerPageTestData")
    public Object[][] getData(){
        return ExcelUtils.getTableArray("data\\test-data.xlsx","RegisterPageTest", false);
    }
}
