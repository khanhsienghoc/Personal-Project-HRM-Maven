package commons;
import java.io.File;

public class GlobalConstants {
    public static EnvironmentList ENV;
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String UPLOAD_FILE= PROJECT_PATH + File.separator +"src" + File.separator +"main"+ File.separator +"resources" + File.separator +"uploadFiles" +File.separator;
    public static final long SHORT_TIMEOUT = 5;
    public static final long LONG_TIMEOUT = 30;
    public static final String REQUIRED_ERROR_MESSAGE = "Required";
    public static final String INVALID_CREDENTIALS_MESSAGE = "Invalid credentials";
    public static final String TEST_DATA= PROJECT_PATH + File.separator +"src" + File.separator +"main"+File.separator +"resources"+File.separator +"testdata"+ File.separator;
    // --- Database ---
    public static final String DB_HOST = "localhost";
    public static final String DB_PORT = "3306";
    public static final String DB_NAME = "orangehrm_db";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "";
    public static final String DATABASE_TESTING_URL = "jdbc:mysql://" + GlobalConstants.DB_HOST + ":" + GlobalConstants.DB_PORT + "/" + GlobalConstants.DB_NAME;
    // --- Localhost environment ---
    public static final String LOCAL_URL = "http://localhost/orangehrm/web/index.php/auth/login";
    public static final String LOCAL_ADMIN_USERNAME = "Admin";
    public static final String LOCAL_ADMIN_PASSWORD = "Admin123456789@";
    // --- Online testing environment ---
    public static final String PORTAL_TESTING_URL ="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    public static final String TESTING_ADMIN_USERNAME = "Admin";
    public static final String TESTING_ADMIN_PASSWORD ="admin123";
}
