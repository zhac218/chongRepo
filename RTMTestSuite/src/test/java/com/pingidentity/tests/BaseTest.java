package com.pingidentity.tests;

import org.openqa.selenium.WebDriver;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;

import com.pingidentity.AbstractBase;
import com.pingidentity.config.ConfigProperties;
import com.pingidentity.config.SeleniumDriver;

import com.pingidentity.pages.MilkHomePage;
import com.pingidentity.pages.MilkLoginPage;

public abstract class BaseTest extends AbstractBase {

	@Before
    public void executeBeforeClass() {
        System.out.println("@Before: executed Before Class");
        SeleniumDriver.getInstance().startDriver(ConfigProperties.getInstance().getBrowser());
		getDriver().get(ConfigProperties.getInstance().getBaseUrl());
    }
	
	@After
	public void executeAfterClass() {
		System.out.println("@After: executed After Class");
		SeleniumDriver.getInstance().cleanSeleniumSession();
		getDriver().quit();
	}	
}