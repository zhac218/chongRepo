package com.pingidentity.tests;

import org.junit.Test;
import com.pingidentity.tests.BaseTest;

import junit.framework.Assert;

import com.pingidentity.businessworkflow.UserActionCase;
import com.pingidentity.task.pages.AdminTask.MilkAdminTaskPage;
import com.pingidentity.task.pages.ManageTask.MilkManageTaskPage;
import com.pingidentity.task.pages.ScheduleTask.MilkScheduleTaskPage;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SmokeTest extends BaseTest {
    
	 	private static String TESTDATA = "ad";  
	 	static final int TIMEOUT = 5;
	
		//@Test
		public void A_successfulLoginTest() {	
			System.out.println("1. Valid User Credential Test");
			UserActionCase userLoginCase = new UserActionCase();
			userLoginCase.validUserLogInCase();

			String adminPageTitle = getDriver().getTitle();
			Assert.assertTrue(adminPageTitle.contains("Remember The Milk"));
		}
		
		//@Test
		public void B_createNewTaskSuccess () {
			System.out.println("2. Create a task Test");
			UserActionCase userActionCase = new UserActionCase();
			userActionCase.validUserLogInCase();

			userActionCase.userSelectInbox();
			
			MilkManageTaskPage manageTaskPage = new MilkManageTaskPage();
			manageTaskPage.enterTask(TESTDATA);
			manageTaskPage.clickTaskButton();
				
			getDynamicWait().waitUntilTextToBePresentInElementByXPath(manageTaskPage.getXPathTaskText(TESTDATA), TESTDATA, TIMEOUT);
			System.out.println("Task Name: "+manageTaskPage.getTaskText(TESTDATA));
			Assert.assertEquals(TESTDATA, manageTaskPage.getTaskText(TESTDATA));
		}
		
		//@Test
		public void C_readTaskSuccess() {
			System.out.println("3. Read Task Test");
			
			UserActionCase userActionCase = new UserActionCase();
			userActionCase.validUserLogInCase();
				
			userActionCase.userSelectInbox();
			
			MilkManageTaskPage manageTaskPage = new MilkManageTaskPage();
			
			getDynamicWait().waitUntilTextToBePresentInElementByXPath(manageTaskPage.getXPathTaskText(TESTDATA), TESTDATA, TIMEOUT);
			Assert.assertEquals(TESTDATA, manageTaskPage.getTaskText(TESTDATA));
		}
		
		@Test
		public void D_updateTaskToTodaySuccess() {
			System.out.println("4. Update Task to Today Test");
			
			UserActionCase userActionCase = new UserActionCase();
			userActionCase.validUserLogInCase();
				
			MilkScheduleTaskPage scheduleTaskPage = userActionCase.userSelectInbox();
			
			MilkManageTaskPage manageTaskPage = new MilkManageTaskPage();
			//getStaticWait().waitForMilliSeconds(5500);
			//manageTaskPage.selectButtonSelectTask();
			manageTaskPage.selectNoneCheckbox();
			//getStaticWait().waitForMilliSeconds(5500);
			
			manageTaskPage.selectCheckBox(TESTDATA);
			manageTaskPage.selectDueTask();
			manageTaskPage.selectToday();
			
			// Check for Today text:
			scheduleTaskPage.selectTodayLink();
						
			getDynamicWait().waitUntilTextToBePresentInElementByXPath(manageTaskPage.getXPathTaskText(TESTDATA), TESTDATA, TIMEOUT);
			System.out.println("Task Name: "+manageTaskPage.getTaskText(TESTDATA));
			Assert.assertEquals(TESTDATA, manageTaskPage.getTaskText(TESTDATA));	
		}
		
		@Test
		public void E_deleteTaskSuccess() {
			System.out.println("5. Delete Test");
			
			UserActionCase userActionCase = new UserActionCase();
			MilkAdminTaskPage adminTaskPage = userActionCase.validUserLogInCase();
			
			userActionCase.userSelectInbox();
			
			MilkManageTaskPage manageTaskPage = new MilkManageTaskPage();
			
			int numTaskFirst = manageTaskPage.numOfTask();
			System.out.println("Num of Task at the beginning: "+numTaskFirst);
			manageTaskPage.selectNoneCheckbox();
			
			manageTaskPage.selectCheckBox(TESTDATA);
			manageTaskPage.selectMoreTask();
			manageTaskPage.selectDeleteTask();
			
			getDynamicWait().waitUntilPageLoaded();
			manageTaskPage.waitForNumberofElements(numTaskFirst-1);
			
			int numTaskAfter = manageTaskPage.numOfTask();
			System.out.println("Num of Task after delete: "+numTaskAfter);
			
			//getDynamicWait().waitInvisibilityByXPath(manageTaskPage.getXPathTaskText(TESTDATA), TIMEOUT);
			//Assert.assertFalse(manageTaskPage.taskTextExist(TESTDATA));	
			Assert.assertEquals(1, numTaskFirst-numTaskAfter);
		}
		
}