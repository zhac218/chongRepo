package com.pingidentity.businessworkflow;
import com.pingidentity.AbstractBase;
import com.pingidentity.pages.MilkHomePage;
import com.pingidentity.pages.MilkLoginPage;
import com.pingidentity.task.pages.AdminTask.MilkAdminTaskPage;
import com.pingidentity.task.pages.ManageTask.MilkManageTaskPage;
import com.pingidentity.task.pages.ScheduleTask.MilkScheduleTaskPage;

import junit.framework.Assert;

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
