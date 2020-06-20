package commons;

import org.testng.annotations.Test;
import java.lang.reflect.Method;

public abstract class BaseTest extends CommonsTest {

    protected abstract void Run(Method method);

    @Test
    public void play(Method method) {
        try {
            Run(method);
        } catch (Throwable e) {
            String error = "Failed test case: \n" + getClass().getName() + "\n" + e.toString() + "\n==================================================\n";
            sendBot(error);
            throw e;
        }

    }

}
