package reportConfigs;

import org.testng.ITestResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VerificationFailures extends HashMap<ITestResult, List<Throwable>> {
    private static final long serialVersionUID = 1L;
    private static VerificationFailures failures;

    // Private constructor (Singleton pattern)
    private VerificationFailures() {
        super();
    }

    // Trả về instance duy nhất
    public static VerificationFailures getFailures() {
        if (failures == null) {
            failures = new VerificationFailures();
        }
        return failures;
    }

    // Lấy danh sách lỗi ứng với 1 test case
    public List<Throwable> getFailuresForTest(ITestResult result) {
        List<Throwable> exceptions = get(result);
        return exceptions == null ? new ArrayList<Throwable>() : exceptions;
    }

    // Thêm lỗi vào danh sách cho test case tương ứng
    public void addFailureForTest(ITestResult result, Throwable throwable) {
        List<Throwable> exceptions = getFailuresForTest(result);
        exceptions.add(throwable);
        put(result, exceptions);
    }
}

