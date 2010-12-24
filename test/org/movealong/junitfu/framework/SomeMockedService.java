package org.movealong.junitfu.framework;

import com.google.inject.ImplementedBy;

/**
* Created by IntelliJ IDEA.
* User: inkblot
* Date: Dec 24, 2010
* Time: 8:58:06 AM
*/
@ImplementedBy(SomeMockedServiceImpl.class)
public interface SomeMockedService {
    void testFired();
}
