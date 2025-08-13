package reportConfigs;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;

public class SkipDBInterceptor implements IMethodInterceptor {
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        String envFromXML = context.getCurrentXmlTest().getParameter("environment");

        boolean isTestEnv = "src/test".equalsIgnoreCase(envFromXML);

        List<IMethodInstance> result = new ArrayList<>();

        for (IMethodInstance method : methods) {
            boolean isDBTest = method.getMethod().getGroups() != null &&
                    List.of(method.getMethod().getGroups()).contains("databaseTesting");

            if (isDBTest && isTestEnv) {
                System.out.println("⏭ Skipping DB test in TEST environment: " + method.getMethod().getMethodName());
                continue;
            }

            result.add(method);
        }

        return result;
    }
}
