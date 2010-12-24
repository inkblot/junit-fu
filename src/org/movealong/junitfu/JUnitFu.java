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

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.jmock.integration.junit4.JMock;
import org.junit.internal.runners.InitializationError;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

/**
 * Created by IntelliJ IDEA.
 * User: inkblot
 * Date: Dec 23, 2010
 * Time: 7:50:28 AM
 */
public class JUnitFu extends JMock {
    private final Class<?> testClass;
    private final Module jMockModule;

    public JUnitFu(Class<?> testClass) throws org.junit.internal.runners.InitializationError {
        super(testClass);
        this.testClass = testClass;
        this.jMockModule = new JMockModule(testClass);
    }

    @Override
    protected void validate() throws InitializationError {
        // probably ought to do some kind of validation, at least
    }

    @Override
    protected Object createTest() throws Exception {
        return createInjector().getInstance(testClass);
    }

    private Injector createInjector() throws InstantiationException, IllegalAccessException {
        return Guice.createInjector(jMockModule).createChildInjector(getModules(testClass));
    }

    private List<? extends Module> getModules(Class<?> testClass) throws IllegalAccessException, InstantiationException {
        if (testClass.getAnnotation(Modules.class) == null) return emptyList();
        List<Class<? extends Module>> moduleClasses = asList(testClass.getAnnotation(Modules.class).value());
        List<Module> modules = new ArrayList<Module>(moduleClasses.size());
        for (Class<? extends Module> moduleClass : moduleClasses) {
            modules.add(moduleClass.newInstance());
        }
        return modules;
    }

}