package commons;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import reportConfigs.VerificationFailures;
import retryConfigs.RetryListener;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

@Listeners({reportConfigs.AllureTestListener.class,RetryListener.class})
public class BaseTest {
    private WebDriver driver;
    protected final Logger log = LoggerFactory.getLogger(getClass());
    @BeforeSuite
    public void initBeforeSuite(){
        System.setProperty("allure.results.directory", GlobalConstants.PROJECT_PATH + "/allure-results");
        deleteAllureReport();
    }
    protected WebDriver getBrowserDriver(String browserName, String envName){
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        if(browserList == BrowserList.CHROME){
            driver = new ChromeDriver();
        } else if(browserList == BrowserList.HEADLESS_CHROME){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("window-size=1920x1080");
            driver = new ChromeDriver(options);
        } else if (browserList == BrowserList.FIREFOX) {
            driver =new FirefoxDriver();
        } else if (browserList == BrowserList.HEADLESS_FIREFOX) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            options.addArguments("window-size=1920x1080");
            driver = new FirefoxDriver(options);
        } else if(browserList == BrowserList.EDGE) {
            driver = new EdgeDriver();
        } else if(browserList == BrowserList.HEADLESS_EDGE){
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--headless");
            options.addArguments("window-size=1920x1080");
            driver = new EdgeDriver(options);
        }else {
            throw new exception.BrowserNotSupport(browserName);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.manage().window().maximize();
        GlobalConstants.ENV = EnvironmentList.valueOf(envName.toUpperCase());
        String baseUrl = EnvironmentConfigManager.getInstance().getBaseUrl();
        driver.get(baseUrl);
        return driver;
    }
    public WebDriver getDriverInstance() {
        return this.driver;
    }
    protected int randomNum(){
        Random rand = new Random();
        return rand.nextInt(9999999);
    }
    protected boolean checkTrue(boolean condition) {
        boolean pass = true;
        try {
            Assertions.assertTrue(condition);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            pass = false;
            log.info(" -------------------------- FAILED -------------------------- ");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }
    protected boolean checkFalse(boolean condition) {
        boolean pass = true;
        try {
            Assertions.assertFalse(condition);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            pass = false;
            log.info(" -------------------------- FAILED -------------------------- ");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }
    protected boolean checkEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assertions.assertEquals(actual, expected);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            pass = false;
            log.info(" -------------------------- FAILED -------------------------- ");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }
    public void deleteAllureReport() {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + "/allure-results";
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles.length != 0) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    protected void closeBrowserAndDriver() {
        String cmd = "";
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            log.info("OS name = " + osName);

            String driverInstanceName = driver.toString().toLowerCase();
            log.info("Driver instance name = " + driverInstanceName);

            if (driverInstanceName.contains("chrome")) {
                if (osName.contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
                } else {
                    cmd = "pkill chromedriver";
                }
            } else if (driverInstanceName.contains("internetexplorer")) {
                if (osName.contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
                }
            } else if (driverInstanceName.contains("firefox")) {
                if (osName.contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
                } else {
                    cmd = "pkill geckodriver";
                }
            } else if (driverInstanceName.contains("safari")) {
                if (osName.contains("mac")) {
                    cmd = "pkill safaridriver";
                }
            }else if (driverInstanceName.contains("edge")) {
                if (osName.contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
                } else {
                    cmd = "pkill msedgedriver";
                }
            }
            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
                driver = null;
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {
                if (!cmd.isEmpty()) {
                    Process process = Runtime.getRuntime().exec(cmd);
                    process.waitFor();
                } else {
                    log.info("No valid kill command found to execute.");
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
