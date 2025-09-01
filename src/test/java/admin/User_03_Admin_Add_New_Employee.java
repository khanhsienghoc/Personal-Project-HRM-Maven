package admin;

import commons.BaseTest;
import commons.EnvironmentConfigManager;
import commons.GlobalConstants;
import dataAccessObject.EmployeeDataAccess;
import database.BaseDBHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.AllureTestNg;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObject.*;
import reportConfigs.AllureTestListener;
import ultilities.DataUltilities;

import java.sql.Array;
import java.sql.SQLException;
import java.util.*;

import static commons.TestGuard.skipIfDBDisabled;

@Listeners({AllureTestNg.class, AllureTestListener.class})
@Test(groups = {"admin"})
public class User_03_Admin_Add_New_Employee extends BaseTest {
    @Parameters({"browser","environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName){
        log.info("Pre-condition - Step_01 - Open Browser "+ browserName + " and navigate to the URL in " + environmentName + " environment");
        driver = getBrowserDriver(browserName, environmentName);
        config = EnvironmentConfigManager.getInstance();
        employeeDAo = EmployeeDataAccess.employeeData();
        loginPage = PageGeneratorManager.getLoginPage(driver);

        log.info("Pre-condition - Step_01 - Login with Admin username: '" + config.getAdminPassword() + "and Admin password: '" + config.getAdminPassword() + "and click Login button");
        loginPage.login(config.getAdminUserName(),config.getAdminPassword() );
        dashboardPage = PageGeneratorManager.getDashboardPage(driver);

        log.info("Pre-condition - Step_02 - Verify the page header 'Dashboard'");
        Assertions.assertEquals("Dashboard", dashboardPage.getPageHeaderByText(driver,"Dashboard"));

        log.info("Pre-condition - Step_03 - Click to PIM");
        pimPage = dashboardPage.clickToMenuByText(driver, "PIM");

        log.info("Pre-condition - Step_04 - Click Add button");
        pimPage.clickToButtonByText(driver, "Add");
        getAddEmployeePage= PageGeneratorManager.getAddEmployeePage(driver);

        fakeData = DataUltilities.getData();
        firstName = fakeData.getFirstName();
        middleName = fakeData.getMiddleName();
        lastName = fakeData.getLastName();
        username = fakeData.getUsername();
        password = fakeData.getValidPassword();
        invalidPassword = fakeData.getInvalidPassword();
        employeeID = fakeData.getEmployeeID();
    }
    @Description("Verify error message when leave required fields empty")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void Admin_AddNewEmployee_01_EmptyFields() {
        log.info("AddNewEmployee_01_EmptyFields - Step_01 - Click on the 'Create Login Details' toggle");
        getAddEmployeePage.checkToCreateLoginDetailsToggle();

        log.info("AddNewEmployee_01_EmptyFields - Step_02 - Click on Save button without inputting any text in First Name and Last Name fields");
        getAddEmployeePage.clickToButtonByText(driver, "Save");

        List<String> fields = Arrays.asList("firstName", "lastName", "Username", "Password","Confirm Password");

        int i = 3;
        String actualError;
        String expectedError;
        for(String field : fields){
            log.info("AddNewEmployee_01_EmptyFields - Step_0"+i+" - Verify the error message in the "+field+" field");
            System.out.println("the field" + field);
            if(Objects.equals(field, "firstName") || Objects.equals(field, "lastName")){
                expectedError = GlobalConstants.REQUIRED_ERROR_MESSAGE;
                actualError = getAddEmployeePage.getErrorMessageOfTextBoxByName(field);
            }else if(Objects.equals(field, "Confirm Password")) {
                expectedError = GlobalConstants.UNMATCHED_CONFIRM_PASSWORD;
                actualError = getAddEmployeePage.getErrorMessageByName(driver, field);
            } else{
                expectedError = GlobalConstants.REQUIRED_ERROR_MESSAGE;
                actualError = getAddEmployeePage.getErrorMessageByName(driver, field);
            }
          Assertions.assertEquals(expectedError, actualError);
        } i++;
    }
    @Description("Verify First Name, Middle Name and Last Name fields are empty")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void Admin_AddNewEmployee_02_VerifyPlaceHolders() {
        int i = 1;
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("First Name", "firstName");
        fields.put("Middle Name", "middleName");
        fields.put("Last Name", "lastName");
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            String placeholderName = entry.getKey();
            String field = entry.getValue();
            log.info("AddNewEmployee_02_VerifyPlaceHolders - Step_0" + i + " - Verify the placeholder of the " + field + " textbox is " + placeholderName);
            Assertions.assertEquals(placeholderName, getAddEmployeePage.getPropertyOfTextBoxByName(driver, "placeholder", field));
        }
    }
    @Description("verify input the unmatched password in Confirm Password field")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void Admin_AddNewEmployee_03_NotMatchConfirmPassword() {
        log.info("Admin_AddNewEmployee_03_NotMatchConfirmPassword - Step_01 - Input employee information");
        getAddEmployeePage.fillEmployeeInformation(firstName, middleName, lastName, employeeID);

        log.info("Admin_AddNewEmployee_03_NotMatchConfirmPassword - Step_02 - Input username, password and invalid confirm password");
        getAddEmployeePage.createLoginDetails(username, password, invalidPassword);

        log.info("Admin_AddNewEmployee_03_NotMatchConfirmPassword - Step_03 - Click Save button");
        getAddEmployeePage.clickToButtonByText(driver, "Save");

        log.info("Admin_AddNewEmployee_03_NotMatchConfirmPassword - Step_04 - Verify the error message in the Confirm Password field");
        Assertions.assertEquals(GlobalConstants.UNMATCHED_CONFIRM_PASSWORD, loginPage.getErrorMessageByName(driver, "Confirm Password"));
    }
    @Description("verify upload invalid files in the Employee avatar")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void Admin_AddNewEmployee_04_VerifyUploadInvalidFile() {
        List<String> invalidFiles = Arrays.asList(docFile, txtFile);
        for(String file : invalidFiles){
            int index = invalidFiles.indexOf(file) + 1;
            log.info("Admin_AddNewEmployee_04_VerifyUploadInvalidFile - Step_01."+index+ "- Upload " + file + " file");
            getAddEmployeePage.uploadEmployeeAvatar(GlobalConstants.UPLOAD_FILE + file);

            log.info("Admin_AddNewEmployee_04_VerifyUploadInvalidFile - Step_02."+index+ "- Verify the error message: 'File type not allowed'");
            Assertions.assertEquals("File type not allowed", getAddEmployeePage.getErrorMessageForEmployeeAvatar());
        }
    }
    @Description("verify upload more than 1MB files in the Employee avatar")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void Admin_AddNewEmployee_05_VerifyUploadInvalidFileMoreThan1MB() {
        List<String> invalidFiles = Arrays.asList(pngFileMoreThan1MB, gifFileMoreThan1MB, jpgFileMoreThan1MB);
        for(String file : invalidFiles){
            int index = invalidFiles.indexOf(file) + 1;
            log.info("Admin_AddNewEmployee_05_VerifyUploadInvalidFileMoreThan1MB - Step_01."+index+ "- Upload " + file + " file");
            getAddEmployeePage.uploadEmployeeAvatar(GlobalConstants.UPLOAD_FILE + file);

            log.info("Admin_AddNewEmployee_05_VerifyUploadInvalidFileMoreThan1MB - Step_02."+index+ "- Verify the error message: 'File type not allowed'");
            Assertions.assertEquals("Attachment Size Exceeded", getAddEmployeePage.getErrorMessageForEmployeeAvatar());
        }
    }
    @Description("Verify upload difference type Image")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void Admin_AddNewEmployee_06_VerifyUploadValidTypeOfImage() {
        List<String> validFiles = Arrays.asList(gifFile, pngFile, jpegFile);
        for(String file : validFiles){
            int index = validFiles.indexOf(file) + 1;
            log.info("Admin_AddNewEmployee_06_VerifyUploadValidTypeOfImage - Step_01."+index+ "- Upload " + file + " file");
            getAddEmployeePage.uploadEmployeeAvatar(GlobalConstants.UPLOAD_FILE + file);

            log.info("Admin_AddNewEmployee_06_VerifyUploadValidTypeOfImage - Step_02."+index+ "- Verify the " + file +" is uploaded");
            Assertions.assertTrue(getAddEmployeePage.isEmployeeImageUploaded());

            log.info("Admin_AddNewEmployee_06_VerifyUploadValidTypeOfImage - Step_03."+index+ "- Verify there is no error show");
            Assertions.assertTrue(getAddEmployeePage.getSizeOfErrorInEmployeeAvatar() < 1);
        }
    }
    @Description("Verify add new employee successfully")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void Admin_AddNewEmployee_07_AddNewEmployeeSuccess() {
        log.info("Admin_AddNewEmployee_07_AddNewEmployeeSuccess - Step_01 - Click PIM");
        pimPage = getAddEmployeePage.clickToMenuByText(driver, "PIM");

        log.info("Admin_AddNewEmployee_07_AddNewEmployeeSuccess - Step_02 - Click Add");
        pimPage.clickToButtonByText(driver, "Add");
        getAddEmployeePage= PageGeneratorManager.getAddEmployeePage(driver);

        log.info("Admin_AddNewEmployee_07_AddNewEmployeeSuccess - Step_03 - Input employee information");
        getAddEmployeePage.fillEmployeeInformation(firstName, middleName, lastName, employeeID);

        log.info("Admin_AddNewEmployee_07_AddNewEmployeeSuccess - Step_04 - Click 'Create Login Details' toggle");
        getAddEmployeePage.checkToCreateLoginDetailsToggle();

        log.info("Admin_AddNewEmployee_07_AddNewEmployeeSuccess - Step_05 - Input username with value '{}', password and confirm password with value '{}'", username, password);
        getAddEmployeePage.createLoginDetails(username, password, password);

        log.info("Admin_AddNewEmployee_07_AddNewEmployeeSuccess - Step_06 - Upload an employee avatar");
        getAddEmployeePage.uploadEmployeeAvatar(GlobalConstants.UPLOAD_FILE + gifFile);

        log.info("Admin_AddNewEmployee_07_AddNewEmployeeSuccess - Step_07 - verify the employee avatar");
        Assertions.assertTrue(getAddEmployeePage.isEmployeeImageUploaded());

        log.info("Admin_AddNewEmployee_07_AddNewEmployeeSuccess - Step_08 - Click Save button");
        getAddEmployeePage.clickToButtonByText(driver, "Save");
        getPersonalPage = PageGeneratorManager.getPersonalDetails(driver);

        log.info("Admin_AddNewEmployee_07_AddNewEmployeeSuccess - Step_09 - Verify success message show");
        Assertions.assertTrue(pimPage.isSuccessPopUpShow(driver));
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put(firstName, "firstName");
        fields.put(middleName, "middleName");
        fields.put(lastName,"lastName" );
        fields.put(String.valueOf(employeeID),"Employee Id");
        int i = 10;
        for (Map.Entry<String, String> entry: fields.entrySet()){
            String expectedValue = entry.getKey();
            String field = entry.getValue();
            log.info("Admin_AddNewEmployee_07_AddNewEmployeeSuccess - Step_"+i+" - Verify "+field+" value is: " + expectedValue);
            if (!Objects.equals(field, "Employee Id")){
                Assertions.assertEquals(expectedValue, getPersonalPage.getPropertyOfTextBoxByName(driver, "value",field));
            } else{
                Assertions.assertEquals(expectedValue, getPersonalPage.getPropertyOfTextBoxByText(driver, "value",field));
            }
        }
    }
    @Description("Verify the employee data exist on database")
    @Severity(SeverityLevel.NORMAL)
    @Test()
    public void Admin_AddNewEmployee_08_IsEmployeeExist() throws SQLException {
        skipIfDBDisabled();
        BaseDBHelper.connect();
        log.info("Admin_AddNewEmployee_08_IsEmployeeExist - Step_01 - Verify the employee record exists in database with employee id:" + employeeID);
        boolean isExist = employeeDAo.isEmployeeExist(employeeID);
        Assertions.assertTrue(isExist, "Employee record does NOT exist in the database!");
    }
    @AfterClass(alwaysRun = true)
    public void afterClass(){
        log.info("Cleaning up: Closing browser and driver");
        closeBrowserAndDriver();
    }
    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private PIMPageObject pimPage;
    private AddEmployeePageObject getAddEmployeePage;
    private PersonalDetailsPageObject getPersonalPage;
    private String firstName, middleName, lastName, username, password, invalidPassword;
    private DataUltilities fakeData;
    private String docFile = "DocFile.doc";
    private String txtFile = "TxtFile.txt";
    private String gifFile = "GifImage.gif";
    private String pngFile = "PngImage.png";
    private String jpegFile = "JpegImage.jpeg";
    private String pngFileMoreThan1MB = "PngImageMoreThan1MB.png";
    private String gifFileMoreThan1MB = "GifImageMoreThan1MB.gif";
    private String jpgFileMoreThan1MB = "JpgImageMoreThan1MB.jpg";
    private String employeeID;
    EnvironmentConfigManager config = EnvironmentConfigManager.getInstance();
    EmployeeDataAccess employeeDAo = new EmployeeDataAccess();
}

