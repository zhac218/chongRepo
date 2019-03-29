package com.pingidentity.businessworkflow;
import com.pingidentity.AbstractBase;
import com.pingidentity.pages.AdminTask.MilkAdminTaskPage;
import com.pingidentity.pages.MilkHomePage;
import com.pingidentity.pages.MilkLoginPage;
import com.pingidentity.pages.ScheduleTask.MilkScheduleTaskPage;

public class UserActionCase extends AbstractBase {

	public MilkAdminTaskPage validUserLogInCase() {
		MilkHomePage homePage = new MilkHomePage();
		MilkLoginPage loginPage = homePage.goToLoginPage();
		
		loginPage.enterUserCredentials();
		MilkAdminTaskPage adminTaskPage = loginPage.goToTaskPage();
		return adminTaskPage;
	}
	
	public MilkScheduleTaskPage userSelectInbox() {
		MilkScheduleTaskPage scheduleTaskPage = new MilkScheduleTaskPage();
		scheduleTaskPage.selectInboxLink();
		return scheduleTaskPage;
	}
	
	
}
