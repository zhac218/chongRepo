package com.pingidentity.wait.customwait;

import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomConditions {

	 public static ExpectedCondition<Boolean> elementWasClicked(final WebElement element) {
	        return new ExpectedCondition<Boolean>() {
	            @Override
	            public Boolean apply(WebDriver driver) {
	                try {
	                    element.click();
	                    return true;
	                } catch (Exception ex) {
	                    return false;
	                }
	            }

	            @Override
	            public String toString() {
	                return "element to be clicked: " + element.toString();
	            }
	        };
	    }
	 
	 public static ExpectedCondition<Boolean> waitForPageLoad() {
		 ExpectedCondition<Boolean> pageLoadCondition = new
	                ExpectedCondition<Boolean>() {
	                    public Boolean apply(WebDriver driver) {
	                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	                    }
	                };
	     return pageLoadCondition;           
	 }
}