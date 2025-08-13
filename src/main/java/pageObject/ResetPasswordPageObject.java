package pageObject;
import commons.BasePage;
import interfaces.ResetPasswordUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPageObject extends BasePage {
    private final WebDriver driver;
    public ResetPasswordPageObject(WebDriver driver){
        this.driver = driver;
    }
    /**
     * Get the title of the Reset Password form.
     *
     * @return The title text of the Reset Password form.
     */
    @Step("Get title of Reset Password form")
    public String getTitleOfResetPassword(){
        waitElementVisible(driver, ResetPasswordUI.FORGOT_PASSWORD_TITLE);
        return getElementText(driver, ResetPasswordUI.FORGOT_PASSWORD_TITLE);
    }
    /**
     * Get the body text of the Reset Password form by specified text.
     *
     * @param text The specific text locator part to identify the body element.
     * @return The body text of the Reset Password form.
     */
    @Step("Get body text '{0}' of Reset Password form")
    public String getBodyTextOfResetPassword(String text){
        waitElementVisible(driver, ResetPasswordUI.FORGOT_PASSWORD_BODY_BY_TEXT, text);
        return getElementText(driver, ResetPasswordUI.FORGOT_PASSWORD_BODY_BY_TEXT, text);
    }
}
