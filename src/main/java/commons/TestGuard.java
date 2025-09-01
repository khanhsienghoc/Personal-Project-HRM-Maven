package commons;

import io.qameta.allure.Step;
import org.testng.SkipException;

public class TestGuard {

    @Step("⏭ Skipping DB test in ENV = TEST")
    public static void skipIfDBDisabled() {
        if (GlobalConstants.ENV == EnvironmentList.TEST) {
            logSkipReasonToConsole();
            throw new SkipException("❌ Skipping test: DB test is disabled in TEST environment");
        }
    }
    private static void logSkipReasonToConsole() {
        System.out.println("⚠️ [TestGuard] Skipped DB test due to ENV = TEST");
    }
}
