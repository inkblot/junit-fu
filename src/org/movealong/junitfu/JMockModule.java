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
package org.movealong.junitfu;

import com.google.inject.AbstractModule;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;

/**
* Created by IntelliJ IDEA.
* User: inkblot
* Date: Dec 23, 2010
* Time: 11:05:10 PM
*/
class JMockModule extends AbstractModule {
    private final MockBinder classMockBinder;

    public JMockModule(Class<?> testClass) {
        classMockBinder = new ClassMockBinder(testClass);
    }

    @Override
    protected void configure() {
        final JUnit4Mockery mock = new JUnit4Mockery();
        bind(Mockery.class).toInstance(mock);
        classMockBinder.bindMocks(mock, binder());
    }
}
