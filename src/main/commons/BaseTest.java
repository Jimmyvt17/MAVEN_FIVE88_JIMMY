package commons;

import org.testng.annotations.Test;
import java.lang.reflect.Method;

public abstract class BaseTest extends CommonsTest {

    String error, err;
    String prefix = "Failed test case: \n" + getClass().getName() + "\n";;
    protected abstract void Run(Method method);

    @Test
    public void play(Method method) {
        try {
            Run(method);
        } catch (Throwable e) {
            if (e.toString().indexOf("\n")==-1) {
                error = e.toString();
            } else {
                error = e.toString().substring(0, e.toString().indexOf("\n"));
            }
            if (!error.contains("StaleElementReferenceException")) {
                if (!error.contains("AssertionError")) {
                    if (!error.contains("ElementNotInteractableException")) {
                        err = prefix + error + "\n==================================================\n";
                    } else {
                        err = prefix + Constants.elementIsOverlaying + "\n==================================================\n";
                    }
                } else {
                    err = prefix + Constants.loadingTimeTooLong + "\n==================================================\n";
                }
            } else {
                err = prefix + Constants.elementIsRemoved + "\n==================================================\n";
            }
            if (getClass().getName().contains("TSport")) {
                sendBotReplyToUser(err, Constants.BASTIAN_MESSAGE);
            } else {
                sendBot(err);
            }
            throw e;
        }

    }

}
