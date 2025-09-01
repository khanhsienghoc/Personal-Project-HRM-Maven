package ultilities;

import com.github.javafaker.Faker;
import commons.GlobalConstants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataUltilities {
    private Faker faker;
    private String date;
    private List<String> nationalityList;

    public static DataUltilities getData(){
        return new DataUltilities();
    }
    public DataUltilities(){
        faker = new Faker();
        loadNationalities();
    }
    public String getFirstName(){
        return faker.name().firstName();
    }
    public String getLastName(){
        return faker.name().lastName();
    }
    public String getMiddleName(){
        return faker.name().firstName();
    }
    public String getUsername (){
        return faker.name().username();
    }
    public String getValidPassword() {
        return faker.internet().password(20, 25, true, true, true);
    }
    public String getInvalidPassword () {
        return faker.internet().password(1, 2);
    }
    public String getInvalidUsername(){
        return faker.name().username();
    }
    public String getOtherID(){
        return faker.bothify("????####");
    }
    public String getDate(){
        Date date = faker.date().future(10, java.util.concurrent.TimeUnit.DAYS);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return String.format("%04d-%02d-%02d", year, day, month);
    }
    public String getMaritalStatus(){
        return faker.options().option("Single", "Married", "Other");
    }
    public String getGender(){
        return faker.options().option("Male", "Female");
    }
    public String getBloodType(){
        return faker.options().option("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
    }
    public String getComment(){
        String comment = faker.lorem().sentence();
        return comment;
    }
    public String getCurrentDate(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
        String formattedDate = currentDate.format(formatter);
        return date = String.valueOf(formattedDate);
    }
    private void loadNationalities() {
        try {
            nationalityList = Files.readAllLines(Paths.get(GlobalConstants.TEST_DATA + "Nationalities.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            nationalityList = Arrays.asList("Afghan");
        }
    }
    public String getNationality() {
        return faker.options().option(nationalityList.toArray(new String[0]));
    }
    public String getEmployeeID (){
        int ID = faker.number().numberBetween(10000, 99999);
        return String.valueOf(ID);
    }

}
