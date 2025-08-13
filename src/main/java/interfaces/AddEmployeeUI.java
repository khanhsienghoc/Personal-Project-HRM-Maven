package interfaces;

public class AddEmployeeUI {
    public static final String TEXTBOX_BY_NAME ="xpath=//input[@name='%s']";
    public static final String CREATE_LOGIN_DETAILS_TOGGLE ="xpath=//span[@class='oxd-switch-input oxd-switch-input--active --label-right']";
    public static final String UPLOAD_EMPLOYEE_AVATAR = "xpath=//input[@type ='file']";
    public static final String UPLOAD_AVATAR_ERROR = "xpath=//input[@type='file']//parent::div//following-sibling::span";
    public static final String UPLOADED_EMPLOYEE_AVATAR = "xpath=//div[@class='employee-image-wrapper']/img";
    public static final String ERROR_MESSAGE_BY_NAME = "xpath=//input[@name='%s']//parent::div//following-sibling::span";
}
