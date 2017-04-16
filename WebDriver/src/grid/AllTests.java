package grid;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import com.googlecode.junittoolbox.ParallelSuite;

@RunWith(ParallelSuite.class)
@SuiteClasses({
	ChromeRemoteGridLoginLogout.class,
	FirefoxRemoteGridLoginLogout.class,
})
public class AllTests {}
