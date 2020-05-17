package own.qualifying;

import own.qualifying.client.starterTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class starterSuite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Tests for Starter");
    suite.addTestSuite(starterTest.class);
    return suite;
  }
}
