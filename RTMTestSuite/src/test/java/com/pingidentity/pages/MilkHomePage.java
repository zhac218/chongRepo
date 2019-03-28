package com.pingidentity.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.pingidentity.wait.DynamicWait;

public class MilkHomePage extends AbstractBasePage{
	
	private static final String linkLocator=".container>ul.hidden-xs>li:nth-child(5)>a ";
	
	@FindBy(css=linkLocator)
	private WebElement linkLogin;
	
	public MilkLoginPage goToLoginPage() {
		getDynamicWait().waitUntilElementWasClicked(linkLogin);
		
		return new MilkLoginPage();
	}
	
	@Override
	protected void load() {
		
	}
	
	@Override
	protected void isLoaded() {
		DynamicWait.getInstance(getDriver()).waitUntilPageWithTitleLoads(getDriver().getTitle());
		
		getDynamicWait().isElementPresent(By.cssSelector(linkLocator), TIMEOUT);
		getDynamicWait().isElementIsVisible(By.cssSelector(linkLocator), TIMEOUT);
		getDynamicWait().isElementIsClickable(By.cssSelector(linkLocator), TIMEOUT);
	}
		
}
