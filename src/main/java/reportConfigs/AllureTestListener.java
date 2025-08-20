package reportConfigs;

import commons.BaseTest;
import commons.EnvironmentList;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;
import org.testng.SkipException;


public class AllureTestListener implements ITestListener {
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    @Attachment(value = "Screenshot of {0}", type = "image/png")
    public static byte[] saveScreenshotPNG(String testName, WebDriver driver) {
        return (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
    @Step("⏭ Skipping DB test due to invalid environment: {env}")
    public static void skipIfNotLocal(EnvironmentList env) {
        if (env != EnvironmentList.LOCAL) {
            System.out.println("⚠️ Skipping DB test in ENV: " + env);
            throw new SkipException("❌ Skipping DB test in ENV = " + env);
        }
    }
    private static void logSkipReasonToConsole() {
            System.out.println("⚠️ Test skipped due to either demo environment or databaseTesting=false");
        }
    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriverInstance();
        if (driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            // Add screenshot manually to current test
            Allure.getLifecycle().addAttachment("Screenshot on failure", "image/png", "png", screenshot);
            System.out.println("✅ Screenshot attached via Allure lifecycle API");
        } else {
            System.out.println("🚨 WebDriver is null, skipping screenshot.");
        }
    }
    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("=== TEST SUITE STARTED ===");
    }
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("⚠️ Test Skipped: " + iTestResult.getName());
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }
    @Override
    public void onFinish(ITestContext arg0) {
        System.out.println("=== TEST SUITE FINISHED ===");
    }
    @Override
    public void onTestStart(ITestResult arg0) {
        System.out.println("TEST STARTED: "+ arg0.getName());
    }
    @Override
    public void onTestSuccess(ITestResult arg0) {
        System.out.println("✅ Test Passed: " + arg0.getName());
    }
}