/**
 * Seema 
 *
 */
package com.cucumber.framework.configreader;

import java.util.Properties;

import org.apache.log4j.Level;

import com.cucumber.framework.configuration.browser.BrowserType;
import com.cucumber.framework.interfaces.IconfigReader;
import com.cucumber.framework.utility.ResourceHelper;


/**
 * @author Seema
 *
 */
public class PropertyFileReader implements IconfigReader {
	
	private Properties prop = null;

	public PropertyFileReader() {
		prop = new Properties();
		
		try {
			prop.load(ResourceHelper
					.getResourcePathInputStream("configfile/config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Java 8 comptiable
	 * public PropertyFileReader(String fileName) {

		prop = new Properties();
		try {
			prop.load(ResourceHelper
					.getResourcePathInputStream("configfile/"
							+ fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public String getHost() 
	{
		return prop.getProperty("mail.smtp.host");
	}

	public String getSSLEnabled() 
	{
		return prop.getProperty("mail.smtp.ssl.enable");
	}


	public String getSmtpAuth() 
	{
		return prop.getProperty("mail.smtp.auth");
	}

	public String getSmtpPort() 
	{
		return prop.getProperty("mail.smtp.port");
	}
	public String getEmail() 
	{
		return prop.getProperty("email");
	}

	public String getEmailPassword() 
	{
		return prop.getProperty("email_pwd");
	}

	public String getUserName() {
		return prop.getProperty("Username");
	}

	public String getPassword() {
		return prop.getProperty("Password");
	}

	public String getWebsite() {
		return prop.getProperty("Website");
	}

	public int getPageLoadTimeOut() {
		return Integer.parseInt(prop.getProperty("PageLoadTimeOut"));
	}

	public int getImplicitWait() {
		return Integer.parseInt(prop.getProperty("ImplcitWait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(prop.getProperty("ExplicitWait"));
	}
	
	public String getDbType() {
		return prop.getProperty("DataBase.Type");
	}

	public String getDbConnStr() {
		return prop.getProperty("DtaBase.ConnectionStr");
	}

	public BrowserType getBrowser() {
		return BrowserType.valueOf(prop.getProperty("Browser"));
	}
	
	public Level getLoggerLevel() {
		
		switch (prop.getProperty("Logger.Level")) {
		
		case "DEBUG":
			return Level.DEBUG;
		case "INFO":
			return Level.INFO;
		case "WARN":
			return Level.WARN;
		case "ERROR":
			return Level.ERROR;
		case "FATAL":
			return Level.FATAL;
		}
		return Level.ALL;
	}

	public String getSendSearchEmailAfterTime() {
		return prop.getProperty("SendSearchEmailAfter");
	}

	public String getSendEmailTo() {
		return prop.getProperty("send_email_to");
	}

	@Override
	public String getSQLiteDB() {
		// TODO Auto-generated method stub
		return prop.getProperty("sqllite.db.location");
	}

	@Override
	public String getReportLocation() {
		// TODO Auto-generated method stub
		return prop.getProperty("report.location");
	}
}
