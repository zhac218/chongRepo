package com.pingidentity.tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import junit.framework.Assert;

import com.pingidentity.businessworkflow.UserActionCase;
import com.pingidentity.pages.AdminTask.MilkAdminTaskPage;
import com.pingidentity.pages.ManageTask.MilkManageTaskPage;
import com.pingidentity.pages.ScheduleTask.MilkScheduleTaskPage;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.io.IOException;

@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SmokeTest extends BaseTest {
    
	 	private static String TESTDATA = "ad";  
	 	static final int TIMEOUT = 5;
	
		//@Test
		public void A_successfulLoginTest() {	
			log.info("1. Valid User Credential Test");
			UserActionCase userLoginCase = new UserActionCase();
			userLoginCase.validUserLogInCase();

			String adminPageTitle = getDriver().getTitle();
			Assert.assertTrue(adminPageTitle.contains("Remember The Milk"));
		}
		
		//@Test
		public void B_createNewTaskSuccess () {
			log.info("2. Create a task Test");
			UserActionCase userActionCase = new UserActionCase();
			userActionCase.validUserLogInCase();

			userActionCase.userSelectInbox();
			
			MilkManageTaskPage manageTaskPage = new MilkManageTaskPage();
			manageTaskPage.enterTask(TESTDATA);
			manageTaskPage.clickTaskButton();
				
			getDynamicWait().waitUntilTextToBePresentInElementByXPath(manageTaskPage.getXPathTaskText(TESTDATA), TESTDATA, TIMEOUT);
			log.info("Task Name: "+manageTaskPage.getTaskText(TESTDATA));
			Assert.assertEquals(TESTDATA, manageTaskPage.getTaskText(TESTDATA));
		}
		
		//@Test
		public void C_readTaskSuccess() {
			log.info("3. Read Task Test");
			
			UserActionCase userActionCase = new UserActionCase();
			userActionCase.validUserLogInCase();
				
			userActionCase.userSelectInbox();
			
			MilkManageTaskPage manageTaskPage = new MilkManageTaskPage();
			
			getDynamicWait().waitUntilTextToBePresentInElementByXPath(manageTaskPage.getXPathTaskText(TESTDATA), TESTDATA, TIMEOUT);
			Assert.assertEquals(TESTDATA, manageTaskPage.getTaskText(TESTDATA));
		}
		
		@Test
		public void D_updateTaskToTodaySuccess() throws IOException, InterruptedException {
			log.info("4. Update Task to Today Test");
			
			UserActionCase userActionCase = new UserActionCase();
			userActionCase.validUserLogInCase();
				
			MilkScheduleTaskPage scheduleTaskPage = userActionCase.userSelectInbox();
			
			MilkManageTaskPage manageTaskPage = new MilkManageTaskPage();
			//manageTaskPage.selectNoneCheckbox();
			
			manageTaskPage.selectCheckBox(TESTDATA);
			manageTaskPage.selectDueTask();
			manageTaskPage.selectToday();
			
			// Check for Today text:
			scheduleTaskPage.selectTodayLink();
						
			getDynamicWait().waitUntilTextToBePresentInElementByXPath(manageTaskPage.getXPathTaskText(TESTDATA), TESTDATA, TIMEOUT);
			log.info("Task Name: "+manageTaskPage.getTaskText(TESTDATA));
			Assert.assertEquals(TESTDATA, manageTaskPage.getTaskText(TESTDATA));	
		}
		
		@Test
		public void E_deleteTaskSuccess() throws IOException, InterruptedException {
			log.info("5. Delete Test");
			
			UserActionCase userActionCase = new UserActionCase();
			MilkAdminTaskPage adminTaskPage = userActionCase.validUserLogInCase();
			
			userActionCase.userSelectInbox();
			
			MilkManageTaskPage manageTaskPage = new MilkManageTaskPage();
			
			int numTaskFirst = manageTaskPage.numOfTask();
			log.info("Num of Task at the beginning: "+numTaskFirst);
			//manageTaskPage.selectNoneCheckbox();
			
			manageTaskPage.selectCheckBox(TESTDATA);
			manageTaskPage.selectMoreTask();
			manageTaskPage.selectDeleteTask();
			
			getDynamicWait().waitUntilPageLoaded();
			manageTaskPage.waitForNumberofElements(numTaskFirst-1);
			
			int numTaskAfter = manageTaskPage.numOfTask();
			log.info("Num of Task after delete: "+numTaskAfter);
			
			//getDynamicWait().waitInvisibilityByXPath(manageTaskPage.getXPathTaskText(TESTDATA), TIMEOUT);
			//Assert.assertFalse(manageTaskPage.taskTextExist(TESTDATA));	
			Assert.assertEquals(1, numTaskFirst-numTaskAfter);
		}
		
}