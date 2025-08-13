package interfaces;

public class BasePageUI {
    public static final String TEXTBOX_BY_TEXT = "xpath=//label[text()='%s']//parent::div//following-sibling::div//input";
    public static final String TEXTBOX_BY_NAME = "xpath=//input[@name='%s']";
    public static final String ERROR_MESSAGE_BY_TEXTBOX_TEXT ="xpath=//label[text()='%s']//parent::div//following-sibling::span";
    public static final String MENU_BY_TEXT = "xpath=//span[text()='%s']//parent::a";
    public static final String BUTTON_BY_TEXT ="xpath=//button[contains(.,'%s')]";
    public static final String SUCCESS_SAVE_POPUP ="xpath=//div[@id='oxd-toaster_1']";
    public static final String PROFILE_DROPDOWN = "xpath=//p[@class='oxd-userdropdown-name']//parent::span//i";
    public static final String PROFILE_OPTION_BY_TEXT ="xpath=//a[text()='%s']";
    public static final String HEADER_PAGE_BY_TEXT="xpath=//h6[text()='%s']";
    public static final String BUTTON_BY_HEADER_AND_BUTTON_TEXT = "xpath=//h6[contains(., '%s')]//parent::div//button[contains(., '%s')]";
    public static final String LOADING_SPINNER = "xpath=//div[@class='oxd-loading-spinner']";
    public static final String DROPDOWN_ARROW_BUTTON_BY_TEXT = "xpath=//label[text()='%s']//parent::div//following-sibling::div//i";
    public static final String DROPDOWN_LIST_BOX = "xpath=//div[@role='listbox']";
    public static final String DROPDOWN_VALUE_BY_TEXT = "xpath=//div[@role='listbox']//span[text()='%s']";
    public static final String RADIO_BUTTON_BY_TEXT ="xpath=//label[contains(., '%s')]//span";
    public static final String CHECKED_RADIO_BUTTON_BY_TEXT ="xpath=//label[contains(., '%s')]//input";
    public static final String UPLOAD_FILE = "xpath=//input[@type='file']";
    public static final String COMMENT_TEXTAREA = "xpath=//label[text()='Comment']//parent::div//following-sibling::div//textarea";
    public static final String UPLOADED_FILE_DESCRIPTION_BY_FIELD_AND_TEXT ="xpath=//div[text()='%s']//ancestor::div[@class='oxd-table-header']//following-sibling::div[@class='oxd-table-body']//div[contains(text(),'%s')]";
    public static final String UPLOADED_FILE_COMMENT_TEXT = "xpath=//div[text()='Description']//ancestor::div[@class='oxd-table-header']//following-sibling::div[@class='oxd-table-body']//div[contains(., '%s')]";
    public static final String EDIT_ATTACHMENT_BY_FILENAME_AND_BUTTONCLASS ="xpath=//div[text()='%s']//ancestor::div[@class='oxd-table-body']//button/i[contains(@class, '%s')]";
    public static final String CHECKBOX_ATTACHMENT_BY_FILENAME = "xpath=//div[text()='%s']//parent::div//preceding-sibling::div//input[@type='checkbox']";
    public static final String NUMBER_OF_UPLOADED_ATTACHMENT ="xpath=//span[contains(.,'Records Found')]";
    public static final String DELETE_SELECTED_BUTTON ="xpath=//button[contains(.,'Delete Selected')]";
    public static final String CONFIRM_DELETE_POPUP ="xpath=//div[@role='document']";
    public static final String BUTTON_IN_CONFIRM_DELETE_POPUP_BY_TEXT = "xpath=//button[contains(.,'%s')]";
}
