package steps;

import commons.BaseTest;
import commons.DriverManager;
import commons.GlobalConstants;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import pageObject.*;

import java.util.Arrays;
import java.util.List;

public class LoginSteps extends BaseTest{
    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    @Given("the admin login page is opened")
    public void the_admin_login_page_is_opened() {
        driver = DriverManager.getDriver();
        loginPage = PageGeneratorManager.getLoginPage(driver);
        Assert.assertTrue(loginPage.isLoginPageShowUp(driver), "Admin login page should be opened");
    }
    @When("I login with username {string} and password {string}")
    public void i_login_with_username_and_password(String username, String password) {
        if (username.equals("<validUsername>")) {
            username = GlobalConstants.TESTING_ADMIN_USERNAME;
        }
        if (password.equals("<validPassword>")) {
            password = GlobalConstants.TESTING_ADMIN_PASSWORD;
        }
        loginPage.login(username, password);
    }
    @Then("the invalid credential message should be displayed")
    public void the_invalid_credential_message_should_be_displayed() {
        Assert.assertEquals(loginPage.getErrorText(), GlobalConstants.INVALID_CREDENTIALS_MESSAGE);
    }

    @Then("the required message should be displayed for {string}")
    public void the_required_message_should_be_displayed_for(String fieldName) {
        Assert.assertEquals(loginPage.getErrorMessageByName(driver, fieldName), GlobalConstants.REQUIRED_ERROR_MESSAGE);
    }
    @Then("the dashboard header should be {string}")
    public void the_dashboard_header_should_be(String header) {
        dashboardPage = PageGeneratorManager.getDashboardPage(driver);
        Assert.assertEquals(dashboardPage.getPageHeaderByText(driver, header), header);
    }
    @Then("the following menu tabs should be visible:")
    public void the_following_menu_tabs_should_be_visible(DataTable dataTable) {
        dashboardPage = PageGeneratorManager.getDashboardPage(driver);
        List<String> expectedTabs = dataTable.asList();

        for (String tabName : expectedTabs) {
            Assert.assertTrue(
                    dashboardPage.isMenuTabDislaysByText(driver, tabName),
                    String.format("'%s' tab should be visible", tabName)
            );
        }
    }
    @Then("the placeholder of {string} should be {string}")
    public void verify_placeholder_of_input_field(String fieldName, String expectedPlaceholder) {
        loginPage = PageGeneratorManager.getLoginPage(driver);
        String actualPlaceholder =
                loginPage.getPropertyOfTextBoxByName(driver, "placeholder", fieldName);
        Assert.assertEquals(actualPlaceholder, expectedPlaceholder,
                String.format("Placeholder of '%s' should be '%s'", fieldName, expectedPlaceholder)
        );
    }
}
