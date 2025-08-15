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

        log.info("Input Middle Name with value {}", data.getMiddleName());
        inputToTextBoxByName(driver, "middleName", data.getMiddleName());

        log.info("Input Last Name with value {}", data.getLastName());
        inputToTextBoxByName(driver, "lastName", data.getLastName());

        log.info("Input Other ID with value {}", data.getOtherID());
        inputToTextBoxByText(driver, "Other Id", data.getOtherID());

        log.info("Input License Expiry Date with value {}", data.getLicenseExpiryDate());
        inputToTextBoxByText(driver, "License Expiry Date", data.getLicenseExpiryDate());

        log.info("Select nationality with value {}", data.getNationality());
        selectValueInDropdownByText(driver, "Nationality", data.getNationality());

        log.info("Select Martial Status with value {}", data.getNationality());
        selectValueInDropdownByText(driver, "Marital Status", data.getMaritalStatus());
        clickToRadioButtonByText(driver, data.getGender());
    }
}
