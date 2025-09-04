package pageObject;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class DashboardPageObject extends BasePage {
    private final WebDriver driver;

    public DashboardPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }




}
