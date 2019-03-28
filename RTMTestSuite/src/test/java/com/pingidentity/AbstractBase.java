package com.pingidentity;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import com.pingidentity.config.ConfigProperties;
import com.pingidentity.config.SeleniumDriver;
import com.pingidentity.wait.DynamicWait;
import com.pingidentity.wait.StaticWait;
import com.pingidentity.seleniumaction.CustomClick;

public abstract class AbstractBase {

	protected ConfigProperties getConfigProperties() {
		return ConfigProperties.getInstance();
	}

	protected WebDriver getDriver() {
		return SeleniumDriver.getInstance().getDriver();
	}
	
	protected SeleniumDriver getSeleuniumDriver() {
		return SeleniumDriver.getInstance();
	}
	
	protected StaticWait getStaticWait() {
		return StaticWait.getInstance();
	}
	
	protected DynamicWait getDynamicWait() {
		return DynamicWait.getInstance(getDriver());			
	}

	protected void checkForAlert() {
		getDynamicWait().alertDialogCheck();
	}
	
	protected CustomClick getCustomClick() {
		return CustomClick.getInstance();
	}
}