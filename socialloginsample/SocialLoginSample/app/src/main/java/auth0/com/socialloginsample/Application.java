package auth0.com.socialloginsample;

import java.util.concurrent.Callable;

/**
 * Created by Exxel on 2/1/17.
 */
public class Application {
    private static Application ourInstance = new Application();

    public static Application getInstance() {
        return ourInstance;
    }

    private Application() {
    }

    public void setLogoutCallable(Callable<Void> callable) {
        Callable<Void> mLogoutCallable = callable;
    }
}
