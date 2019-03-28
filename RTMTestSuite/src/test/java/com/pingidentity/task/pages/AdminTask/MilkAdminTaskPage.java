package com.pingidentity.task.pages.AdminTask;

import com.pingidentity.wait.DynamicWait;
import com.pingidentity.config.ConfigProperties;
import com.pingidentity.pages.AbstractSynchronousPage;
import com.pingidentity.task.pages.DetailTask.MilkDetailTaskPage;
import com.pingidentity.task.pages.ManageTask.MilkManageTaskPage;
import com.pingidentity.task.pages.ScheduleTask.MilkScheduleTaskPage;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;

public class MilkAdminTaskPage extends AbstractSynchronousPage {
	
	protected MilkScheduleTaskPage scheduleTaskPage;
	protected MilkManageTaskPage manageTaskPage;
	protected MilkDetailTaskPage detailTaskPage;

	@FindBy(xpath="//span[@class='b-g-Kt-dk-qm']")	
	private WebElement txtWarning;
	
	public MilkAdminTaskPage() {
		checkForAlert();
		MilkScheduleTaskPage scheduleTaskPage = new MilkScheduleTaskPage();
		MilkManageTaskPage manageTaskPage = new MilkManageTaskPage();
		MilkDetailTaskPage detailTaskPage = new MilkDetailTaskPage();
	}
	
	public MilkScheduleTaskPage getScheduleTaskPage() {
		return scheduleTaskPage;
	}

	public MilkManageTaskPage getManageTaskPage() {
		return manageTaskPage;
	}

	public MilkDetailTaskPage getDetailTaskPage() {
		return detailTaskPage;
	}

	public void setScheduleTaskPage(MilkScheduleTaskPage scheduleTaskPage) {
		this.scheduleTaskPage = scheduleTaskPage;
	}

	public void setManageTaskPage(MilkManageTaskPage manageTaskPage) {
		this.manageTaskPage = manageTaskPage;
	}

	public void setDetailTaskPage(MilkDetailTaskPage detailTaskPage) {
		this.detailTaskPage = detailTaskPage;
	}

	public String getTxtWarning() {
		return txtWarning.getText();
	}
	
	@Override
	public void waitPageLoad() {
		DynamicWait.getInstance(getDriver()).waitUntilPageWithTitleLoads(getDriver().getTitle());
	} 

	@Override
	protected void load() {
		
	}
	
	// *** FIX ***
	@Override
	protected void isLoaded() {
		DynamicWait.getInstance(getDriver()).waitUntilPageWithTitleLoads(getDriver().getTitle());
	}
	
	
	
}