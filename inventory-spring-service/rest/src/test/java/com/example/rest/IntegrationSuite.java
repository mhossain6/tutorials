package com.example.rest;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({ // @formatter:off
        UsersIntegrationTest.class,
        AdminIntegrationTest.class
})// @formatter:on
public class IntegrationSuite {
    //
}
