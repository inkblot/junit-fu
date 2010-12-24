package org.movealong.junitfu.framework;

import com.google.inject.AbstractModule;

/**
* Created by IntelliJ IDEA.
* User: inkblot
* Date: Dec 24, 2010
* Time: 8:59:15 AM
*/
public class TestModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(SomeService.class).to(SomeServiceAlternateImpl.class);
    }
}
