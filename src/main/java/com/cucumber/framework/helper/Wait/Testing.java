package com.cucumber.framework.helper.Wait;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.cucumber.framework.helper.DatabaseHelper.ReportGenerator;


public class Testing {

   public static void main( String args[] ) {
	   //SqlLiteHelper sqlLiteHelper = new SqlLiteHelper();
	   //List<Searchreport> srchRpt = sqlLiteHelper.fetchSearchReport("16-Mar-2022");
		ReportGenerator reportGenerator = new ReportGenerator();
		String repDate = "22-Mar-2022";
		String sql = "select * from searchreport where DATETIME like '"+repDate+"%'";
		String outputFileName = "target/Searchreport_query"+repDate.replace("-","")+".xls";
		try {
			reportGenerator.createDocumentFromQuery(new FileOutputStream(outputFileName), "exceldata/searchresult_query_template.xls", sql);
	//		reportGenerator.createDocument(new FileOutputStream("target/Searchreport_pojo_"+"16/03/2022".replace("/","")+".xls"), "SearchResult_POJO_template", srchRpt);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Issue in createSearchReport:"+e.getMessage());
		}

/*		PropertyFileReader reader = new PropertyFileReader();
		LocalTime currentTime = LocalDateTime.now().toLocalTime();
		LocalTime timeToProcessEmail = LocalTime.parse(reader.getSendSearchEmailAfterTime());
	    if (currentTime.compareTo(timeToProcessEmail)>=0) //TimeToProcessEmail is greate than or equal to currentTime
	    {
	    	try {
				SendMail.send("Search Report for "+repDate, "Please find attached report for different searches during the day.", outputFileName);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				System.out.println("Issue in Sending Email createSearchReport:"+e.getMessage());
			}
	    }*/
   }
   
}