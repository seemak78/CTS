package com.cucumber.framework.helper.PageObject.homepage;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.framework.configreader.PropertyFileReader;
import com.cucumber.framework.helper.Logger.LoggerHelper;
import com.cucumber.framework.helper.PageObject.PageBase;
import com.cucumber.framework.interfaces.IconfigReader;
import com.cucumber.framework.settings.ObjectRepo;

public class LoginPage extends PageBase {
	
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(this.getClass());

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	/** Web Elements */
	
	@FindBy(how=How.NAME,using="email")
	public WebElement email;
	
	@FindBy(how=How.NAME,using="password")
	public WebElement password;
	
	@FindBy(how=How.XPATH,using="/html/body/div[2]/div/div/div[3]/div[2]/div/div/div[2]/form/div[3]/div[1]/button")
	public WebElement loginbutton;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"grouped-pageload-Banner\"]/div/div/div/div[3]/button")
	public WebElement cookiebutton;

	@FindBy(how=How.XPATH,using="/html/body/div[2]/div/div/div[3]/div[2]/div/div/div[2]/form/div[1]")
	public WebElement loginErrorMessage;

	
	/** Public Methods  **/	
	public WebDriver getDriver() {
		return this.driver;
	}
	
	
	public void login() {
		email.sendKeys(ObjectRepo.reader.getWebsiteUsername());
		password.sendKeys(ObjectRepo.reader.getWebsitePassword());
		loginbutton.click();
		log.info("Login complete");
	}
	
	public void acceptCookies() {
		
		String winHandleBefore = this.driver.getWindowHandle();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			log.error("Issue with acceptCookies() function:"+e.getLocalizedMessage().toString());
		}
		// frame load explicit condition
		WebDriverWait w = new WebDriverWait(this.driver, 5);    
		w.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ifrmCookieBanner"));
    	// identify element inside frame
    	this.cookiebutton.click();
		log.info("Cookie dismissed");
		driver.switchTo().window(winHandleBefore);
	}
}
