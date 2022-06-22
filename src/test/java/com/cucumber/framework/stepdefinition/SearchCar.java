package com.cucumber.framework.stepdefinition;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.cucumber.framework.configreader.PropertyFileReader;
import com.cucumber.framework.helper.DatabaseHelper.Searchreport;
import com.cucumber.framework.helper.DatabaseHelper.SqlLiteHelper;
import com.cucumber.framework.helper.Generic.GenericHelper;
import com.cucumber.framework.helper.Logger.LoggerHelper;
import com.cucumber.framework.helper.Navigation.NavigationHelper;
import com.cucumber.framework.helper.PageObject.homepage.CarPage;
import com.cucumber.framework.helper.Wait.WaitHelper;
import com.cucumber.framework.interfaces.IconfigReader;
import com.cucumber.framework.settings.ObjectRepo;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

/**
 * @author Seema
 *
 *
 */

public class SearchCar {
	
	private CarPage cPage=new CarPage(ObjectRepo.driver);
	private final Logger log = LoggerHelper.getLogger(this.getClass());
	private IconfigReader reader = new PropertyFileReader();
	private SoftAssert softAssert = new SoftAssert();
	private Searchreport  singleCarSearch = new Searchreport();
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
	private DateTimeFormatter dtf_time = DateTimeFormatter.ofPattern("HH");
	private LocalDateTime now = LocalDateTime.now(); 
	private LocalDateTime dtExecutionStart = null;
	private LocalDateTime dtExecutionEnd = null;
	private GenericHelper genHelp;
	
	@When("^: I search from \"([^\"]*)\" to \"([^\"]*)\" after (\\d+) days from today for (\\d+) days to find Car$")
	public void i_search_from_to_after_days_from_today_for_days_to_find_Car(String fromCity, String toCity, int addDaysFuturedate, int returnDaysFromFutureDate) throws Throwable {
		genHelp = new GenericHelper(cPage.getDriver());
		dtExecutionStart = LocalDateTime.now();
		singleCarSearch.setDateTime(now.format(dtf));
		singleCarSearch.setTime(Integer.parseInt(now.format(dtf_time)));
		singleCarSearch.setPlace(fromCity+" to "+toCity);

		cPage.carTab.click();
		genHelp.jQueryAutoCompleteDropdown(cPage.pickupCity,fromCity, 10,2000);
		genHelp.jQueryAutoCompleteDropdown(cPage.dropoffCity,toCity, 10,2000);
		Thread.sleep(100);
		String strDate=genHelp.dateCalculator(0,0, addDaysFuturedate);
		singleCarSearch.setStartDate(strDate);
		genHelp.populateReadonlyDate(cPage.pickupDate, strDate, "12:00");
		strDate=genHelp.dateCalculator(0,0, (addDaysFuturedate+returnDaysFromFutureDate));
		singleCarSearch.setEndDate(strDate);
		singleCarSearch.setModule(cPage.getModule());
		genHelp.populateReadonlyDate(cPage.dropoffDate, strDate, "15:00");
		cPage.btnSearch.click();
	
		Thread.sleep(100);
		singleCarSearch.setExecutionStart(dtExecutionStart.toString());
		WaitHelper wait = new WaitHelper(cPage.getDriver(), reader);		
		wait.untilJqueryIsDone(120);

	}

	@When("^: Find number of cars for hire$")
	public void find_number_of_cars_for_hire() throws Throwable {
		Boolean isPresent = cPage.resultBoxes.size()>0; //cPage.getDriver().findElements(By.cssSelector("li[class='car_main_li_box']")).size() > 0;
		System.out.println("size:"+cPage.resultBoxes.size());
		String strRec;		
		String lowestPrice = null;
		if (isPresent)
		{	
			strRec = cPage.numOfCars.getText();
			System.out.println("StrRec:"+strRec);
			if (Integer.parseInt(strRec)>0)
			{
				lowestPrice= cPage.lowestPrice.getText();
				log.info("Total Cars found: "+strRec+"- Lowest Price:"+lowestPrice);
		
				System.out.println("Total Cars found: "+strRec+"- Lowest Price:"+lowestPrice);
			} else
			{
				String screenshot = genHelp.takeScreenShot(singleCarSearch.getDateTime());
				strRec="0";
				SqlLiteHelper.createBug(cPage.getModule(), "", singleCarSearch.getPlace(), singleCarSearch.getDateTime(), screenshot);
				log.info("No Cars displayed");
				System.out.println("No Cars displayed");
			}
		} else
		{
			strRec="0";
			isPresent = cPage.getDriver().findElements(By.cssSelector("div[class='error_car_in']")).size() > 0;
			String screenshot =genHelp.takeScreenShot("Car_search_failed_"+LocalDateTime.now().toString().replace("/","").replace(".", "").replace(":",""));
			SqlLiteHelper.createBug(cPage.getModule(), "", singleCarSearch.getPlace(), singleCarSearch.getDateTime(), screenshot);
			genHelp.takeScreenShot("Car_search_failed_"+LocalDateTime.now().toString().replace("/","").replace(".", "").replace(":",""));
			if (isPresent)
			{	
				softAssert.assertTrue(false, "Car error");
				log.error("Car Error");
			}
			else
				log.info("Car result section not available");
				System.out.println("Car result section not available");
		}
		singleCarSearch.setNumRecords(Integer.parseInt(strRec));
		singleCarSearch.setLowestPrice(lowestPrice);
		dtExecutionEnd = LocalDateTime.now();
		singleCarSearch.setExecutionEnd(dtExecutionEnd.toString());
		singleCarSearch.setDiffExecutionTime();
		singleCarSearch.setCorelationId("NA");
		SqlLiteHelper.saveRecordsSearchResultsTable(singleCarSearch);
		//createSearchReport();
	}
	
	@And("^: I am clicking on book$")
		public   void I_am_clicking_on_book() throws Throwable {
		NavigationHelper navHelp = new NavigationHelper(cPage.getDriver());
		String openDetailPageURL= cPage.bookNow.getAttribute("href");
		cPage.getDriver().get(openDetailPageURL);
		Thread.sleep(1000);
		List<WebElement> allInputElements = cPage.getDriver().findElements(By.cssSelector(".booknowCarbtn")); 
		Integer intCar=  allInputElements.size();  
		System.out.println("Anchor tags::"+intCar+":"+cPage.bookNow);
		
		Select title=new Select(cPage.title);
		//title.selectByVisibleText("Mr");
		title.selectByValue("Mr");
		cPage.contactno.sendKeys("1234567890");
		Select country=new Select(cPage.country);
		country.selectByVisibleText("United States");
		Thread.sleep(2000);
		
		//boolean isEnabled = cPage.check.isEnabled();
		// performing click operation if element is enabled
		//if (isEnabled == true) {
			//cPage.check.click();
		//}
		//Thread.sleep(2000);
		//cPage.bookN.click();	
		
		if (intCar <= 0)
		{			
			String screenshot=genHelp.takeScreenShot(navHelp.getParamFromCurrentURL("CAR"));
			SqlLiteHelper.createBug(cPage.getModule(),"", singleCarSearch.getPlace(), singleCarSearch.getDateTime(), screenshot);		
		} else
		{
			
			cPage.getDriver().get(allInputElements.get(0).getAttribute("href"));
			Thread.sleep(200);
		}
	
		
	}
}