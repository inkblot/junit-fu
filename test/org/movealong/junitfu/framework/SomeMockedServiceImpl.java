package org.movealong.junitfu.framework;

import static org.junit.Assert.fail;

/**
* Created by IntelliJ IDEA.
* User: inkblot
* Date: Dec 24, 2010
* Time: 8:57:18 AM
*/
public class SomeMockedServiceImpl implements SomeMockedService {
    @Override
    public void testFired() {
        fail("The test case should have had a mock injected");
    }
}
