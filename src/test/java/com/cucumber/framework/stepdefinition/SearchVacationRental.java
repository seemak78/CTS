package com.cucumber.framework.stepdefinition;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.cucumber.framework.configreader.PropertyFileReader;
import com.cucumber.framework.helper.DatabaseHelper.Searchreport;
import com.cucumber.framework.helper.DatabaseHelper.SqlLiteHelper;
import com.cucumber.framework.helper.Generic.GenericHelper;
import com.cucumber.framework.helper.Logger.LoggerHelper;
import com.cucumber.framework.helper.PageObject.homepage.ActivityPage;
import com.cucumber.framework.helper.PageObject.homepage.CarPage;
import com.cucumber.framework.helper.PageObject.homepage.VacationRentalPage;
import com.cucumber.framework.helper.Wait.WaitHelper;
import com.cucumber.framework.interfaces.IconfigReader;
import com.cucumber.framework.settings.ObjectRepo;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;


public class SearchVacationRental {
	
	private VacationRentalPage vPage=new VacationRentalPage(ObjectRepo.driver);
	private final Logger log = LoggerHelper.getLogger(this.getClass());
	private IconfigReader reader = new PropertyFileReader();
	private SoftAssert softAssert = new SoftAssert();
	private Searchreport  singlevacationSearch = new Searchreport();
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
	private DateTimeFormatter dtf_time = DateTimeFormatter.ofPattern("HH");
	private LocalDateTime now = LocalDateTime.now(); 
	private LocalDateTime dtExecutionStart = null;
	private LocalDateTime dtExecutionEnd = null;
	private GenericHelper genHelp;
	
	
	
	@When("^: I search in \"([^\"]*)\" to after (\\d+) from today within (\\d+) days to VR$")
	public void i_search_in_to_after_from_today_within_days_to_VR(String searchKey, int addDaysFuturedate, int durOfStayinDays) throws Throwable {
	//public void i_search_in_to_after_from_today_within_days_to_VR(String searchKey, int addDaysFuturedate, int durOfStayinDays) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	    genHelp = new GenericHelper(vPage.getDriver());
		dtExecutionStart = LocalDateTime.now();
		singlevacationSearch.setDateTime(now.format(dtf));
		singlevacationSearch.setTime(Integer.parseInt(now.format(dtf_time)));
		singlevacationSearch.setPlace(searchKey);
	
		vPage.VacationTab.click();
//		genHelp.jQueryAutoCompleteDropdown(vPage.location,searchKey, 10,1000);
		vPage.location.click();
		//vPage.actions.moveToElement(ele);
		//vPage.actions.click().build().perform();;
		vPage.eleUS.click();
		if (searchKey=="ALASKA")
			vPage.alaska.click();
		else if (searchKey=="CALIFORNIA")
			vPage.california.click();
		
		vPage.closebutton.click();
		
		String strDate=genHelp.dateCalculator(0,0, addDaysFuturedate);
		singlevacationSearch.setStartDate(strDate);
		strDate=genHelp.dateCalculator(0,0, (addDaysFuturedate+durOfStayinDays));
		singlevacationSearch.setEndDate(strDate);
		singlevacationSearch.setModule(vPage.getModule());
		vPage.btnSearch.click();
		Thread.sleep(100);
		singlevacationSearch.setExecutionStart(dtExecutionStart.toString());
		WaitHelper wait = new WaitHelper(vPage.getDriver(), reader);		
		wait.untilJqueryIsDone(120);
	}
	

@When("^: Find number of VR$")
public void find_number_of_VR() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
   // throw new PendingException();
    Boolean isPresent = vPage.resultBoxes.size()>0; 
    		String strRec;		
		String lowestPrice = null;
		if (isPresent)
		{	
			 Select price = new Select(vPage.sortBy);
			 price.selectByIndex(2);
			 Thread.sleep(100);
			System.out.println("Total records:"+vPage.numOfVacation.getText());
			strRec = extractInt(vPage.numOfVacation.getText());
			if (Integer.parseInt(strRec)>0)
			{
				lowestPrice= "0"; //aPage.lowestPrice.getText();
				log.info("Total vacations found: "+strRec+"- Lowest Price:"+lowestPrice);
		
				System.out.println("Total vacations found: "+strRec+"- Lowest Price:"+lowestPrice);
			} else
			{
				strRec="0";
				log.info("No vacations displayed");
				System.out.println("No vacations displayed");
			}
		} else
		{
			strRec="0";
			isPresent = vPage.getDriver().findElements(By.cssSelector("div[class='error_car_in']")).size() > 0;
			genHelp.takeScreenShot("Activity_search_failed_"+LocalDateTime.now().toString().replace("/","").replace(".", "").replace(":",""));
			if (isPresent)
			{	
				softAssert.assertTrue(false, "Activity error");
				log.error("vacation Error");
			}
			else
				log.info("vacation result section not available");
				System.out.println("vacation result section not available");
		}
		singlevacationSearch.setNumRecords(Integer.parseInt(strRec));
		singlevacationSearch.setLowestPrice(lowestPrice);
		dtExecutionEnd = LocalDateTime.now();
		singlevacationSearch.setExecutionEnd(dtExecutionEnd.toString());
		singlevacationSearch.setDiffExecutionTime();
		singlevacationSearch.setCorelationId("NA");
		SqlLiteHelper.saveRecordsSearchResultsTable(singlevacationSearch);
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
  
}
