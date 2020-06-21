package commons;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public abstract class BaseTest extends CommonsTest {

    String error, err;

    WebDriver driver;

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
                    err = "Failed test case: \n" + getClass().getName() + "\n" + error + "\n==================================================\n";
                } else {
                    err = "Failed test case: \n" + getClass().getName() + "\nLoading time is too long to bet\n==================================================\n";
                }
            } else {
                err = "Failed test case: \n" + getClass().getName() + "\nElement is removed in DOM\n==================================================\n";
            }
            sendBot(err);
            throw e;
        }

    }

}
