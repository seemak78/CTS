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
	
	@FindBy(how=How.XPATH,using="//*[@id=\"prop-15202579\"]/div/div/div[1]/div/div[2]/div[4]/a")
	public WebElement selRoom;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"g1\"]/div[2]/div[1]/div/div/div[2]/div[2]/div/a")
	public List<WebElement> bookNow;
	
	@FindBy(how=How.CSS, using=".btn.av_btn_text")
	public List<WebElement> bookNowList;	
	
	@FindBy(how=How.XPATH,using="//*[@id='step-1']/div/div/div/div[2]/div[1]/div/div[2]/input")
	public WebElement firstName;

	@FindBy(how=How.XPATH,using="//*[@id=\"adult0\"]")
	public WebElement lastName;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"step-1\"]/div/div/div/div[2]/div[2]/div/div[2]/input")
	public WebElement firstName1;

	@FindBy(how=How.XPATH,using="//*//*[@id=\"step-1\"]/div/div/div/div[2]/div[2]/div/div[3]/input")
	public WebElement lastName1;
	
	@FindBy(how=How.XPATH, using="//*[@id=\"nextBtn\"]")
	public WebElement continu;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"step-2\"]/div[1]/div/div/div[1]/div[2]/input")
	public WebElement firstName2;

	@FindBy(how=How.XPATH,using="//*[@id=\"step-2\"]/div[1]/div/div/div[1]/div[3]/input")
	public WebElement lastName2;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"step-2\"]/div[1]/div/div/div[2]/div[1]/input")
	public WebElement email;
	
	@FindBy(how=How.XPATH, using="//*[@id=\"step-2\"]/div[1]/div/div/div[2]/div[2]/input")
	public WebElement contact;
	
	//@FindBy(how=How.XPATH,using="//*[@id=\"step-2\"]/div[2]/div/div/div/div/label/input")
	
	@FindBy(how=How.NAME,using="accept_terms")
	public WebElement checkbox;
	
	@FindBy(how=How.XPATH, using="//*[@id=\"payNowBtn\"]")
	public WebElement book;
	
	@FindBy(how=How.XPATH, using="//*[@id=\"card_number\"]")
	public WebElement card;
	
	
	@FindBy(how=How.XPATH, using="/html/body/iframe")
	public WebElement payPageMainFrame;
	
	
	@FindBy(how=How.XPATH, using="/html/body/div[2]/section/div[1]/div[1]/form/div[1]/div[2]/fieldset[3]/span/div[2]/iframe")
	public WebElement payPageIframe;
	
	/** Public Methods  **/	
	public WebDriver getDriver() {
		return this.driver;
	}
	
	
}
