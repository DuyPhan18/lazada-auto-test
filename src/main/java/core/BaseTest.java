package core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.junit.Before;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.Set;

public class BaseTest {

    private WebDriver driver;
    private Set<Cookie> cookies;
    protected ExtentReports extent;
    protected ExtentTest test;

    @Before
    public void beforeSuite(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterSuite
    public void afterSuite(){
        if (driver != null){
            try {
                driver.close();
                driver.quit();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
    // Khởi tạo ExtentReports trong lớp BaseTest
    public void setupExtentReports() {
        extent = ExtentManager.getInstance();
    }

    // Phương thức này sẽ được gọi trong các phương thức @BeforeClass của các lớp kiểm tra
    public void setup() {
        setupExtentReports();
    }

    // Phương thức để lấy đối tượng ExtentReports, có thể cần thiết trong các lớp kiểm tra con
    public ExtentReports getExtentReports() {
        return extent;
    }

    // Phương thức để tạo đối tượng ExtentTest cho từng bài kiểm tra
    public ExtentTest createTest(String testName) {
        test = extent.createTest(testName);
        return test;
    }

    public WebDriver getDriver(){
        return driver;
    }

    public void saveCookies() {
            cookies = driver.manage().getCookies();
        }

//    public void loadCookies() {
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                driver.manage().addCookie(cookie);
//            }
//        }
//    }
        public void loadCookies(String currentDomain) {
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getDomain().equals(currentDomain)) {
                        driver.manage().addCookie(cookie);
                    }
                }
            }
    }
}
