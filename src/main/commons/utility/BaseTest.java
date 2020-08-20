package commons.utility;

import commons.CommonsTest;
import commons.Constants;
import org.testng.annotations.Test;

public abstract class BaseTest extends CommonsTest {

    String prefix = Constants.prefix + getClass().getName() + "\n";;
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
