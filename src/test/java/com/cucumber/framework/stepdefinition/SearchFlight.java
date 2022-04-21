package com.cucumber.framework.stepdefinition;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import com.cucumber.framework.configreader.PropertyFileReader;
import com.cucumber.framework.helper.DatabaseHelper.Searchreport;
import com.cucumber.framework.helper.DatabaseHelper.SqlLiteHelper;
import com.cucumber.framework.helper.Generic.GenericHelper;
import com.cucumber.framework.helper.Logger.LoggerHelper;
import com.cucumber.framework.helper.PageObject.homepage.FlightPage;
import com.cucumber.framework.helper.Wait.WaitHelper;
import com.cucumber.framework.interfaces.IconfigReader;
import com.cucumber.framework.settings.ObjectRepo;

import cucumber.api.java.en.When;

/**
 * @author Seema
 *
 *
 */

public class SearchFlight {
	
	private FlightPage fPage=new FlightPage(ObjectRepo.driver);
	private final Logger log = LoggerHelper.getLogger(this.getClass());
	private IconfigReader reader = new PropertyFileReader();
	private SoftAssert softAssert = new SoftAssert();
	private Searchreport  singleFlightSearch = new Searchreport();
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
	private DateTimeFormatter dtf_time = DateTimeFormatter.ofPattern("HH");
	private LocalDateTime now = LocalDateTime.now(); 
	private LocalDateTime dtExecutionStart = null;
	private LocalDateTime dtExecutionEnd = null;
	private GenericHelper genHelp;
	
	@When("^: I search from \"([^\"]*)\" to \"([^\"]*)\" after (\\d+) days from today for (\\d+) days to find flight$")
	public void i_search_from_to_after_days_from_today_for_days_to_find_flight(String fromCity, String toCity, int addDaysFuturedate, int returnDaysFromFutureDate) throws Throwable {
		genHelp = new GenericHelper(fPage.getDriver());
		dtExecutionStart = LocalDateTime.now();
		singleFlightSearch.setDateTime(now.format(dtf));
		singleFlightSearch.setTime(Integer.parseInt(now.format(dtf_time)));
		singleFlightSearch.setPlace(fromCity+" to "+toCity);

		fPage.flightTab.click();
		genHelp.jQueryAutoCompleteDropdown(fPage.fromCity,fromCity, 10,1000);
		genHelp.jQueryAutoCompleteDropdown(fPage.toCity,toCity, 10,1000);
		String strDate=genHelp.dateCalculator(0,0, addDaysFuturedate);
		singleFlightSearch.setStartDate(strDate);
		genHelp.populateReadonlyDate(fPage.departDate, strDate);
		strDate=genHelp.dateCalculator(0,0, (addDaysFuturedate+returnDaysFromFutureDate));
		singleFlightSearch.setEndDate(strDate);
		singleFlightSearch.setModule(fPage.getModule());
		genHelp.populateReadonlyDate(fPage.arrivalDate, strDate);
		fPage.btnSearch.click();
		Thread.sleep(100);
		singleFlightSearch.setExecutionStart(dtExecutionStart.toString());
		WaitHelper wait = new WaitHelper(fPage.getDriver(), reader);		
		wait.loadingWait(fPage.getDriver(), fPage.visibleLoader, fPage.invisibleLoader);;

	}

	@When("^: Find number of flight records$")
	public void find_number_of_flight_records() throws Throwable {
		
		Boolean isPresent = fPage.resultBoxes.size()>0; //fPage.findElements(By.cssSelector("li[class='main_li_box']")).size() > 0;
		String strRec;
		
		String lowestPrice = null;
		if (isPresent)
		{	
			strRec = fPage.numOfFlights.getText();
			if (Integer.parseInt(strRec)>0)
			{
				lowestPrice= fPage.lowestPrice.getText();
				log.info("Total Flights found: "+strRec+"- Lowest Price:"+lowestPrice);
		
				System.out.println("Total Flights found: "+strRec+"- Lowest Price:"+lowestPrice);
			} else
			{
				strRec="0";
				log.info("No Flights displayed");
				System.out.println("No Flights displayed");
			}
		} else
		{
			strRec="0";
			isPresent = fPage.getDriver().findElements(By.cssSelector("div[class='error_flight']")).size() > 0;
			genHelp.takeScreenShot("Flight_search_failed_"+LocalDateTime.now().toString().replace("/","").replace(".", "").replace(":",""));
			if (isPresent)
			{	
				softAssert.assertTrue(false, "Flight error");
				log.error("Flight Error");
			}
			else
				log.info("Flight result section not available");
				System.out.println("Flight result section not available");
		}
		singleFlightSearch.setNumRecords(Integer.parseInt(strRec));
		singleFlightSearch.setLowestPrice(lowestPrice);
		dtExecutionEnd = LocalDateTime.now();
		singleFlightSearch.setExecutionEnd(dtExecutionEnd.toString());
		singleFlightSearch.setDiffExecutionTime();
		singleFlightSearch.setCorelationId("NA");
		SqlLiteHelper.saveRecordsSearchResultsTable(singleFlightSearch);
		//createSearchReport();
	}
}