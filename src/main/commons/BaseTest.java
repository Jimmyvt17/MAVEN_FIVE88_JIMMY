package commons;

import org.testng.annotations.Test;

import java.lang.reflect.Method;

public abstract class BaseTest extends CommonsTest {

    String error, err;
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
            err = "Failed test case: \n" + getClass().getName() + "\n" + error + "\n==================================================\n";
            sendBot(err);
            throw e;
        }

    }

}
