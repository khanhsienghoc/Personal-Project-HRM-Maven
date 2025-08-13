package pageObject;

import commons.BasePage;
import dataObject.PersonalDetailsData;
import interfaces.MyInfoPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class PersonalDetailsPageObject extends BasePage {
    private final WebDriver driver;

    public PersonalDetailsPageObject(WebDriver driver){
        this.driver = driver;
    }
    /**
     * Get the selected value from the Nationality dropdown by visible text.
     *
     * @param text The visible text representing the nationality option.
     * @return The chosen nationality value displayed in the dropdown.
     */
    @Step("Get the chosen value from Nationality dropdown by text '{0}'")
    public String getChosenValueFromNationalityDropdownByText(String text){
        waitElementVisible(driver, MyInfoPageUI.NATIONALITY_CHOSEN_VALUE, text);
        return getElementText(driver, MyInfoPageUI.NATIONALITY_CHOSEN_VALUE, text);
    }
    public void fillPersonalDetailsForm(PersonalDetailsData data) {
        log.info("Filling personal details form with new data");
        log.info("Input First Name with value {}", data.getFirstName());
        inputToTextBoxByName(driver, "firstName", data.getFirstName());
        inputToTextBoxByName(driver, "middleName", data.getMiddleName());
        inputToTextBoxByName(driver, "lastName", data.getLastName());
        inputToTextBoxByText(driver, "Other Id", data.getOtherID());
        inputToTextBoxByText(driver, "License Expiry Date", data.getLicenseExpiryDate());
        selectValueInDropdownByText(driver, "Nationality", data.getNationality());
        selectValueInDropdownByText(driver, "Marital Status", data.getMaritalStatus());
        clickToRadioButtonByText(driver, data.getGender());
    }
}
