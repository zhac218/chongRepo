package com.pingidentity.pages.ManageTask;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.FindBy;

import com.pingidentity.pages.AbstractBasePage;
import com.pingidentity.wait.DynamicWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MilkManageTaskPage extends AbstractBasePage {
	
	private static final String taskInputLocator=".b-Qn-sm-Xs-Ys";
	private static final String taskButtonLocator="//button[@class='b-Qn-i-Yn Um-i' and text()='Add Task']";
	private static final String taskTextLocator="div.b-fb-an-Gj>span.b-fb-an-Oj";
	
	private static final String taskSelectLocator="//div[@aria-label='Select']";	
	private static final String markNoneLocator="//div[@class='Um-sn-Dn' and text()='None']";
	
	private static final String taskDueDateLocator="div.b-fb-an-Gj b-fb-an-ym";
	private static final String taskDueLocator="//div[@aria-label='Due']";	
	private static final String markTodayLocator="//div[@class='b-An-sn-jq' and text()='Today']";
	
	private static final String chkMoreLocator="//div[@aria-label='More']"; 
	private static final String deleteTaskLocator="//div[@class='Um-sn-Dn' and text()='Delete Task']";
	
	@FindBy(css=taskInputLocator)
	private WebElement txtBoxTaskInput;
	
	@FindBy(xpath=taskButtonLocator)
	private WebElement btnTask;
	
	@FindBy(css=taskTextLocator)
	private WebElement txtTask;
	
	@FindBy(xpath=taskSelectLocator)
	private WebElement btnSelect;
	
	@FindBy(xpath=markNoneLocator)
	private WebElement markNone;
	
	@FindBy(css=taskDueDateLocator)
	private WebElement txtDueDate;
	
	@FindBy(xpath=taskDueLocator)
	private WebElement dueTask;
	
	@FindBy(xpath=markTodayLocator)
	private WebElement markToday;

	@FindBy(xpath=chkMoreLocator)
	private WebElement chkMoreTask;
	
	@FindBy(xpath=deleteTaskLocator)
	private WebElement selectDeleteTask;

	public void enterTask(String txtTaskInput) {
		getDynamicWait().presenceOfElementLocatedByCSS(taskInputLocator);
		getDynamicWait().waitForElementToBeClickable(txtBoxTaskInput);
		txtBoxTaskInput.sendKeys(txtTaskInput);
	}
	
	public void clickTaskButton() {
		getDynamicWait().presenceOfElementLocatedByXPath(taskButtonLocator);
		getCustomClick().customizedClickElement(getDriver(), btnTask, taskButtonLocator);	
	}

	public WebElement findElement(By by) {
		WebElement elem = driver.findElement(by);
		// draw a border around the found element
		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", elem);
		}
		return elem;
	}

	public WebElement findElement(By by, int xOffSet, int yOffSet) throws IOException {
		WebElement ele = driver.findElement(by);

		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File screenshotAs = scrShot.getScreenshotAs(OutputType.FILE);

		// Get entire page screenshot
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage fullImg = ImageIO.read(screenshot);

		// Get the location of element on the page
		Point point = ele.getLocation();

		// Get width and height of the element
		int eleWidth = ele.getSize().getWidth();
		int eleHeight = ele.getSize().getHeight();

		// Crop the entire page screenshot to get only element screenshot
		BufferedImage eleScreenShot= fullImg.getSubimage(point.getX(), point.getY(),
				eleWidth, eleHeight);

		Graphics2D g = (Graphics2D) eleScreenShot.getGraphics();
		g.setStroke(new BasicStroke(3));
		g.setColor(Color.BLUE);
		g.drawOval(eleWidth/2 + xOffSet, eleHeight/2 + yOffSet, 2, 2);
//		g.drawRect(10, 10, eleScreenShot.getWidth() - 20, eleScreenShot.getHeight() - 20);


		ImageIO.write(eleScreenShot, "png", screenshot);


		Path path = Paths.get(System.getProperty("user.dir"), RandomStringUtils.randomAlphabetic(5) + ".png");

		// Copy the element screenshot to disk
		File screenshotLocation = new File(path.toAbsolutePath().toString());
		FileUtils.copyFile(screenshot, screenshotLocation);

		// draw a border around the found element
		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", ele);
		}
		return ele;
	}

	// Dynamic Checkbox:
	public Boolean selectCheckBox(String currTask) throws IOException, InterruptedException {
		Boolean selected = false;
		By locator = By.xpath("//*[@class='b-fn-Dn']//*[@aria-label='Select']");

		WebElement currTaskChkBox = findElement(locator, 10, 5);
		getDynamicWait().waitForElementToBeClickable(currTaskChkBox);

		Actions builder = new Actions(getDriver());
		builder.moveToElement(currTaskChkBox, 10, 5)
				.click()
				.build()
				.perform();

		Thread.sleep(2000); // replace with wait
		WebElement due_today = driver.findElement(By.xpath("//*[contains(text(), 'Due Today')]"));

		due_today.click();

		if (isCurrentCheckBoxSelected(currTask)) {
			log.info("Selectd Checkbox - no need to select the checkbox!");
			selected = true;
		} else if (isCurrentCheckBoxUnSelected(currTask)) {
			log.info("Unselectd Checkbox - need to select the checkbox!");
			getDynamicWait().waitUntilElementWasClicked(currTaskChkBox);
			selected = false;
		}
		return selected;
	}
	

	private Boolean isCheckboxSelected() {
		int numOfCheckedBox = 0;	
		numOfCheckedBox = numberOfSelectedCheckbox();
		
		return (numOfCheckedBox>0);
	}
	
	private String getSelectedCheckboxSelector() {
		String chkBoxSelectedLocator = "//span[@class='b-oo b-oo-po']";
		return chkBoxSelectedLocator;
	}
		
	private int numberOfSelectedCheckbox() {
		String chkBoxSelectedLocator = getSelectedCheckboxSelector();
		int numOfCheckedBox = 0;
		
		try {
			numOfCheckedBox = getDriver().findElements(By.xpath(chkBoxSelectedLocator)).size();
			log.info("Number of already selected checkbox: "+numOfCheckedBox);
		} catch (NoSuchElementException noExe) {
			log.info("None of selected checkbox: "+numOfCheckedBox);
		}
		
		return numOfCheckedBox;
	}
	
	public void selectNoneCheckbox() {
		if (isCheckboxSelected()) {
			log.info("Checkbox is already selected.  Select none.");
			selectButtonSelectTask();
			selectNone();
		} else {
			log.info("None of Checkbox is selected.");
		}
		waitForNoSelectedCheckBox(0);
	}
	
	
	// *****  Might need move to BasePage Class *****
	// Case: checkbox is already selected (true)
	private Boolean isCurrentCheckBoxSelected(String currTask) {
		Boolean selected;
		String currTaskChkBoxLocator = null;
		currTaskChkBoxLocator = "//div[@class='b-fb-an-Gj b-fb-an-Nn']/span[@class='b-fb-an-Oj' and text()='"+currTask+ "']/parent::div/preceding-sibling::div[5]/span[@class='b-oo b-oo-po']";
		
		try {
			getDriver().findElement(By.xpath(currTaskChkBoxLocator));
			selected =true;
			log.info("Checkbox was selected already.");
		}
		catch (NoSuchElementException noExe) {
			log.info("Not Selected Checkbox.");
			selected = false;
		} catch (TimeoutException timeoutExe) {
			selected = false;
		} catch (Exception e) {
			selected = false;
		}
		return selected;
	}
	
	// Case: checkbox is already unselected (true)
	private Boolean isCurrentCheckBoxUnSelected(String currTask) {
		Boolean unselected;
		String currTaskChkBoxLocator = null;
		currTaskChkBoxLocator = "//div[@class='b-fb-an-Gj b-fb-an-Nn']/span[@class='b-fb-an-Oj' and text()='"+currTask+ "']/parent::div/preceding-sibling::div[5]/span[@class='b-oo b-oo-unchecked']";
		
		try {
			getDriver().findElement(By.xpath(currTaskChkBoxLocator));
			unselected =true;
			log.info("Checkbox was unselected!");
		}
		catch (NoSuchElementException noExe) {
			unselected = false;
		} catch (TimeoutException timeoutExe) {
			unselected = false;
		}
		return unselected;
	}

	public String getTaskText(String currTask) {
		String currentTask = "";
		
		if (numOfTask()>0) {
			currentTask = getDriver().findElement(By.xpath("//div[@class='b-fb-an-Gj b-fb-an-Nn']/span[@class='b-fb-an-Oj' and text()='"+currTask+ "']")).getText();
		}
		return currentTask;
	}
	
	public String getXPathTaskText(String currTask) {
		String currentXPathTask = "";
		currentXPathTask = "//div[@class='b-fb-an-Gj b-fb-an-Nn']/span[@class='b-fb-an-Oj' and text()='"+currTask+ "']";
		return currentXPathTask;
	}
		
	public int numOfTask() {
		return getDriver().findElements(By.cssSelector(taskTextLocator)).size();
	}
		
	public Boolean taskTextExist(String currTask) {
		
		boolean taskExist = true;
		
		try {		
			getDriver().findElement(By.xpath("//div[@class='b-fb-an-Gj b-fb-an-Nn']/span[@class='b-fb-an-Oj' and text()='"+currTask+ "']"));
			log.info("Found Element");
			taskExist= true;
		} catch (NoSuchElementException noElement) {
			log.info("No Longer exist");
			taskExist = false;
		}
		return taskExist;
	}
		
	public void selectButtonSelectTask() {
		getDynamicWait().waitUntilElementWasClicked(btnSelect);
	}
	
	public void selectNone() {
		getDynamicWait().waitUntilElementWasClicked(markNone);
	}
	
	
	public void selectDueTask() {
		getDynamicWait().waitUntilElementWasClicked(dueTask);
	}
	
	public void selectToday() {
		getDynamicWait().waitUntilElementWasClicked(markToday);
	}
	
	public String getTxtDueDate() {
		return txtDueDate.getText();
	}
			
	public void selectMoreTask() {
		getDynamicWait().waitUntilElementWasClicked(chkMoreTask);
	
	}
	
	public void selectDeleteTask() {
		getDynamicWait().isElementIsClickable(By.xpath(deleteTaskLocator), TIMEOUT);
		selectDeleteTask.click();
	}
	
	public void waitForNoSelectedCheckBox(int finalTotal) {
		int calculatedNum = getDynamicWait().waitUntilNumberOfElementsByXPath(getSelectedCheckboxSelector(), finalTotal).size();
		log.info("After Calculation: "+calculatedNum);
	}
	
	public void waitForNumberofElements(int finalTotal) {
		int calculatedNum = getDynamicWait().waitUntilNumberOfElementsByCSS(taskTextLocator, finalTotal).size();
		log.info("After Calculation: "+calculatedNum);
	}
	
	@Override
	protected void load() {
		
	}
	
	@Override
	protected void isLoaded() {
		DynamicWait.getInstance(getDriver()).waitUntilPageWithTitleLoads(getDriver().getTitle());
		
		//taskDueLocator
		getDynamicWait().isElementPresent(By.xpath(taskDueLocator), TIMEOUT);
		getDynamicWait().isElementIsVisible(By.xpath(taskDueLocator), TIMEOUT);
		getDynamicWait().isElementIsClickable(By.xpath(taskDueLocator), TIMEOUT);
		
		//markTodayLocator
		getDynamicWait().isElementPresent(By.xpath(markTodayLocator), TIMEOUT);
		getDynamicWait().isElementIsVisible(By.xpath(markTodayLocator), TIMEOUT);
		getDynamicWait().isElementIsClickable(By.xpath(markTodayLocator), TIMEOUT);		
		
		// Select: 
//		getDynamicWait().isElementPresent(By.xpath(taskSelectLocator), TIMEOUT);
//		getDynamicWait().isElementIsVisible(By.xpath(taskSelectLocator), TIMEOUT);
//		getDynamicWait().isElementIsClickable(By.xpath(taskSelectLocator), TIMEOUT);
//				
//		// None: 
//		getDynamicWait().isElementPresent(By.xpath(markNoneLocator), TIMEOUT);
//		getDynamicWait().isElementIsVisible(By.xpath(markNoneLocator), TIMEOUT);
//		getDynamicWait().isElementIsClickable(By.xpath(markNoneLocator), TIMEOUT);		
		
		// More: 
		getDynamicWait().isElementPresent(By.xpath(chkMoreLocator), TIMEOUT);
		getDynamicWait().isElementIsVisible(By.xpath(chkMoreLocator), TIMEOUT);
		getDynamicWait().isElementIsClickable(By.xpath(chkMoreLocator), TIMEOUT);

		// Delete Task:
		getDynamicWait().isElementPresent(By.xpath(deleteTaskLocator), TIMEOUT);
		getDynamicWait().isElementIsVisible(By.xpath(deleteTaskLocator), TIMEOUT);
	}
		
}