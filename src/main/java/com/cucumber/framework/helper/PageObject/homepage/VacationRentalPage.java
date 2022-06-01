package com.cucumber.framework.helper.PageObject.homepage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.cucumber.framework.helper.PageObject.PageBase;

public class VacationRentalPage extends PageBase {
	
	private WebDriver driver;

	public VacationRentalPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	
	/** Web Elements */
	public String module="Vacation";

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@FindBy(how=How.ID,using="ippkon1")
	public WebElement VacationTab;
	
	@FindBy(how=How.ID,using="vacation_rentals_to_date")
	public WebElement fromDate;
	
	//within date
	@FindBy(how=How.XPATH,using="//*[@id=\"vacationRentalSearchForm\"]/div/div/div[3]/div/div/div/span")
	public WebElement toDate;
	
	
	@FindBy(how=How.XPATH,using="//*[@id=\"vacationRentalSearchForm\"]/div/div/div[1]/div/div[1]")
	public WebElement location;
	
	//Hover to United States +
    @FindBy(how=How.XPATH,using="//*[@id=\"area\"]/div[2]/div[5]/div[1]/a/text()")
	 public WebElement ele;
    
    @FindBy(how=How.XPATH,using="//*[@id=\"area\"]/div[2]/div[5]/div[1]/a/i")
	 public WebElement eleUS;

	//Creating object of an Actions class
	// public Actions actions = new Actions(driver);
	
	//Radio Button(california)
	@FindBy(how=How.XPATH,using="//*[@id=\"area\"]/div[2]/div[5]/div[2]/div[1]/div/label/div")
	public WebElement alaska;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"area\"]/div[2]/div[5]/div[2]/div[9]/div/label/div")
	public WebElement california;
	
	
	
	//close sign for location
	@FindBy(how=How.XPATH,using="//*[@id='vacationRentalSearchForm']/div/div/div[1]/div/div[2]/div/div[1]/span")
	public WebElement closebutton;

	

	@FindBy(how=How.ID,using="searchBtnHomeVR")
	public WebElement btnSearch;
	
		
	//@FindBy(how=How.XPATH,using="//*[@id=\"result\"]/div/div/div/div[1]/div[1]/div/div")
	//@FindBy(how=How.XPATH,using="//*[@id='result']/div/div/div/div[1]/div[1]/div/div/span")
	@FindBy(how=How.XPATH,using="//*[@id='result']/div/div/div/div[1]/div[1]/div/div/span")
	public WebElement numOfVacation;
	
	//@FindBy(how=How.XPATH,using="//*[@id=\"result\"]/div/div/div/div[2]/div[3]/ul/li[1]/div/div[1]/div[3]/div/p/text()")
	@FindBy(how=How.XPATH,using="//*[@id='result']/div/div/div/div[2]/div[2]/ul/li[1]/div/div/div[3]/div/div[3]/span[2]")

	public WebElement lowestPrice;	
	
	//@FindBy(how=How.CSS,using="li[class='activity_main_li_box']")
	//@FindBy(how=How.XPATH,using="/html/body/div[3]/div/section/div/div/div/div[1]/div[1]/div/div")
	@FindBy(how=How.XPATH,using="//*[@id=\"result\"]/div/div/div/div[2]/div[2]/ul/li[1]/div")
	public List<WebElement> resultBoxes;
	
	
	@FindBy(how=How.ID, using="selectSortingVacation")
	public WebElement sortBy;
	
	/** Public Methods  **/	
	public WebDriver getDriver() {
		return this.driver;
	}
	
	
}




