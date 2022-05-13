package com.cucumber.framework.helper.PageObject.homepage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.cucumber.framework.helper.PageObject.PageBase;

public class ActivityPage extends PageBase {
	
	private WebDriver driver;

	public ActivityPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	
	/** Web Elements */
	public String module="Activity";

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@FindBy(how=How.ID,using="activity1")
	public WebElement activityTab;
	
	@FindBy(how=How.ID,using="fromDate")
	public WebElement fromDate;
	
	@FindBy(how=How.ID,using="toDate")
	public WebElement toDate;
	
	
	@FindBy(how=How.XPATH,using="//*[@id=\"activity_destination\"]")
	public WebElement location;

	@FindBy(how=How.XPATH,using="//*[@id=\"activitySearchForm\"]/button")
	public WebElement btnSearch;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"result\"]/div/div/div/div[1]/div[1]/div/div/span")
	public WebElement numOfActivity;
	
	
	@FindBy(how=How.XPATH,using="//*[@id=\"result\"]/div/div/div/div[2]/div[3]/ul/li[1]/div/div[1]/div[3]/div/p/text()")
	public WebElement lowestPrice;	
	
	//@FindBy(how=How.CSS,using="li[class='activity_main_li_box']")
	@FindBy(how=How.XPATH,using="/html/body/div[3]/div/section/div/div/div/div[1]/div[1]/div/div")
	public List<WebElement> resultBoxes;
	
	
	
	/** Public Methods  **/	
	public WebDriver getDriver() {
		return this.driver;
	}
	
	
}




