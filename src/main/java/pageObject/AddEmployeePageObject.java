package pageObject;

import commons.BasePage;
import commons.GlobalConstants;
import interfaces.AddEmployeeUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class AddEmployeePageObject extends BasePage {
    private final WebDriver driver;

    public AddEmployeePageObject(WebDriver driver){
        this.driver = driver;
    }
    /**
     * Inputs value into a textbox identified by its name attribute.
     *
     * @param name The name attribute of the textbox.
     * @param text The text to input.
     */
    @Step("In the field {0}, input the value '{1}'")
    public void inputEmployeeInformationByName(String name, String text){
        waitElementVisible(driver, AddEmployeeUI.TEXTBOX_BY_NAME, name);
        sendKeyToElement(driver, AddEmployeeUI.TEXTBOX_BY_NAME,text, name);
    }
    /**
     * Checks the "Create Login Details" toggle button.
     */
    @Step("Check the 'Create Login Details' toggle")
    public void checkToCreateLoginDetailsToggle(){
        waitForElementClickable(driver, AddEmployeeUI.CREATE_LOGIN_DETAILS_TOGGLE);
        clickToElementByJS(driver, AddEmployeeUI.CREATE_LOGIN_DETAILS_TOGGLE);
    }
    /**
     * Uploads an avatar image for the employee.
     *
     * @param filePath The file path to the image.
     */
    public void uploadEmployeeAvatar(String filePath){
        waitElementPresence(driver, AddEmployeeUI.UPLOAD_EMPLOYEE_AVATAR);
        uploadOneFile(driver, AddEmployeeUI.UPLOAD_EMPLOYEE_AVATAR, filePath);
    }
    /**
     * Gets the error message shown when uploading an invalid employee avatar.
     *
     * @return The error message text.
     */
    public String getErrorMessageForEmployeeAvatar(){
        waitElementVisible(driver, AddEmployeeUI.UPLOAD_AVATAR_ERROR);
        return getElementText(driver, AddEmployeeUI.UPLOAD_AVATAR_ERROR);
    }
    /**
     * Checks whether the employee image has been uploaded successfully.
     *
     * @return {@code true} if image is uploaded; {@code false} otherwise.
     */
    public boolean isEmployeeImageUploaded(){
        waitElementVisible(driver, AddEmployeeUI.UPLOADED_EMPLOYEE_AVATAR);
        String src = getAttributeValue(driver, AddEmployeeUI.UPLOADED_EMPLOYEE_AVATAR, "src");
        if (src.startsWith("data:image")){
            return true;
        }
        return false;
    }
    /**
     * Gets the error message of a specific textbox by its name.
     *
     * @param textboxName The name of the textbox.
     * @return The error message text.
     */
    public String getErrorMessageOfTextBoxByName(String textboxName){
        waitElementVisible(driver, AddEmployeeUI.ERROR_MESSAGE_BY_NAME,textboxName );
        return getElementText(driver, AddEmployeeUI.ERROR_MESSAGE_BY_NAME, textboxName);
    }
    /**
     * Gets the number of error messages related to the employee avatar upload.
     *
     * @return The number of error messages.
     */
    public int getSizeOfErrorInEmployeeAvatar(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
        int size = getListElement(driver, AddEmployeeUI.UPLOAD_AVATAR_ERROR).size();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        return size;
    }
    /**
     * Fills out the "Create Login Details" section with provided credentials.
     *
     * @param username The username to input.
     * @param password The password to input
     * @param confirmPassword The password to confirm.
     */
    @Step("Fill Login Details: Username={username}, Password={password}, Confirm Password={confirmPassword}")
    public void createLoginDetails(String username, String password, String confirmPassword){
        log.info("Input username with value: " + username);
        inputToTextBoxByText(driver, "Username", username);
        log.info("Input Password Name with value: " + password);
        inputToTextBoxByText(driver, "Password", password);
        log.info("Input Confirm Password Name with value: " + confirmPassword);
        inputToTextBoxByText(driver, "Confirm Password", confirmPassword);
    }
    /**
     * Enter employee information into the form.
     *
     * @param firstName   employee first name
     * @param middleName  employee middle name
     * @param lastName    employee last name
     * @param employeeID  employee ID
     */
    @Step("Fill employee info: FirstName={firstName}, MiddleName={middleName}, LastName={lastName}, EmployeeID={employeeID}")
    public void fillEmployeeInformation(String firstName, String middleName, String lastName, String employeeID){
        log.info("Input First Name with value: " + firstName);
        inputEmployeeInformationByName("firstName", firstName);
        log.info("Input Middle Name with value: " + middleName);
        inputEmployeeInformationByName("middleName", middleName);
        log.info("Input Last Name with value: " + lastName);
        inputEmployeeInformationByName("lastName", lastName);
        log.info("Input Employee ID with value: " + employeeID);
        inputToTextBoxByText(driver, "Employee Id", employeeID);
    }
}
