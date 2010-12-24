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

import com.google.inject.Inject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static org.apache.commons.lang.StringUtils.defaultIfEmpty;
import static org.apache.commons.lang.StringUtils.uncapitalize;

/**
 * Created by IntelliJ IDEA.
 * User: inkblot
 * Date: Dec 23, 2010
 * Time: 11:46:00 PM
 */
class MethodMockBinder extends CompositeMockBinder {
    public MethodMockBinder(Method method) {
        super(createMockBinders(method));
    }

    private static Collection<? extends MockBinder> createMockBinders(Method method) {
        if (method.getAnnotation(Inject.class) != null) {
            HashSet<MockBinder> mockBinders = new HashSet<MockBinder>();
            for (int parameterIndex = 0 ; parameterIndex < method.getParameterTypes().length ; parameterIndex++) {
                for (Annotation annotation : method.getParameterAnnotations()[parameterIndex]) {
                    if (annotation.annotationType() == Mock.class) {
                        Class<?> mockClass = method.getParameterTypes()[parameterIndex];
                        String mockName = defaultIfEmpty(((Mock) annotation).value(), uncapitalize(mockClass.getSimpleName()));
                        mockBinders.add(new SimpleMockBinder(mockClass, mockName));
                    }
                }
            }
            return mockBinders;
        } else {
            return Collections.emptyList();
        }
    }
}