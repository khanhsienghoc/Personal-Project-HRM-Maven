package pageObject;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class PIMPageObject extends BasePage {
    private final WebDriver driver;
    public PIMPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
}
