package com.pingidentity.tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.After;

import com.pingidentity.AbstractBase;
import com.pingidentity.config.ConfigProperties;
import com.pingidentity.config.SeleniumDriver;

@Slf4j
public abstract class BaseTest extends AbstractBase {
	@Before
    public void executeBeforeClass() {
		log.info("@Before: executed Before Class");
		boolean maximize = false;
        SeleniumDriver.getInstance().startDriver(ConfigProperties.getInstance().getBrowser(), maximize);
		getDriver().get(ConfigProperties.getInstance().getBaseUrl());
    }
	
	@After
	public void executeAfterClass() {
		log.info("@After: executed After Class");
		SeleniumDriver.getInstance().cleanSeleniumSession();
		getDriver().quit();
	}	
}