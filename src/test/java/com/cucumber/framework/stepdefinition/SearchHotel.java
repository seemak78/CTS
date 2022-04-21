package com.cucumber.framework.stepdefinition;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.cucumber.framework.configreader.PropertyFileReader;
import com.cucumber.framework.helper.Button.ButtonHelper;
import com.cucumber.framework.helper.DatabaseHelper.Searchreport;
import com.cucumber.framework.helper.DatabaseHelper.SqlLiteHelper;
import com.cucumber.framework.helper.Generic.GenericHelper;
import com.cucumber.framework.helper.Logger.LoggerHelper;
import com.cucumber.framework.helper.Navigation.NavigationHelper;
import com.cucumber.framework.helper.PageObject.homepage.HotelPage;
import com.cucumber.framework.helper.PageObject.homepage.LoginPage;
import com.cucumber.framework.helper.Wait.WaitHelper;
import com.cucumber.framework.interfaces.IconfigReader;
import com.cucumber.framework.settings.ObjectRepo;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

/**
 * @author Seema
 *
 *
 */
public class SearchHotel {
	
	private LoginPage lPage;
	private HotelPage hPage;
	private final Logger log = LoggerHelper.getLogger(this.getClass());
	private IconfigReader reader = new PropertyFileReader();
	private SoftAssert softAssert = new SoftAssert();
	private Searchreport  singleHotelSearch = new Searchreport();
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
	private DateTimeFormatter dtf_time = DateTimeFormatter.ofPattern("HH");
	private LocalDateTime now = LocalDateTime.now(); 
	private LocalDateTime dtExecutionStart = null;
	private LocalDateTime dtExecutionEnd = null;
	private GenericHelper genHelp;
	   
	@Given("^: I am at the login home page$")
	public void i_am_at_the_login_home_page() throws Throwable {
		ObjectRepo.driver.get(ObjectRepo.reader.getWebsite());
		lPage = new LoginPage(ObjectRepo.driver);
		ObjectRepo.data.put("LoginPage", hPage);
	}

	@When("^: I enter \"([^\"]*)\" and \"([^\"]*)\" to login$")
	public void i_enter_username_and_password_to_login(String uname, String pwd){
    	lPage.acceptCookies();
		lPage.login(uname, pwd);
	}
	
	@And("^: I am logged in with message \"([^\"]*)\" succesfully")
	public void i_am_logged_in_successfully(String msg){
		hPage = new HotelPage(ObjectRepo.driver);
		if (hPage.landingPageMessages.size()>0 && hPage.landingPageMessage.getText().equals(msg))
				log.info("Logged in succesfully");
		else
			Assert.fail("Logged in failed");
	}
	
	@And("^: I am not logged in with message \"([^\"]*)\" succesfully")
	public void i_am_not_logged_in_successfully(String msg){
		if (lPage.loginErrorMessage.getText().contains(msg))
			log.info("Not Logged in ");
		else
			Assert.fail("Logged in failed:");
	}

	
	@And("^: I search in \"([^\"]*)\" to after (\\d+) from today for (\\d+) days to find hotel$")
	public void i_search_in_to_after_from_today_for_days_to_find_hotel(String searchKey, int addDaysFuturedate, int durOfStayinDays) throws Throwable {
		hPage.hotelTab.click();
		genHelp = new GenericHelper(hPage.getDriver());
		dtExecutionStart = LocalDateTime.now();
		singleHotelSearch.setDateTime(now.format(dtf));
		singleHotelSearch.setTime(Integer.parseInt(now.format(dtf_time)));
		singleHotelSearch.setPlace(searchKey);

		genHelp.jQueryAutoCompleteDropdown(hPage.location,searchKey, 10,1000);
		String strDate=genHelp.dateCalculator(0,0, addDaysFuturedate);
		singleHotelSearch.setStartDate(strDate);
		genHelp.populateReadonlyDate(hPage.checkInDate, strDate);
		strDate=genHelp.dateCalculator(0,0, (addDaysFuturedate+durOfStayinDays));
		singleHotelSearch.setEndDate(strDate);
		genHelp.populateReadonlyDate(hPage.checkOutDate, strDate);

		ButtonHelper btnHelper = new ButtonHelper(this.hPage.getDriver());
		btnHelper.click(By.id("searchBtnHome"));
		Thread.sleep(100);
		singleHotelSearch.setExecutionStart(dtExecutionStart.toString());
		WaitHelper wait = new WaitHelper(hPage.getDriver(), reader);		
		wait.untilJqueryIsDone(80);
		
	}
	
	@When("^: Find number of records and lowest priced hotel$")
	public void find_number_of_records_and_lowest_priced_hotel() throws Throwable {
		NavigationHelper navHelp = new NavigationHelper(hPage.getDriver());
		String strRec;
		String lowestPrice=null;
		if (hPage.hotelResults.size() > 0)
		{	

				strRec = hPage.numOfHotels.getText();
				

				lowestPrice = hPage.lowestPrice.getText();
				System.out.println("Total Results found: "+strRec+": Lowest Price:"+lowestPrice);
		} else
		{
			strRec="0";
			//isPresent =  hPage.getDriver().findElements(By.cssSelector("div[class='error_page']")).size() > 0;
			genHelp.takeScreenShot(navHelp.getParamFromCurrentURL("correlationId"));			
			if (hPage.errorList.size()>0 )
			{	
				softAssert.assertTrue(false, "Hotel error");
			}
			else
				System.out.println("Result section not available");
		}
		singleHotelSearch.setNumRecords(Integer.parseInt(strRec));
		dtExecutionEnd = LocalDateTime.now();
		singleHotelSearch.setExecutionEnd(dtExecutionEnd.toString());
		singleHotelSearch.setDiffExecutionTime();
		singleHotelSearch.setLowestPrice(lowestPrice);
		singleHotelSearch.setModule(hPage.getModule());
		singleHotelSearch.setCorelationId(navHelp.getParamFromCurrentURL("correlationId"));
		SqlLiteHelper.saveRecordsSearchResultsTable(singleHotelSearch);
		SqlLiteHelper.createSearchReport(genHelp.dateCalculator(0, 0, 0, "dd-MMM-yyyy"));
	}

	@Given("^: Generate report for all my searches$")
	public void generate_report_for_all_my_searches() throws Throwable {
		//hPage = new HotelPage(ObjectRepo.driver);
		genHelp = new GenericHelper(ObjectRepo.driver);
		SqlLiteHelper.createSearchReport(genHelp.dateCalculator(0, 0, 0, "dd-MMM-yyyy"));
	}

	public void setDiffExecutionTime()
	{
		Duration duration = Duration.between(dtExecutionStart, dtExecutionEnd);
		BigDecimal d = new BigDecimal(duration.toSeconds());
		singleHotelSearch.setExecTime(d);
	}
}