package ultilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static ultilities.LocatorHelper.*;

public class Waiter  {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public Waiter(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    /**
     * Waits until a single element is visible on the page.
     *
     * @param locator The locator of the element (e.g., "xpath=//div[@id='example']").
     * @return The visible WebElement once it appears.
     */
    public WebElement waitForVisibilityOfElement(String locator) {
        By by = getByLocatorType(locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    /**
     * Waits until all elements matching the given locator are visible on the page.
     *
     * @param locatorType The locator of the elements (e.g., "css=.menu-item").
     * @return A list of visible WebElements.
     */
    public List<WebElement> waitForVisibilityOfAllElements(String locatorType) {
        By by = getByLocatorType(locatorType);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }
    /**
     * Waits until a single element is clickable on the page.
     *
     * @param locator The locator of the element (e.g., "id=submit-button").
     */
    public void waitForElementToBeClickable(String locator) {
        By by = getByLocatorType(locator);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    /**
     * Waits until a dynamic element is clickable on the page.
     *
     * @param locator        The locator template (e.g., "xpath=//button[text()='%s']").
     * @param dynamicValues  The values to replace placeholders in the locator.
     */
    public void waitForElementToBeClickable(String locator, String... dynamicValues) {
        By by = getByLocatorType(getDynamicXpath(locator, dynamicValues));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    /**
     * Waits until a single dynamic element is visible on the page.
     *
     * @param locatorType    The locator template (e.g., "xpath=//input[@name='%s']").
     * @param dynamicValues  The values to replace placeholders in the locator.
     * @return The visible WebElement once it appears.
     */
    public WebElement waitForVisibilityOfElement(String locatorType, String... dynamicValues) {
        By by = getByLocatorType(getDynamicXpath(locatorType, dynamicValues));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    /**
     * Waits until a dynamic element is present in the DOM (does not have to be visible).
     *
     * @param locatorType    The locator template (e.g., "xpath=//div[@class='%s']").
     * @param dynamicValues  The values to replace placeholders in the locator.
     */
    public void waitForPresenceOfElement(String locatorType, String... dynamicValues) {
        By by = getByLocatorType(getDynamicXpath(locatorType, dynamicValues));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    /**
     * Waits until an element is present in the DOM (does not have to be visible).
     *
     * @param locatorType The locator of the element (e.g., "id=username").
     */
    public void waitForPresenceOfElement(String locatorType) {
        By by = getByLocatorType(locatorType);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    /**
     * Waits until all elements matching the given locator become invisible.
     *
     * @param locatorType The locator of the elements (e.g., "css=.loading-spinner").
     */
    public void waitForInvisibilityOfListElement(String locatorType) {
        wait.until(ExpectedConditions.invisibilityOfAllElements(findMultipleElements(driver, locatorType)));
    }
    /**
     * Waits until all dynamic elements matching the given locator become invisible.
     *
     * @param locatorType    The locator template (e.g., "xpath=//div[@class='%s']").
     * @param dynamicValues  The values to replace placeholders in the locator.
     */
    public void waitForInvisibilityOfListElement(String locatorType, String... dynamicValues) {
        wait.until(ExpectedConditions.invisibilityOfAllElements(
                findMultipleElements(driver, getDynamicXpath(locatorType, dynamicValues))
        ));
    }

}
