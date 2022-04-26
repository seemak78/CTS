package com.cucumber.framework.helper.PageObject.homepage;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.cucumber.framework.helper.PageObject.PageBase;

public class CruisePage extends PageBase {
	
	private WebDriver driver;

	public CruisePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	/** Web Elements */
	public String module="Cruise";

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@FindBy(how=How.ID,using="cruise1")
	public WebElement cruiseTab;
	
	@FindBy(how=How.ID,using="pickupDate2")
	public WebElement pickupDate;
	
	@FindBy(how=How.ID,using="dropoffDate2")
	public WebElement dropoffDate;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"carSearchForm2\"]/div[1]/div[1]/div/input[1]")
	public WebElement pickupCity;

	@FindBy(how=How.XPATH,using="//*[@id=\"carSearchForm2\"]/div[1]/div[2]/div/input[1]")
	public WebElement dropoffCity;

	@FindBy(how=How.XPATH,using="//*[@id=\"carSearchForm2\"]/div[3]/button")
	public WebElement btnSearch; 
	

	
	
	@FindBy(how=How.XPATH,using="//*[@id=\"result\"]/div[1]/div/div/div[1]/div[1]/div/div/span")
	public WebElement numOfCars;	
	
	@FindBy(how=How.XPATH,using="//*[@id=\"car_listing_tmpl\"]/li[1]/div/div/div[2]/div/div[2]/ul/li[1]/span")
	public WebElement lowestPrice;	
	
	@FindBy(how=How.CLASS_NAME,using="car_main_li_box")
	public List<WebElement> resultBoxes;
	
	/** Public Methods  **/	
	public WebDriver getDriver() {
		return this.driver;
	}
	
	
}
