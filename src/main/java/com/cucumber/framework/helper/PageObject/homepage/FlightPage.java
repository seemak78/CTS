package com.cucumber.framework.helper.PageObject.homepage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.cucumber.framework.helper.PageObject.PageBase;

public class FlightPage extends PageBase {
	
	private WebDriver driver;

	public FlightPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	/** Web Elements */
	public String module="Flight";

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@FindBy(how=How.ID,using="flight1")
	public WebElement flightTab;
	
	@FindBy(how=How.ID,using="departureDate_r")
	public WebElement departDate;
	
	@FindBy(how=How.ID,using="arrivalDate_r")
	public WebElement arrivalDate;

	@FindBy(how=How.CLASS_NAME,using="round_trip")
	public WebElement roundTrip;

	@FindBy(how=How.CLASS_NAME,using="one_way")
	public WebElement oneWay;

	@FindBy(how=How.CLASS_NAME,using="multi_city")
	public WebElement multiCity;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"flightSearchForm\"]/div[1]/div[1]/div/input[1]")
	public WebElement fromCity;

	@FindBy(how=How.XPATH,using="//*[@id=\"flightSearchForm\"]/div[1]/div[2]/div/input[1]")
	public WebElement toCity;

	@FindBy(how=How.XPATH,using="//*[@id=\"flightSearchForm\"]/button")
	public WebElement btnSearch;

	@FindBy(how=How.XPATH,using="//*[@id=\"result\"]/div[1]")
	public WebElement visibleLoader;

//	@FindBy(how=How.XPATH,using="//*[@id=\"result\"]/div[1]")
	public By invisibleLoader = By.xpath("//*[@id=\"result\"]/div[1]");
	
	@FindBy(how=How.XPATH,using="//*[@id=\"result\"]/div[2]/div/div/div[1]/div[1]/div/div/span")
	public WebElement numOfFlights;	
	
	@FindBy(how=How.XPATH,using="//*[@id=\"result\"]/div[2]/div/div/div[2]/div[3]/ul/li[1]/div/div[3]/div[2]/ul/li[1]/span")
	public WebElement lowestPrice;	
	
	@FindBy(how=How.CSS,using="li[class='main_li_box']")
	public List<WebElement> resultBoxes;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"result\"]/div[2]/div/div/div[2]/div[3]/ul/li[1]/div/div[3]/div[2]/a")
	public List<WebElement> bookNo;
	
	@FindBy(how=How.ID,using="adulthtml1")
	public WebElement passenger;
	
	@FindBy(how=How.NAME,using="title[]")
	public WebElement title;
	
	@FindBy(how=How.NAME,using="bdate[]")
	public WebElement birthdate;
	
	@FindBy(how=How.NAME,using="gender[]")
	public WebElement gender;
	
	@FindBy(how=How.NAME,using="nationality[]")
	public WebElement coun;
	
	@FindBy(how=How.NAME,using="postcode")
	public WebElement postal;
	
	@FindBy(how=How.NAME,using="phonenumber")
	public WebElement phone;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"formCheckout\"]/div/div[1]/div/div[2]/div[4]/button")
	public WebElement book;
	
	@FindBy(how=How.XPATH,using="/html/body/iframe")
	public WebElement mainFrame;
	
	@FindBy(how=How.XPATH,using="/html/body/div/div/iframe")
	public WebElement subFrame1;
	
	@FindBy(how=How.CSS,using=".razorpay-checkout-frame")
	public WebElement subFrame2;
	
	
	
	@FindBy(how=How.ID,using="email")
	public WebElement email;
	
	
	/** Public Methods  **/	
	public WebDriver getDriver() {
		return this.driver;
	}
	
	
}
