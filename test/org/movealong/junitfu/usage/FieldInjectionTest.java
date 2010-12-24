/*
 * (c) Copyright 2010 Nate Riffe <inkblot@movealong.org>
 *
 * This file is part of junit-fu.
 *
 * junit-fu is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * junit-fu is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Affero General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with junit-fu.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.movealong.junitfu.usage;

import com.google.inject.Inject;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.movealong.junitfu.JUnitFu;
import org.movealong.junitfu.Mock;
import org.movealong.junitfu.framework.SomeMockedService;
import org.movealong.junitfu.framework.SomeService;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: inkblot
 * Date: Dec 24, 2010
 * Time: 10:26:39 AM
 */
@RunWith(JUnitFu.class)
public class FieldInjectionTest {
    @Inject
    public Mockery mock;
    @Inject
    @Mock
    public SomeMockedService mocked;
    @Inject
    public SomeService someService;

    @Before
    public void setUp() {
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
        assertEquals("This is the Knuckles of Frisco", someService.getShibboleth());
        assertEquals("mocked", mocked.toString());
    }
}
