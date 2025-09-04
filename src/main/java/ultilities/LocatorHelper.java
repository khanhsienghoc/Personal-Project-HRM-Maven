package ultilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LocatorHelper {
    /**
     * Converts a string-based locator into a Selenium {@link By} object.
     * <p>
     * Supported locator prefixes:
     * <ul>
     *     <li><b>id=</b> - Locate by element ID</li>
     *     <li><b>class=</b> - Locate by class name</li>
     *     <li><b>name=</b> - Locate by element name</li>
     *     <li><b>xpath=</b> - Locate by XPath</li>
     *     <li><b>css=</b> - Locate by CSS selector</li>
     * </ul>
     *
     * @param locatorType A locator string prefixed with its type.
     *                    Example: {@code "id=username"}, {@code "xpath=//div[@class='example']"}
     * @return A {@link By} object that can be used with WebDriver.
     * @throws RuntimeException if the locator type is unsupported.
     */
    public static By getByLocatorType(String locatorType) {
        if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
            return By.id(locatorType.substring(3));
        } else if (locatorType.startsWith("class=") || locatorType.startsWith("Class=") || locatorType.startsWith("CLASS=")) {
            return By.className(locatorType.substring(6));
        } else if (locatorType.startsWith("name=") || locatorType.startsWith("Name=") || locatorType.startsWith("NAME=")) {
            return By.name(locatorType.substring(5));
        } else if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=")) {
            return By.xpath(locatorType.substring(6));
        } else if (locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
            return By.cssSelector(locatorType.substring(4));
        } else {
            throw new RuntimeException("Locator type is not supported: " + locatorType);
        }
    }
    /**
     * Formats a dynamic XPath locator with provided values.
     * <p>
     * This is useful when the locator contains placeholders like <code>%s</code>
     * that need to be replaced at runtime.
     *
     * <p><b>Example:</b></p>
     * <pre>
     * String dynamicLocator = "xpath=//div[text()='%s']";
     * String finalLocator = getDynamicXpath(dynamicLocator, "Login");
     * // Result: "xpath=//div[text()='Login']"
     * </pre>
     *
     * @param locatorType The locator template string (must start with "xpath=").
     *                    Example: {@code "xpath=//button[text()='%s']"}
     * @param values      Values to replace placeholders in the locator.
     * @return A fully formatted locator string.
     */
    public static String getDynamicXpath(String locatorType, String... values) {
        if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=")) {
            locatorType = String.format(locatorType, (Object[]) values);
        }
        return locatorType;
    }
    /**
     * Finds all elements on the page that match the given locator.
     *
     * @param driver       The active {@link WebDriver} instance.
     * @param locatorTypes The locator string with its prefix.
     *                     Example: {@code "css=.menu-item"}, {@code "id=username"}
     * @return A list of matching {@link WebElement} objects.
     */
    public static List<WebElement> findMultipleElements(WebDriver driver, String locatorTypes) {
        return driver.findElements(getByLocatorType(locatorTypes));
    }
}
