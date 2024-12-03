package tests;

import com.aventstack.extentreports.ExtentTest;
import core.BaseTest;
import core.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CartPage;
import java.net.MalformedURLException;
import java.net.URL;

public class CartPageTest extends BaseTest {
    @BeforeClass
    public void setup() {
        super.setup();

        CartPage cartPage = new CartPage(getDriver());

        cartPage.navigateToUrl("https://member.lazada.vn/user/login");
        if (!cartPage.isLogIned()) {
            cartPage.loginWithFacebook("", ""); // Thay bằng thông tin đăng nhập thực tế
        }
        cartPage.waitForLoginSuccess();
        saveCookies();
    }

    @Test(dataProvider = "cartPageTestData")
    public void cardPageTest(String testCaseId, String url) throws MalformedURLException {
        ExtentTest testCartPage = createTest("Cart Page Test - " + testCaseId);
        CartPage cartPage = new CartPage(getDriver());

        String currentDomain = new URL(url).getHost();
        loadCookies(currentDomain);
        cartPage.navigateToUrl(url);
        cartPage.clickToSwitchLang();


        testCartPage.info("Navigated to URL: " + url);

        String status = "fail";
        // Số lượng sản phẩm trước khi thêm vào giỏ hàng
        int initialItemCount = cartPage.getCartItemCount();

        if (cartPage.addProductsToCart()) {
            if (cartPage.verifyAddToCartDone()) {
                // Số lượng sản phẩm sau khi thêm vào giỏ hàng
                int finalItemCount = cartPage.getCartItemCount();
                if (finalItemCount == initialItemCount + 1) {
                    testCartPage.pass("Added product to cart successfully");
                    status = "pass";
                } else {
                    testCartPage.fail("Item count in cart did not increase after adding product");
                }

            } else {
                testCartPage.fail("Failed to add product to cart");
            }

        }

        Assert.assertEquals(status,"pass","Failed to add");

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


    @DataProvider(name = "cartPageTestData")
    public Object[][] getData() {
        return ExcelUtils.getTableArray("data\\test-data.xlsx", "CartPageTest", false);
    }
}
