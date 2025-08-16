package pageObject;

import commons.BasePage;
import interfaces.LoginPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginPageObject extends BasePage {
    private final WebDriver driver;
    public LoginPageObject(WebDriver driver){
        this.driver = driver;
    }
    /**
     * Clicks on the Login button.
     */
    @Step("Click Login button")
    public void clickToLoginButton(){
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }
    /**
     * Clicks on the "Forgot your password?" hyperlink.
     *
     * @return {@link ResetPasswordPageObject} The Reset Password page object.
     */
    @Step("Click 'Forgot your password?'")
    public ResetPasswordPageObject clickForgotPasswordHyperlink(){
        waitForElementClickable(driver, LoginPageUI.FORGOT_PASSWORD_HYPERLINK);
        clickToElement(driver, LoginPageUI.FORGOT_PASSWORD_HYPERLINK);
        return PageGeneratorManager.getResetPassPage(driver);
    }
    /**
     * Checks whether the login failed error message is displayed.
     *
     * @return {@code true} if error message is displayed; otherwise {@code false}.
     */
    @Step("Check if login failed error message is displayed")
    public boolean isLoginFailedErrorMessage(){
        waitElementVisible(driver, LoginPageUI.INVALID_CREDENTIALS_ERROR_MESSAGE);
        return isElementDisplayed(driver, LoginPageUI.INVALID_CREDENTIALS_ERROR_MESSAGE);
    }
    /**
     * Gets the error message text when login fails.
     *
     * @return The error message string.
     */
    @Step("Get the login failed error message text")
    public String getErrorText(){
        waitElementVisible(driver, LoginPageUI.INVALID_CREDENTIALS_ERROR_MESSAGE);
        return getElementText(driver, LoginPageUI.INVALID_CREDENTIALS_ERROR_MESSAGE);
    }
    /**
     * Logs in using the provided username and password.
     *
     * @param username The username to input.
     * @param password The password to input.
     */
    @Step("Login with username '{0}' and password '{1}' and click Login button")
    public void login(String username, String password) {
        Map<String, String> fields
                = new HashMap<String, String>();
        fields.put("Username", username);
        fields.put("Password", password);
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            log.info("Input " + key + " with value " + value);
            inputToTextBoxByText(driver, key, value);
        }
        clickToLoginButton();
    }
}
