package com.pingidentity.task.pages.DetailTask;
import com.pingidentity.pages.AbstractBasePage;
import com.pingidentity.wait.DynamicWait;

public class MilkDetailTaskPage extends AbstractBasePage{

	@Override
	protected void load() {
		
	}
	
	// *** FIX ***
	@Override
	protected void isLoaded() {
		DynamicWait.getInstance(getDriver()).waitUntilPageWithTitleLoads(getDriver().getTitle());
	}
	
}
