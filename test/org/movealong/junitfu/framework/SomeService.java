package org.movealong.junitfu.framework;

import com.google.inject.ImplementedBy;

/**
* Created by IntelliJ IDEA.
* User: inkblot
* Date: Dec 24, 2010
* Time: 8:58:14 AM
*/
@ImplementedBy(SomeServiceImpl.class)
public interface SomeService {
    String getShibboleth();
}
