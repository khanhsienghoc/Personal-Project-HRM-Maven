package commons;

public class EnvironmentConfigManager {
    private final EnvironmentList env;

    public EnvironmentConfigManager(EnvironmentList env) {
        this.env = env;
    }
    public static EnvironmentConfigManager getInstance(){
        return new EnvironmentConfigManager(GlobalConstants.ENV);
    }
    public String getBaseUrl() {
        return env == EnvironmentList.LOCAL ? GlobalConstants.LOCAL_URL : GlobalConstants.PORTAL_TESTING_URL;
    }
    public String getAdminUserName() {
        return env == EnvironmentList.LOCAL ? GlobalConstants.LOCAL_ADMIN_USERNAME : GlobalConstants.TESTING_ADMIN_USERNAME;
    }
    public String getAdminPassword() {
        return env == EnvironmentList.LOCAL ? GlobalConstants.LOCAL_ADMIN_PASSWORD : GlobalConstants.TESTING_ADMIN_PASSWORD;
    }
}