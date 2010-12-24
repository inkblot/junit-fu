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
 * Date: Dec 23, 2010
 * Time: 7:48:06 PM
 */
@RunWith(JUnitFu.class)
@Modules({TestModule.class})
public class ConstructorInjectionTest {

    private Mockery mock;
    private SomeService someService;
    private SomeMockedService someMockedService;

    @Inject
    public ConstructorInjectionTest(Mockery mock, @Foil SomeService someService, @Mock final SomeMockedService someMockedService) {
        this.mock = mock;
        this.someService = someService;
        this.someMockedService = someMockedService;

        mock.checking(new Expectations() {{
            one(someMockedService).testFired();
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
        someMockedService.testFired();
        assertEquals("Get me into the Pillows", someService.getShibboleth());
    }

}
