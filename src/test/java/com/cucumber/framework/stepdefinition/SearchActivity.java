package com.cucumber.framework.stepdefinition;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.cucumber.framework.configreader.PropertyFileReader;
import com.cucumber.framework.helper.DatabaseHelper.Searchreport;
import com.cucumber.framework.helper.DatabaseHelper.SqlLiteHelper;
import com.cucumber.framework.helper.Generic.GenericHelper;
import com.cucumber.framework.helper.Logger.LoggerHelper;
import com.cucumber.framework.helper.Navigation.NavigationHelper;
import com.cucumber.framework.helper.PageObject.homepage.ActivityPage;
import com.cucumber.framework.helper.PageObject.homepage.CarPage;
import com.cucumber.framework.helper.Wait.WaitHelper;
import com.cucumber.framework.interfaces.IconfigReader;
import com.cucumber.framework.settings.ObjectRepo;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;


public class SearchActivity {
	
	private ActivityPage aPage=new ActivityPage(ObjectRepo.driver);
	private final Logger log = LoggerHelper.getLogger(this.getClass());
	private IconfigReader reader = new PropertyFileReader();
	private SoftAssert softAssert = new SoftAssert();
	private Searchreport  singleactivitySearch = new Searchreport();
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
	private DateTimeFormatter dtf_time = DateTimeFormatter.ofPattern("HH");
	private LocalDateTime now = LocalDateTime.now(); 
	private LocalDateTime dtExecutionStart = null;
	private LocalDateTime dtExecutionEnd = null;
	private GenericHelper genHelp;
	
	
	@When("^: I search in \"([^\"]*)\" to after (\\d+) from today for (\\d+) days to find activity$")
	public void i_search_in_to_after_from_today_for_days_to_find_activity(String searchKey, int addDaysFuturedate, int durOfStayinDays) throws Throwable {
		genHelp = new GenericHelper(aPage.getDriver());
		dtExecutionStart = LocalDateTime.now();
		singleactivitySearch.setDateTime(now.format(dtf));
		singleactivitySearch.setTime(Integer.parseInt(now.format(dtf_time)));
		singleactivitySearch.setPlace(searchKey);
	
		aPage.activityTab.click();
		genHelp.jQueryAutoCompleteDropdown(aPage.location,searchKey, 10,1000);
		String strDate=genHelp.dateCalculator(0,0, addDaysFuturedate);
		singleactivitySearch.setStartDate(strDate);
		strDate=genHelp.dateCalculator(0,0, (addDaysFuturedate+durOfStayinDays));
		singleactivitySearch.setEndDate(strDate);
		singleactivitySearch.setModule(aPage.getModule());
		aPage.btnSearch.click();
		Thread.sleep(100);
		singleactivitySearch.setExecutionStart(dtExecutionStart.toString());
		WaitHelper wait = new WaitHelper(aPage.getDriver(), reader);		
		wait.untilJqueryIsDone(120);
	}

	
	
	@When("^: Find number of activities$")
	public void find_number_of_activities() throws Throwable {
		Boolean isPresent = aPage.resultBoxes.size()>0; 
		String strRec;		
		String lowestPrice = null;
		if (isPresent)
		{	
			System.out.println("Total records:"+aPage.numOfActivity.getText());
			strRec = extractInt(aPage.numOfActivity.getText());
			if (Integer.parseInt(strRec)>0)
			{
				lowestPrice= "0"; //aPage.lowestPrice.getText();
				log.info("Total activities found: "+strRec+"- Lowest Price:"+lowestPrice);
		
				System.out.println("Total activities found: "+strRec+"- Lowest Price:"+lowestPrice);
			} else
			{
				String screenshot = genHelp.takeScreenShot(singleactivitySearch.getDateTime());
				strRec="0";
				SqlLiteHelper.createBug(aPage.getModule(), "", singleactivitySearch.getPlace(), singleactivitySearch.getDateTime(), screenshot);
				log.info("No Activities displayed");
				System.out.println("No Activities displayed");
			}
		} else
		{
			strRec="0";
			isPresent = aPage.getDriver().findElements(By.cssSelector("div[class='error_car_in']")).size() > 0;
			String screenshot =genHelp.takeScreenShot("Car_search_failed_"+LocalDateTime.now().toString().replace("/","").replace(".", "").replace(":",""));
			SqlLiteHelper.createBug(aPage.getModule(), "", singleactivitySearch.getPlace(), singleactivitySearch.getDateTime(), screenshot);
			genHelp.takeScreenShot("Activity_search_failed_"+LocalDateTime.now().toString().replace("/","").replace(".", "").replace(":",""));
			if (isPresent)
			{	
				softAssert.assertTrue(false, "Activity error");
				log.error("Activity Error");
			}
			else
				log.info("Activity result section not available");
				System.out.println("Activity result section not available");
		}
		singleactivitySearch.setNumRecords(Integer.parseInt(strRec));
		singleactivitySearch.setLowestPrice(lowestPrice);
		dtExecutionEnd = LocalDateTime.now();
		singleactivitySearch.setExecutionEnd(dtExecutionEnd.toString());
		singleactivitySearch.setDiffExecutionTime();
		singleactivitySearch.setCorelationId("NA");
		SqlLiteHelper.saveRecordsSearchResultsTable(singleactivitySearch);
		//createSearchReport();
	}
	
	static String extractInt(String str)
    {
        // Replacing every non-digit number
        // with a space(" ")
        str = str.replaceAll("[^\\d]", " ");
  
        // Remove extra spaces from the beginning
        // and the ending of the string
        str = str.trim();
  
        // Replace all the consecutive white
        // spaces with a single space
        str = str.replaceAll(" +", " ");
  
        if (str.equals(""))
            return "-1";
  
        return str;
    }
	
	@When("^: I am clicking on show$")
	public void i_am_clicking_on_show() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    aPage.show.click();
	    aPage.booK.click();
	    Thread.sleep(2000);
	}
}
