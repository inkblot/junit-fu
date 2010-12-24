package org.movealong.junitfu.usage;

import com.google.inject.Inject;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.movealong.junitfu.JUnitFu;
import org.movealong.junitfu.Mock;
import org.movealong.junitfu.Modules;
import org.movealong.junitfu.framework.Foil;
import org.movealong.junitfu.framework.SomeMockedService;
import org.movealong.junitfu.framework.SomeService;
import org.movealong.junitfu.framework.TestModule;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: inkblot
 * Date: Dec 24, 2010
 * Time: 9:01:55 AM
 */
@RunWith(JUnitFu.class)
@Modules({TestModule.class})
public class MethodInjectionTest {
    private Mockery mock;
    private SomeMockedService mocked;
    private SomeService someService;

    @Inject
    public void configure(@Foil Mockery mock, @Mock("mocked") final SomeMockedService mocked, SomeService someService) {
        this.mock = mock;
        this.mocked = mocked;
        this.someService = someService;

        mock.checking(new Expectations() {{
            one(mocked).testFired();
        }});
    }

    @Test
    public void itRanOnce() {
        doStuff();
    }

    @Test
    public void itRanAgain() {
        doStuff();
    }

    private void doStuff() {
        mocked.testFired();
        assertEquals("Get me into the Pillows", someService.getShibboleth());
        assertEquals("mocked", mocked.toString());
    }
}
