package reportConfigs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.Utils;

import java.util.List;

public class MethodListener implements IInvokedMethodListener {
    private static final Logger log = LoggerFactory.getLogger(MethodListener.class);

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult result) {
        log.debug("Before invocation of " + method.getTestMethod().getMethodName());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        log.debug("After invocation of " + method.getTestMethod().getMethodName());
        Reporter.setCurrentTestResult(result);

        if (method.isTestMethod()) {
            VerificationFailures allFailures = VerificationFailures.getFailures();

            if (result.getThrowable() != null) {
                allFailures.addFailureForTest(result, result.getThrowable());
            }

            List<Throwable> failures = allFailures.getFailuresForTest(result);
            int size = failures.size() - 1;

            if (size > 0) {
                result.setStatus(ITestResult.FAILURE);
                if (size == 1) {
                    result.setThrowable(failures.get(0));
                } else {
                    StringBuffer message = new StringBuffer("Multiple failures (")
                            .append(size + 1).append("):\n");

                    for (int i = 0; i < size; i++) {
                        message.append("Failure ").append(i + 1).append(" of ").append(size + 1).append(":\n");
                        message.append(Utils.longStackTrace(failures.get(i), false)).append("\n");
                    }

                    Throwable last = failures.get(size);
                    message.append("Failure ").append(size + 1).append(" of ").append(size + 1).append(":\n");
                    message.append(last.toString());

                    Throwable merged = new Throwable(message.toString());
                    merged.setStackTrace(last.getStackTrace());

                    result.setThrowable(merged);
                }
            }
        }
    }
}
