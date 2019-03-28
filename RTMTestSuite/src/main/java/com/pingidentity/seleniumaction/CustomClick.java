package com.pingidentity.seleniumaction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;

import com.pingidentity.config.ConfigProperties;
import com.pingidentity.config.SeleniumDriver;
import com.pingidentity.wait.DynamicWait;

public class CustomClick {
	
	private static CustomClick instance = new CustomClick();
    
    private CustomClick() {	
    }
	
    public static CustomClick getInstance() {
    	return instance;
    }
	
	public void customizedClickElement(WebDriver driver, WebElement element, String locator) {	
		try {			
			DynamicWait.getInstance(driver).waitForElementToBeClickable(element);
			DynamicWait.getInstance(driver).waitUntilElementWasClicked(element);
         } catch (TimeoutException ex) {
            throw new RuntimeException(String.format(
                    "Failed clicking on element %s",
                    locator), ex);
        }
	}
}
