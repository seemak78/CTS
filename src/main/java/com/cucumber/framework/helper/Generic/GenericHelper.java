/**
 * @author Seema
 *	
 *	06-Aug-2016
 */
package com.cucumber.framework.helper.Generic;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.cucumber.framework.helper.Logger.LoggerHelper;
import com.cucumber.framework.interfaces.IwebComponent;
import com.cucumber.framework.utility.DateTimeHelper;
import com.cucumber.framework.utility.ResourceHelper;


/**
 * @author Seema
 *
 *         06-Aug-2016
 *
 */
public class GenericHelper implements IwebComponent {

	private WebDriver driver;
	private Logger oLog = LoggerHelper.getLogger(GenericHelper.class);

	public GenericHelper(WebDriver driver) {
		this.driver = driver;
		oLog.debug("GenericHelper : " + this.driver.hashCode());
	}

	public WebElement getElement(By locator) {
		oLog.info(locator);
		if (IsElementPresentQuick(locator))
			return driver.findElement(locator);
		
		try {
			throw new NoSuchElementException("Element Not Found : " + locator);
		} catch (RuntimeException re) {
			oLog.error(re);
			throw re;
		}
	}
	
	/**
	 * Check for element is present based on locator
	 * If the element is present return the web element otherwise null
	 * @param locator
	 * @return WebElement or null
	 */
	
	public WebElement getElementWithNull(By locator) {
		oLog.info(locator);
		try {
			return driver.findElement(locator);
		} catch (NoSuchElementException e) {
			// Ignore
		}
		return null;
	}

	public boolean IsElementPresentQuick(By locator) {
		boolean flag = driver.findElements(locator).size() >= 1;
		oLog.info(flag);
		return flag;
	}

	public String takeScreenShot(String name) throws IOException {

		if (driver instanceof HtmlUnitDriver) {
			oLog.fatal("HtmlUnitDriver Cannot take the ScreenShot");
			return "";
		}

		File destDir = new File(ResourceHelper.getResourcePath("screenshots/")
				+ DateTimeHelper.getCurrentDate());
		if (!destDir.exists())
			destDir.mkdir();

		File destPath = new File(destDir.getAbsolutePath()
				+ System.getProperty("file.separator") + name + ".jpg");
		try {
			FileUtils
					.copyFile(((TakesScreenshot) driver)
							.getScreenshotAs(OutputType.FILE), destPath);
		} catch (IOException e) {
			oLog.error(e);
			throw e;
		}
		oLog.info(destPath.getAbsolutePath());
		return destPath.getAbsolutePath();
	}

	
	
	public String takeScreenShot() {
		oLog.info("");
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}

	public void jQueryAutoCompleteDropdown(WebElement cSelect, String searchKey, int timeOutInSeconds, int keyWaitMilSec) {
		driver.manage().timeouts().implicitlyWait(timeOutInSeconds,TimeUnit.SECONDS);
		cSelect.sendKeys(searchKey);
		try {
			Thread.sleep(keyWaitMilSec);
		} catch (InterruptedException e) {
			oLog.error("Error in jQueryAutoCompleteDropdown:"+e.getLocalizedMessage());
		}
		cSelect.sendKeys(Keys.ARROW_DOWN);
		try {
			Thread.sleep(keyWaitMilSec);
		} catch (InterruptedException e) {
			oLog.error("Error in jQueryAutoCompleteDropdown:"+e.getLocalizedMessage());
		}
		cSelect.sendKeys(Keys.ENTER);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			oLog.error("Error in jQueryAutoCompleteDropdown:"+e.getLocalizedMessage());
		}
	}
	
	
	public void populateReadonlyDate(WebElement dtControl, String strDate) {
	    JavascriptExecutor ex = (JavascriptExecutor) driver;
	    ex.executeScript("arguments[0].value='"+ strDate +"';", dtControl);
	}
	
	public void populateReadonlyDate(WebElement dtControl, String strDate, String time) {
	    JavascriptExecutor ex = (JavascriptExecutor) driver;
	    ex.executeScript("arguments[0].value='"+ strDate+" "+time+"';", dtControl);
	}
	
	public String dateCalculator(int addYear, int addMonth, int addNumOfDays)
	{
		return dateCalculator(addYear, addMonth, addNumOfDays, "MM/dd/yyyy");
	}

	public String dateCalculator(int addYear, int addMonth, int addNumOfDays, String DATE_FORMAT)
	{
		// Date Format to be displayed
	    DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	    //DateTimeFormatter dateFormat8 = DateTimeFormatter.ofPattern(DATE_FORMAT);

	    // Get current Date
        Date currentDate = new Date();
 
        // convert date to local date time
        LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        
        // add parameters
        localDateTime = localDateTime.plusYears(addYear).plusMonths(addMonth).plusDays(addNumOfDays);
        //localDateTime = localDateTime.plusHours(1).plusMinutes(2).minusMinutes(1).plusSeconds(1);

        // convert LocalDateTime to date
        Date currentDatePlusOneDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        String strDate = dateFormat.format(currentDatePlusOneDay);
		return strDate;
	}
	
}
