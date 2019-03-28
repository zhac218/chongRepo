package com.pingidentity.pages;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

import com.pingidentity.AbstractBase;
import com.pingidentity.config.CustomLoadableComponent;

public abstract class AbstractBasePage extends CustomLoadableComponent {

	protected int TIMEOUT = 20;
	
	public AbstractBasePage() {
		PageFactory.initElements(getDriver(), this);	
	}
	
	
	
	
}
