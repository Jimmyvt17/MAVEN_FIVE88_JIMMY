package commons.utility;

import commons.CommonsTest;
import org.testng.annotations.Test;

public abstract class BaseTest extends CommonsTest {

    String prefix = "Failed test case: \n" + getClass().getName() + "\n";;
    protected abstract void Run();

    @Test
    public void play() {
        try {
            Run();
        } catch (Throwable e) {
            convertException(e, prefix);
            throw e;
        }

    }

}
