package com.pingidentity.pages.ScheduleTask;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.pingidentity.pages.AbstractBasePage;
import com.pingidentity.wait.DynamicWait;

public class MilkScheduleTaskPage extends AbstractBasePage {

    	private static final String inboxLocator="//div[@class='Fj-an-Oj' and @title='Inbox']";
    	private static final String todayLocator="//div[@class='Um-Pn-mq-Kn']";
    	
    	@FindBy(xpath=inboxLocator)
		private WebElement linkInbox;
	
		@FindBy(xpath=todayLocator)
		private WebElement linkToday;
		
		public void selectInboxLink() {
			getDynamicWait().presenceOfElementLocatedByXPath(inboxLocator);
			getCustomClick().customizedClickElement(getDriver(), linkInbox, inboxLocator);
		}

		public void selectTodayLink() {
			getDynamicWait().presenceOfElementLocatedByXPath(todayLocator);
			getCustomClick().customizedClickElement(getDriver(), linkToday, todayLocator);
		}
	
		
		@Override
		protected void load() {
			
		}
		
		// *** Fix ***
		@Override
		protected void isLoaded() {
			DynamicWait.getInstance(getDriver()).waitUntilPageWithTitleLoads(getDriver().getTitle());
		}
		
		
		
}
