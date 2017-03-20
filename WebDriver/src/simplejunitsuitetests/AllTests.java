package simplejunitsuitetests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({  SuiteSmoteTests.class,
	SuiteAddEditDeleteTests.class})
public class AllTests {

}
