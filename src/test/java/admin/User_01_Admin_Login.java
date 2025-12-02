package admin;

import commons.BaseTest;
import commons.EnvironmentConfigManager;
import commons.GlobalConstants;
import io.qameta.allure.Description;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObject.DashboardPageObject;
import pageObject.LoginPageObject;
import pageObject.PageGeneratorManager;
import reportConfigs.AllureTestListener;
import retryConfigs.RetryAnalyzer;
import ultilities.DataUltilities;

import java.util.Arrays;
import java.util.List;

@Listeners({AllureTestNg.class, AllureTestListener.class})
@Test(groups = {"admin"})
public class User_01_Admin_Login extends BaseTest {
    @Parameters({"browser","environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName){
        log.info("Pre-condition: Open Browser "+ browserName + " and navigate to the URL in " + environmentName + " environment");

        driver = getBrowserDriver(browserName, environmentName);
        config = EnvironmentConfigManager.getInstance();
        loginPage = PageGeneratorManager.getLoginPage(driver);
        fakeData = DataUltilities.getData();

        invalidUsername = "invalidUsername" + fakeData.getUsername();
        invalidPassword = fakeData.getInvalidPassword();
    }
    @Test
    @Description("Verify error messages when both username and password are empty")
    public void Login_01_Admin_Empty_Username_And_Password(){
        log.info("Login_01_Admin_Empty_Username_And_Password - Step_01 - Leave username and password empty and click Login button");
        loginPage.login("", "");

        log.info("Login_01_Admin_Empty_Username_And_Password - Step_02 - Verify the error message when leave the username and password empty");
        Assert.assertEquals(GlobalConstants.REQUIRED_ERROR_MESSAGE, loginPage.getErrorMessageByName(driver, "Username"), "Username field should show required error message");
        Assert.assertEquals(GlobalConstants.REQUIRED_ERROR_MESSAGE, loginPage.getErrorMessageByName(driver, "Password"), "Password field should show required error message");
    }
    @Test
    @Description("Verify error message for invalid credentials")
    public void Login_02_Admin_InvalidCredentials(){
        log.info("Login_02_Admin_InvalidCredentials - Step_01 - Input invalid username with value '" + invalidUsername + "' and invalid password with value '" + invalidPassword + "' then click Login button");
        loginPage.login(invalidUsername, invalidPassword);

        log.info("Login_02_Admin_InvalidCredentials - Step_02 - Verify the error message is: '"+ GlobalConstants.INVALID_CREDENTIALS_MESSAGE + "'");
        Assert.assertEquals(GlobalConstants.INVALID_CREDENTIALS_MESSAGE, loginPage.getErrorText(), "Invalid credentials error message should be displayed");
    }
    @Test
    @Description("Verify error message when username is empty")
    public void Login_03_Admin_EmptyUsername(){
        log.info("Login_03_Admin_EmptyUsername - Step_01 - Leave username empty, input password with value '" + config.getAdminPassword() +"' and click Login button");
        loginPage.login("", config.getAdminPassword());

        log.info("Login_03_Admin_EmptyUsername - Step_02 - Verify the error message is: Verify the error message when leave the username empty");
        Assert.assertEquals(GlobalConstants.REQUIRED_ERROR_MESSAGE, loginPage.getErrorMessageByName(driver, "Username"),"Username field should show required error message");
    }
    @Test
    @Description("Verify error message when password is empty")
    public void Login_04_Admin_EmptyPassword(){
        log.info("Login_04_Admin_EmptyPassword - Step_01 - Input username with value '" + config.getAdminUserName() +"' ,leave password empty and click Login button");
        loginPage.login(config.getAdminUserName(), "");

        log.info("Login_04_Admin_EmptyPassword - Step_02 - Verify the error message is: Verify the error message when leave the password empty");
        Assert.assertEquals(GlobalConstants.REQUIRED_ERROR_MESSAGE, loginPage.getErrorMessageByName(driver, "Password"), "Password field should show required error message");
    }
    @Test
    @Description("Verify placeholder text for input fields")
    public void Login_05_Admin_VerifyPlaceholders(){
        log.info("Login_05_Admin_VerifyPlaceholders - Step_01 - Verify the placeholder of the username textbox is 'Username'");
        Assert.assertEquals("Username", loginPage.getPropertyOfTextBoxByName(driver, "placeholder","username"),"Username field placeholder should be 'Username'");

        log.info("Login_05_Admin_VerifyPlaceholders - Step_02 - Verify the placeholder of the password textbox is 'Password'");
        Assert.assertEquals("Password", loginPage.getPropertyOfTextBoxByName(driver, "placeholder","password"),"Password field placeholder should be 'Password'");
    }
    @Test
    @Description("Verify successful admin login")
    public void Login_06_Admin_SuccessLogin(){
        log.info("Login_06_Admin_SuccessLogin - Step_01 - Input username with value '" + config.getAdminUserName() +"' and password with value '" + config.getAdminPassword() +"' and click Login button");
        loginPage.login(config.getAdminUserName(), config.getAdminPassword());

        log.info("Login_06_Admin_SuccessLogin - Step_02 - Verify the page header 'Dashboard'");
        homePage = PageGeneratorManager.getDashboardPage(driver);
        Assert.assertEquals("Dashboard", homePage.getPageHeaderByText(driver,"Dashboard"),"Dashboard header should be displayed after successful login");
    }
    @Test
    @Description("Verify all expected menu tabs are displayed")
    public void Login_07_Admin_VerifyTabDisplays(){
        List<String> EXPECTED_MENU_TABS = Arrays.asList(
                "PIM", "Leave", "Time", "Recruitment", "My Info",
                "Performance", "Dashboard", "Directory", "Maintenance", "Claim", "Buzz"
        );
        for(String tabName : EXPECTED_MENU_TABS){
            log.info("Login_07_Admin_VerifyTabDisplays - Step_01 - Checking visibility of '{}' tab", tabName);
            Assert.assertTrue(homePage.isMenuTabDislaysByText(driver, tabName),
                    String.format("'%s' tab should be visible", tabName));
        }
    }
    @AfterClass(alwaysRun = true)
    public void afterClass(){
        log.info("Cleaning up: Closing browser and driver");
        closeBrowserAndDriver();
    }
    private WebDriver driver;
    private LoginPageObject loginPage;
    private DataUltilities fakeData;
    private String invalidUsername, invalidPassword;
    private DashboardPageObject homePage;
    EnvironmentConfigManager config = EnvironmentConfigManager.getInstance();
}
