package com.brahalla.cerberus.integration.suite;

import com.brahalla.cerberus.integration.controller.rest.AuthenticationControllerTest;
import com.brahalla.cerberus.integration.controller.rest.ProtectedControllerTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  AuthenticationControllerTest.class,
  ProtectedControllerTest.class
})
public class IntegrationTestSuite {

}
