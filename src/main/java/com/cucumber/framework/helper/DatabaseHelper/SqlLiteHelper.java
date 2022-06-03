package com.cucumber.framework.helper.DatabaseHelper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;

import com.cucumber.framework.configreader.PropertyFileReader;
import com.cucumber.framework.helper.Logger.LoggerHelper;
import com.cucumber.framework.helper.Wait.Testing;
import com.cucumber.framework.helper.email.SendMail;

public class SqlLiteHelper {
    private static Connection conn = null;
    private static PropertyFileReader reader = new PropertyFileReader();
    private static String dbName = reader.getSQLiteDB();  //"target/classes/exceldata/CTS_SearchResults.db";
    private static Statement stmt = null;
	private static final Logger log = LoggerHelper.getLogger(SqlLiteHelper.class);

	 public static void saveRecordsSearchResultsTable(Searchreport obj)
	 {
		creatSearchReportTableIfNotExists();
		String tableName = obj.getClass().getSimpleName();
		String placeholders = "'"+obj.getDateTime()+"',"+obj.getTime()+",'"+obj.getPlace()+"','"+obj.getStartDate()+"','"+
						obj.getEndDate()+"', "+obj.getNumRecords()+",'"+obj.getLowestPrice()+"',"+
						obj.getExecTime()+",'"+obj.getCorelationId()+"','"+obj.getModule()+"'";
		String sql = "INSERT INTO " + tableName + "(DATETIME, TIME, PLACE, STARTDATE, ENDDATE, "+
					"NUMRECORDS, LOWESTPRICE, EXECTIME, CORELATION_ID, MODULE) values "+
					"(" + placeholders + ")";
		try {
			createConnection(dbName);
			stmt.execute(sql);
		 	conn.close();
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL:"+sql);
			e.printStackTrace();
		}

	   }
	   
	public static List<Searchreport> fetchSearchReport(String param)
	{
		List<Searchreport> hotelSearchList = new ArrayList<>();

		createConnection(dbName);
		String sql = "select * from searchreport where DATETIME like '"+param+"%'";
		try {
			conn.prepareStatement(sql);
			Searchreport sr = new Searchreport();
			ResultSet rs  = stmt.executeQuery(sql);
			while (rs.next()) {
				sr.setDateTime(rs.getString("datetime"));
				sr.setDateTime(rs.getString("time"));
				sr.setModule(rs.getString("module"));
				sr.setPlace(rs.getString("place"));
				sr.setStartDate(rs.getString("startdate"));
				sr.setEndDate(rs.getString("enddate"));
				sr.setNumRecords(rs.getInt("numrecords"));
				sr.setLowestPrice(rs.getString("lowestprice"));
				sr.setExecTime(rs.getBigDecimal("EXECTIME"));
				sr.setCorelationId(rs.getString("corelation_id"));
				hotelSearchList.add(sr);
				System.out.println("str:"+sr.getPlace());
			}
		  	stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hotelSearchList;
	}
	   

	   public static void createConnection(String dbName)
	   {
	       try {
	    	   Class.forName("org.sqlite.JDBC");
		       conn = DriverManager.getConnection("jdbc:sqlite:"+dbName);
		       stmt = conn.createStatement();
	       } catch (ClassNotFoundException e) {
	    	   System.out.println("openDatabase: "+Testing.class.getName()+":"+e.getMessage());
	       } catch (SQLException e) {
			// TODO Auto-generated catch block
	    	   System.out.println("openDatabase: SQException :"+Testing.class.getName()+":"+e.getMessage());
	       }
	   }
	   
		private static void creatSearchReportTableIfNotExists()
		{
	   	 	try {
			String sql = "CREATE TABLE IF NOT EXISTS searchreport " +
					"(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
					" DATETIME TEXT NOT NULL, " + 
					" TIME INT NOT NULL, " + 
					" MODULE TEXT, " + 
					" PLACE TEXT NOT NULL, " + 
					" STARTDATE TEXT NOT NULL, " + 
					" ENDDATE TEXT, " + 
					" NUMRECORDS INT, " + 
					" LOWESTPRICE REAL, " + 
					" EXECTIME TEXT,"  + 
					" CORELATION_ID TEXT)"; 
				createConnection(dbName);
				stmt.execute(sql);
		   	 	conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	    // Function to convert camel case
	    // string to snake case string
	    public static String camelToSnakeCase(String str)
	    {
	 
	        // Empty String
	        String result = "";
	 
	        // Append first character(in lower case)
	        // to result string
	        char c = str.charAt(0);
	        result = result + Character.toLowerCase(c);
	 
	        // Traverse the string from
	        // ist index to last index
	        for (int i = 1; i < str.length(); i++) {
	 
	            char ch = str.charAt(i);
	 
	            // Check if the character is upper case
	            // then append '_' and such character
	            // (in lower case) to result string
	            if (Character.isUpperCase(ch)) {
	                result = result + '_';
	                result
	                    = result
	                      + Character.toLowerCase(ch);
	            }
	 
	            // If the character is lower case then
	            // add such character into result string
	            else {
	                result = result + ch;
	            }
	        }
	 
	        // return the result
	        return result;
	    }
	    
		public static void createSearchReport(String repDate) {		
			ReportGenerator reportGenerator = new ReportGenerator();
			String sql = "select * from searchreport where DATETIME like '"+repDate+"%' ORDER BY MODULE, PLACE, datetime ASC ";
			String outputFileName = reader.getReportLocation()+"/Searchreport_query"+repDate.replace("-","")+".xls";
			try {
				reportGenerator.createDocumentFromQuery(new FileOutputStream(outputFileName), "exceldata/searchresult_query_template.xls", sql);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				log.error("Issue in createSearchReport:"+e.getMessage());
			}
			dispatchEmail(repDate,outputFileName);
		}
		
		public static void dispatchEmail(String repDate, String outputFileName)
		{
			PropertyFileReader reader = new PropertyFileReader();
			String subject="[Automation Robot] Search Report for various module on "+repDate;
			String body="Please refer attached report to understand state of searches during the day.";
	    	try {
				SendMail.send("report", subject, body, outputFileName);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				log.error("Issue in Sending Email createSearchReport:"+e.getMessage());
			}
		}

		public static void createBug(String module, String coorelationid, String location, String dateTime, String outputFileName)
		{
			PropertyFileReader reader = new PropertyFileReader();
			String subject = "[Automation Bug] in "+module+" module no inventory is available";
			String body = "While searching for location:"+location+" robot did not find invetory";
						if (coorelationid!=null && coorelationid!="") 
						{
							body= body + "<br><br>Please refer correlation id for further debugging:"+coorelationid;
						}
	    	try {
				SendMail.send("bug", subject, body, outputFileName);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				log.error("Issue in Sending Email to create Bug:"+e.getMessage());
			}
		}


}
