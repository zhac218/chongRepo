package com.pingidentity.pages;

import com.pingidentity.pages.AdminTask.MilkAdminTaskPage;
import org.openqa.selenium.support.FindBy;

import com.pingidentity.config.ConfigProperties;
import com.pingidentity.wait.DynamicWait;

import org.openqa.selenium.WebElement;

public class MilkLoginPage extends AbstractBasePage {
	
	private static final String usernameLocator="username";
	private static final String passwordLocator="password";
	private static final String loginLocator="login-button";
	
	@FindBy(id=usernameLocator)
	private WebElement txtBoxUsername;
	
	@FindBy(id=passwordLocator)
	private WebElement txtBoxPassword;
	
	@FindBy(id=loginLocator)
	private WebElement btnLogin;
	
	public void enterUsername(String txtUsername) {
		txtBoxUsername.sendKeys(txtUsername);
	}
	
	public void enterPassword(String txtPassword) {
		txtBoxPassword.sendKeys(txtPassword);
	}
	
	public void enterUserCredentials() {
		String username = ConfigProperties.getInstance().getUsername();
		String password = ConfigProperties.getInstance().getPassword();
		
		getDynamicWait().presenceOfElementLocatedByID(usernameLocator);
		getDynamicWait().waitForElementToBeClickable(txtBoxUsername);	
		
		getDynamicWait().presenceOfElementLocatedByID(passwordLocator);
		getDynamicWait().waitForElementToBeClickable(txtBoxPassword);
		
		txtBoxUsername.sendKeys(username);
		txtBoxPassword.sendKeys(password);
	}
	
	public MilkAdminTaskPage goToTaskPage() {
		
		getDynamicWait().presenceOfElementLocatedByID(loginLocator);
		getDynamicWait().waitForElementToBeClickable(btnLogin);
		
		btnLogin.submit();
		return new MilkAdminTaskPage();
	}

	@Override
	protected void load() {
		
	}
	
	@Override
	protected void isLoaded() {
		DynamicWait.getInstance(getDriver()).waitUntilPageWithTitleLoads(getDriver().getTitle());
		
//		getDynamicWait().isElementPresent(By.id(usernameLocator), TIMEOUT);
//		getDynamicWait().isElementIsVisible(By.id(usernameLocator), TIMEOUT);
//		getDynamicWait().isElementIsClickable(By.id(usernameLocator), TIMEOUT);
//		
//		getDynamicWait().isElementPresent(By.id(passwordLocator), TIMEOUT);
//		getDynamicWait().isElementIsVisible(By.id(passwordLocator), TIMEOUT);
//		getDynamicWait().isElementIsClickable(By.id(passwordLocator), TIMEOUT);
//		
//		getDynamicWait().isElementPresent(By.id(loginLocator), TIMEOUT);
//		getDynamicWait().isElementIsVisible(By.id(loginLocator), TIMEOUT);
//		getDynamicWait().isElementIsClickable(By.id(loginLocator), TIMEOUT);
		
	}
	
	
}