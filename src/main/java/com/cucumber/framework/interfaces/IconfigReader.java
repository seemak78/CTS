/**
 * rsr 
 *
 *Aug 6, 2016
 */
package com.cucumber.framework.interfaces;

import com.cucumber.framework.configuration.browser.BrowserType;




/**
 * @author Seema
 *
 * Aug 6, 2016
 */
public interface IconfigReader {
	public String getUserName();
	public String getPassword();
	public String getWebsite();
	public int getPageLoadTimeOut();
	public int getImplicitWait();
	public int getExplicitWait();
	public BrowserType getBrowser();
	public String getHost();
	public String getSSLEnabled();
	public String getSmtpAuth();
	public String getSmtpPort();
	public String getEmail();
	public String getEmailPassword();
	public String getSendSearchEmailAfterTime();
	public String getSendEmailTo();
	public String getSQLiteDB();
	public String getReportLocation();
}
