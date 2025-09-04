package pageObject;

import commons.BasePage;
import commons.GlobalConstants;
import interfaces.BasePageUI;
import interfaces.MyInfoPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ultilities.Waiter;

import java.time.Duration;

public class MyInfoPageObject extends BasePage {
    private final WebDriver driver;
    private final Waiter waiter;

    public MyInfoPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
        this.waiter = new Waiter(driver);
    }
    /**
     * Clicks on the left tab by visible text and navigates to the corresponding page object.
     *
     * @param text The visible text of the tab (e.g. "Personal Details").
     * @param <T>  The expected Page Object returned after clicking the tab.
     * @return     The page object corresponding to the selected tab.
     */
    @Step("Click on the left tab with text '{0}'")
    @SuppressWarnings("unchecked")
    public  <T extends BasePage> T  clickOnLeftTabByText(String text){
        waiter.waitForVisibilityOfElement(MyInfoPageUI.HYPERLINK_LEFT_TAB_BY_TEXT, text);
        clickToElement(driver, MyInfoPageUI.HYPERLINK_LEFT_TAB_BY_TEXT, text);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
        waiter.waitForInvisibilityOfListElement(BasePageUI.LOADING_SPINNER);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        if (text.equals("Personal Details")) {
            return (T) PageGeneratorManager.getPersonalDetails(driver);
        }
        return (T) this;
    }
    /**
     * Gets the displayed title text of a left tab by its visible text.
     *
     * @param text The visible text of the tab.
     * @return     The tab's displayed text content.
     */
    @Step("Get the title text of the left tab with text '{0}'")
    public String getLeftTabTitleByText(String text){
        waiter.waitForVisibilityOfElement(MyInfoPageUI.HYPERLINK_LEFT_TAB_BY_TEXT, text);
        return getElementText(driver, MyInfoPageUI.HYPERLINK_LEFT_TAB_BY_TEXT, text);
    }
}
