package com.cucumber.framework.helper.PageObject.homepage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import com.cucumber.framework.helper.PageObject.PageBase;

public class CarPage extends PageBase {
	
	private WebDriver driver;

	public CarPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	/** Web Elements */
	public String module="Car";

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@FindBy(how=How.ID,using="carRental")
	public WebElement carTab;
	
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
	
	@FindBy(how=How.XPATH,using="//*[@id=\"car_listing_tmpl\"]/li[1]/div/div/div[2]/div/div[2]/ul/li[2]/a")
	public WebElement bookNow;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"title\"]")
	public WebElement title;
	
	@FindBy(how=How.NAME,using="phonenumber")
	public WebElement contactno;
	
	
	@FindBy(how=How.NAME,using="nationality")
	public WebElement country;
	
	@FindBy(how=How.NAME,using="terms_condition_checkbox")
	public WebElement check;
	
	@FindBy(how=How.XPATH,using="/html/body/div[3]/div/div[6]/div/div/div[2]/div/form/div[1]/div[2]/div[10]/button")
	public WebElement bookN;
	
	
	
	/** Public Methods  **/	
	public WebDriver getDriver() {
		return this.driver;
	}
	
	
}
