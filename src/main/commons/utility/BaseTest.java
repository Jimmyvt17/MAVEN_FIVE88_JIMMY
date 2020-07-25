package commons.utility;

import commons.CommonsTest;
import commons.Constants;
import org.testng.annotations.Test;

public abstract class BaseTest extends CommonsTest {

    String error, err;
    String prefix = "Failed test case: \n" + getClass().getName() + "\n";;
    protected abstract void Run();

    @Test
    public void play() {
        try {
            Run();
        } catch (Throwable e) {
            if (!e.toString().contains("\n")) {
                error = e.toString();
            } else {
                error = e.toString().substring(0, e.toString().indexOf("\n"));
            }
            if (!error.contains("StaleElementReferenceException")) {
                if (!error.contains("AssertionError")) {
                    if (!error.contains("ElementNotInteractableException")) {
                        err = prefix + error + "\n==============================================\n";
                    } else {
                        err = prefix + Constants.elementIsOverlaying + "\n==============================================\n";
                    }
                } else {
                    err = prefix + Constants.loadingTimeTooLong + "\n==============================================\n";
                }
            } else {
                err = prefix + Constants.elementIsRemoved + "\n==============================================\n";
            }
            sendBot(err);
            throw e;
        }

    }

}
