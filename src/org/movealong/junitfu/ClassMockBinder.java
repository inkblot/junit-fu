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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: inkblot
 * Date: Dec 23, 2010
 * Time: 11:29:48 PM
 */
class ClassMockBinder extends CompositeMockBinder {
    public ClassMockBinder(Class<?> testClass) {
        super(createMockBinders(testClass));
    }

    private static Collection<? extends MockBinder> createMockBinders(Class<?> testClass) {
        ArrayList<MockBinder> binders = new ArrayList<MockBinder>();
        for (Field field : testClass.getDeclaredFields()) {
            binders.add(new FieldMockBinder(field));
        }
        for (Method method : testClass.getDeclaredMethods()) {
            binders.add(new MethodMockBinder(method));
        }
        for (Constructor<?> constructor : testClass.getDeclaredConstructors()) {
            binders.add(new ConstructorMockBinder(constructor));
        }
        return binders;
    }
}
