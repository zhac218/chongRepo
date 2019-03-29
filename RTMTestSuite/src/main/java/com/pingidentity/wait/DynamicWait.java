package com.pingidentity.wait;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriverException;

import com.pingidentity.wait.customwait.CustomConditions;

@Slf4j
public class DynamicWait {
	private WebDriver driver;
	private static final int GLOABL_WAIT_TIME = 20;
	
	private DynamicWait(WebDriver webDriver) {
		this.driver = webDriver;
	}
	
	public static DynamicWait getInstance(WebDriver driver) {
		return new DynamicWait(driver);
	}
	
	public Boolean isAlertPresent(int timeout) {
			Boolean foundAlert = false;
			WebDriverWait webDriverWait = new WebDriverWait(driver,timeout);
			
			try {
				ExpectedCondition<Alert> condition = ExpectedConditions.alertIsPresent();
				webDriverWait.until(condition);
				foundAlert = true;
			} catch (TimeoutException eTO) {
				foundAlert = false;
			}
			return foundAlert;
	}	
	
	public void alertDialogCheck() {
		if (isAlertPresent(1)) {
			Alert alert = this.driver.switchTo().alert();
			alert.accept();
			log.info("Alert Present and Accept");
		} else {
			log.info("No Alert");
		}
		
	}
	
	public Boolean waitUntilPageWithTitleLoads(String title) {
	    ExpectedCondition<Boolean> condition = ExpectedConditions.titleContains(title);
	    return waitInternalForBoolean(condition);
    }

	
	public DynamicWait isElementPresent(By by, int timeout) {
		
		//(WebDriverWait) 
		
        try {
        	new WebDriverWait(driver, timeout).ignoring(NoSuchElementException.class)
            .ignoring(StaleElementReferenceException.class).until(ExpectedConditions.presenceOfElementLocated(by));
            return this;
        } catch (WebDriverException e) {
            throw new Error("Element is not present");
        }
		
	}
	
	public DynamicWait isElementIsClickable(By by, int timeout) {
		
		//.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
		
        try {
            new WebDriverWait(driver, timeout).ignoring(NoSuchElementException.class)
            .ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(by));
            return this;
        } catch (WebDriverException e) {
            throw new Error("Element is not clickable");
        }
    }

    public DynamicWait isElementIsVisible(By by, int timeout) {
    	
    	//.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
    	
        try {
            new WebDriverWait(driver, timeout).ignoring(NoSuchElementException.class)
            .ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfElementLocated(by));
            return this;
        } catch (WebDriverException e) {
            throw new Error("Element is not visible");
        }
    }
	
    public List<WebElement> waitUntilNumberOfElementsByXPath(String xPath, int totalNumber) {
    	ExpectedCondition<java.util.List<WebElement>>condition = ExpectedConditions.numberOfElementsToBe(By.xpath(xPath), totalNumber); 
    	
    	 WebDriverWait webDriverWait = (WebDriverWait) new WebDriverWait(driver, GLOABL_WAIT_TIME);
		    webDriverWait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
		    return webDriverWait.until(condition);
    }
    
    
    
    public List<WebElement> waitUntilNumberOfElementsByCSS(String cssSelector, int totalNumber) {
    	ExpectedCondition<java.util.List<WebElement>>condition = ExpectedConditions.numberOfElementsToBe(By.cssSelector(cssSelector), totalNumber); 
    	
    	 WebDriverWait webDriverWait = (WebDriverWait) new WebDriverWait(driver, GLOABL_WAIT_TIME);
		    webDriverWait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
		    return webDriverWait.until(condition);
    }
    
    
	public Boolean waitUntilPageLoaded() {
		ExpectedCondition<Boolean> condition = CustomConditions.waitForPageLoad();
		return waitInternalForBoolean(condition);
	}
	
	public Boolean waitUntilElementWasClicked(WebElement element) {
		  ExpectedCondition<Boolean> condition = CustomConditions.elementWasClicked(element);
		  return waitInternalForBoolean(condition);
	}
	
	public void waitUntilTextToBePresentInElementByXPath(String xpath, String text, int timeout) {
		ExpectedCondition<Boolean> condition = ExpectedConditions.textToBePresentInElementLocated(By.xpath(xpath), text);
		waitInternalForBoolean(condition, timeout);
	}
	
	public void waitUntilTextDisappearByXPath(String xpath, String value, int timeout) {
		ExpectedCondition<Boolean> condition = ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(By.xpath(xpath), value));
		waitInternalForBoolean(condition, timeout);
	}
	
	public WebElement waitVisibilityOf(WebElement webElement) {
	    ExpectedCondition<WebElement> condition = ExpectedConditions.visibilityOf(webElement);
	    return waitInternal(condition);
	  }
	
	public void waitInvisibilityByXPath(String xpath, int timeout) {
		ExpectedCondition<Boolean> condition = ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath));
		waitInternalForBoolean(condition, timeout);
	}
	
	  public WebElement presenceOfElementLocatedByXPath(String xpath) {
		    ExpectedCondition<WebElement> presenceOfElementLocated = ExpectedConditions.presenceOfElementLocated(By.xpath(xpath));
		    return waitInternal(presenceOfElementLocated);
	   }	
	
	  public WebElement presenceOfElementLocatedByCSS (String css) {
		    ExpectedCondition<WebElement> presenceOfElementLocated = ExpectedConditions.presenceOfElementLocated(By.cssSelector(css));
		    return waitInternal(presenceOfElementLocated);
	  }
	
	  public WebElement presenceOfElementLocatedByID (String id) {
		    ExpectedCondition<WebElement> presenceOfElementLocated = ExpectedConditions.presenceOfElementLocated(By.id(id));
		    return waitInternal(presenceOfElementLocated);
	  }
	  
	  public void waitForElementToBeClickable(WebElement element) {
		    WebDriverWait webDriverWait = new WebDriverWait(this.driver, GLOABL_WAIT_TIME);
		    ExpectedCondition<WebElement> condition = ExpectedConditions.elementToBeClickable(element);
		    webDriverWait.until(condition);
	  }
	  
	  public void waitForElementToBeClickableByXpath(String theXpath) {
		    WebDriverWait webDriverWait = new WebDriverWait(driver, GLOABL_WAIT_TIME);
		    ExpectedCondition<WebElement> condition = ExpectedConditions.elementToBeClickable(By.xpath(theXpath));
		    webDriverWait.until(condition);
	  }
	  
	  public void waitForElementToBeClickableByCSS(String cssSelector) {
		    WebDriverWait webDriverWait = new WebDriverWait(driver, GLOABL_WAIT_TIME);
		    ExpectedCondition<WebElement> condition = ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector));
		    webDriverWait.until(condition);
	  }
	  
	  public WebElement getElementByXPath(String xpath) {
		    WebDriverWait webDriverWait = new WebDriverWait(driver, GLOABL_WAIT_TIME);
		    ExpectedCondition<WebElement> condition = ExpectedConditions.presenceOfElementLocated(By.xpath(xpath));
		    return webDriverWait.until(condition);
	  }
	  
	  public WebElement getElementByCSS(String cssSelector) {
		    WebDriverWait webDriverWait = new WebDriverWait(driver, GLOABL_WAIT_TIME);
		    ExpectedCondition<WebElement> condition = ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector));
		    return webDriverWait.until(condition);
	  }
	
	  private WebElement waitInternal(ExpectedCondition<WebElement> condition) {
		    return waitInternal(condition, GLOABL_WAIT_TIME);
	  }
	  
	  private WebElement waitInternal(ExpectedCondition<WebElement> condition, int timeout) {
		    WebDriverWait webDriverWait = (WebDriverWait) new WebDriverWait(driver, timeout);
		    webDriverWait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
		    .ignoring(TimeoutException.class);
		    return webDriverWait.until(condition);
	  }
	  
	  private Boolean waitInternalForBoolean(ExpectedCondition<Boolean> condition) {
		    return waitInternalForBoolean(condition, GLOABL_WAIT_TIME);    
	  }
	  
	  private Boolean waitInternalForBoolean(ExpectedCondition<Boolean> condition, int timeout) {
		    WebDriverWait webDriverWait = (WebDriverWait) new WebDriverWait(driver, timeout);
		    webDriverWait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
		    return webDriverWait.until(condition);
	  }
}
