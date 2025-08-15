package dataObject;

public class PersonalDetailsData {
    private String firstName;
    private String middleName;
    private String lastName;
    private String otherID;
    private String licenseExpiryDate;
    private String nationality;
    private String maritalStatus;
    private String gender;
    private String bloodType;

    /**
     * Constructor with all fields
     */
    public PersonalDetailsData(String firstName, String middleName, String lastName,
                               String otherID, String licenseExpiryDate, String nationality,
                               String maritalStatus, String gender, String bloodType) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.otherID = otherID;
        this.licenseExpiryDate = licenseExpiryDate;
        this.nationality = nationality;
        this.maritalStatus = maritalStatus;
        this.gender = gender;
        this.bloodType = bloodType;
    }

    // Getters and Setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOtherID() {
        return otherID;
    }

    public void setOtherID(String otherID) {
        this.otherID = otherID;
    }

    public String getLicenseExpiryDate() {
        return licenseExpiryDate;
    }

    public void setLicenseExpiryDate(String licenseExpiryDate) {
        this.licenseExpiryDate = licenseExpiryDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
}
