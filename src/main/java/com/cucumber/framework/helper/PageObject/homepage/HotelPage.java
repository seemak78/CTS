package com.cucumber.framework.helper.PageObject.homepage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.cucumber.framework.helper.PageObject.PageBase;

public class HotelPage extends PageBase {
	
	private WebDriver driver;

	public HotelPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	/** Web Elements */
	public String module="Hotel";
	
	public String getModule() {
		return module;
	}


	public void setModule(String module) {
		this.module = module;
	}

	@FindBy(how=How.ID,using="hotel1")
	public WebElement hotelTab;
	
	@FindBy(how=How.ID,using="checkIn")
	public WebElement checkInDate;

	@FindBy(how=How.ID,using="checkOut")
	public WebElement checkOutDate;

	@FindBy(how=How.XPATH,using="//*[@id=\"geocomplete_vvt\"]")
	public WebElement location;

	@FindBy(how=How.ID,using="searchBtnHome")
	public WebElement btnSearch;
		
	@FindBy(how=How.ID,using="searchRecords")
	public WebElement numOfHotels;	
	
	@FindBy(how=How.ID,using="results-section")
	public List<WebElement> hotelResults;
	
	@FindBy(how=How.CSS ,using="div[class='error_page']")
	public List<WebElement> errorList;	
	
	@FindBy(how=How.XPATH, using="/html/body/section/div[2]/div/div/div[2]/div[2]/article[1]/div/div/div[2]/div/div/div/div[2]/p[2]/span")
	public WebElement lowestPrice;	
	
	@FindBy(how=How.CSS, using="span[class='price_class pay_price']")
	public List<WebElement> lowestPriceList;	
	
	@FindBy(how=How.XPATH,using="/html/body/div[2]/div[2]/div/div[1]/div/div/h1")
	public WebElement landingPageMessage;
	
	@FindBy(how=How.XPATH,using="/html/body/div[2]/div[2]/div/div[1]/div/div/h1")
	public List<WebElement> landingPageMessages;

	
	/** Public Methods  **/	
	public WebDriver getDriver() {
		return this.driver;
	}
	
	
}
