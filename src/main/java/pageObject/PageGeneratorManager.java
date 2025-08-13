package pageObject;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    public static DashboardPageObject getDashboardPage(WebDriver driver){
        return new DashboardPageObject(driver);
    }
    public static LoginPageObject getLoginPage (WebDriver driver){
        return new LoginPageObject(driver);
    }
    public static PIMPageObject getPIMPage (WebDriver driver){
        return new PIMPageObject(driver);
    }
    public static AddEmployeePageObject getAddEmployeePage(WebDriver driver){
        return new AddEmployeePageObject(driver);
    }
    public static ResetPasswordPageObject getResetPassPage (WebDriver driver){
        return new ResetPasswordPageObject(driver);
    }
    public static MyInfoPageObject getMyInfoPage (WebDriver driver){
        return new MyInfoPageObject(driver);
    }
    public static PersonalDetailsPageObject getPersonalDetails(WebDriver driver){
        return new PersonalDetailsPageObject(driver);
    }
}
