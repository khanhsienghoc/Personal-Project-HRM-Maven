package hooks;

import commons.BaseTest;
import commons.DriverManager;
import commons.EnvironmentConfigManager;
import io.cucumber.java.*;

import org.openqa.selenium.WebDriver;

public class Hooks extends BaseTest {

    public static EnvironmentConfigManager config;
    public static WebDriver driver;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        String env = System.getProperty("environment", "test");

        WebDriver driver = getBrowserDriver(browser, env);
        DriverManager.setDriver(driver);
    }

    @After
    public void tearDown() {
     DriverManager.quitDriver();
    }
}