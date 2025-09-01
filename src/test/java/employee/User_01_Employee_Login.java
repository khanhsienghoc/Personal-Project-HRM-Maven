package employee;

import common.Common_Employee_Login;
import commons.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.AllureTestNg;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObject.DashboardPageObject;
import pageObject.LoginPageObject;
import pageObject.PageGeneratorManager;
import reportConfigs.AllureTestListener;
import ultilities.DataUltilities;

import java.util.Arrays;
import java.util.List;

@Listeners({AllureTestNg.class, AllureTestListener.class})
@Test(groups = {"employee"})
public class User_01_Employee_Login extends BaseTest {
    @Parameters({"browser","environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName){
        log.info("Pre-condition: Open Browser "+ browserName + " and navigate to the URL");
        driver = getBrowserDriver(browserName,environmentName);
        loginPage = PageGeneratorManager.getLoginPage(driver);
        fakeData = DataUltilities.getData();

        invalidPassword = fakeData.getInvalidPassword();
        invalidUsername = fakeData.getInvalidUsername();
    }
    @Description("Verify login with valid employee username and invalid password")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void Login_01_Employee_ValidUsername_And_InvalidPassword(){
        log.info("Login_01_Employee_ValidUsername_And_InvalidPassword - Step_01 - Input username with value '{}' and password with invalid value '{}' and click Login button", Common_Employee_Login.username, invalidPassword);
        loginPage.login(Common_Employee_Login.username, invalidPassword);

        log.info("Login_01_Employee_ValidUsername_And_InvalidPassword - Step_02 - Verify the 'Invalid credentials' error message displays");
        Assertions.assertTrue(loginPage.isLoginFailedErrorMessage());
    }
    @Description("Verify login with invalid employee username and valid password")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void Login_02_Employee_InvalidUsername_And_ValidPassword(){
        log.info("Login_02_Employee_InvalidUsername_And_ValidPassword - Step_01 - Input username with invalid value '{}' and password with valid value '{}' and click Login button", invalidUsername, Common_Employee_Login.password);
        loginPage.login(invalidUsername, Common_Employee_Login.password);

        log.info("Login_02_Employee_InvalidUsername_And_ValidPassword - Step_02 - Verify the 'Invalid credentials' error message displays");
        Assertions.assertTrue(loginPage.isLoginFailedErrorMessage());
    }
    @Description("Verify login with invalid employee username and invalid password")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void Login_03_Employee_InvalidUsername_And_InvalidPassword(){
        log.info("Login_03_Employee_InvalidUsername_And_InvalidPassword - Step_01 - Input username with invalid value '{}' and password with invalid value '{}' and click Login button", invalidUsername, invalidPassword);
        loginPage.login(invalidUsername, invalidPassword);

        log.info("Login_03_Employee_InvalidUsername_And_InvalidPassword - Step_02 - Verify the 'Invalid credentials' error message displays");
        Assertions.assertTrue(loginPage.isLoginFailedErrorMessage());
    }
    @Description("Verify login with valid employee username and valid password")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void Login_04_Employee_ValidCredentials(){
        log.info("Login_04_Employee_ValidCredentials - Step_01 - Input username with valid value '{}' and password with valid value '{}' and click Login button", Common_Employee_Login.username, Common_Employee_Login.password);
        loginPage.login(Common_Employee_Login.username, Common_Employee_Login.password);
        homePage = PageGeneratorManager.getDashboardPage(driver);

        log.info("Login_04_Employee_ValidCredentials - Step_02 - Verify the page header 'Dashboard'");
        Assertions.assertEquals("Dashboard", homePage.getPageHeaderByText(driver,"Dashboard"));
    }
    @Description("Verify that some left tab displayed when login as Employee role")
    @Severity(SeverityLevel.NORMAL)
    @Test(dependsOnMethods = "Login_04_Employee_ValidCredentials")
    public void Login_05_Employee_VerifyTabDisplayed(){
        List<String> EXPECTED_MENU_TABS = Arrays.asList(
                "Leave", "Time", "Time", "My Info",
                "Performance", "Dashboard", "Directory", "Claim", "Buzz"
        );
        for (String tab: EXPECTED_MENU_TABS){
            log.info("Login_05_Employee_VerifyTabDisplayed - Step_01 - Verify the {} tab displays", tab);
            Assertions.assertTrue(homePage.isMenuTabDislaysByText(driver,tab));

        }
    }
    @Description("Verify that some left tab undisplayed when login as Employee role")
    @Severity(SeverityLevel.NORMAL)
    @Test(dependsOnMethods = "Login_04_Employee_ValidCredentials")
    public void Login_06_Employee_VerifyTabUndisplayed(){
        List<String> EXPECTED_MENU_TABS = Arrays.asList(
                "PIM", "Recruitment", "Maintenance"
        );
        for (String tab : EXPECTED_MENU_TABS){
            log.info("Login_06_Employee_VerifyTabUndisplayed - Step_01 - Verify the '{}' tab undisplayed", tab);
            Assertions.assertTrue(homePage.getListMenuTabSize(driver,tab) < 1);
        }

    }
    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowserAndDriver();
    }
    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject homePage;
    private String invalidPassword, invalidUsername;
    private DataUltilities fakeData;
}
