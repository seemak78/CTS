package com.cucumber.framework.helper.DatabaseHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.jxls.common.Context;
import org.jxls.jdbc.JdbcHelper;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cucumber.framework.configreader.PropertyFileReader;
import com.cucumber.framework.utility.ResourceHelper;

public class ReportGenerator {

	private static final Logger logger = LoggerFactory.getLogger(ReportGenerator.class);

	public void createDocument(OutputStream outStream, String templateName, List<Searchreport> objList) {
		logger.debug("Start creation of document");
		String pathTemplateName = "exceldata/"+templateName+".xls";
		System.out.println("Template:"+pathTemplateName);
		try(InputStream input = ResourceHelper.getResourcePathInputStream(pathTemplateName)) {//1
		
            Context context = new Context();
			context.putVar("reportgenerators", objList);
			JxlsHelper.getInstance().processTemplate(input, outStream, context); // 3

		} catch (Exception exception) {
			logger.error("Fail to generate the document", exception);
		} finally {
			closeAndFlushOutput(outStream); // 4
		}
	}

	public void createDocumentFromQuery(OutputStream outStream, String templateName, String sql) {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			PropertyFileReader reader = new PropertyFileReader();
		    String dbName = reader.getSQLiteDB(); //reader"target/classes/exceldata/CTS_SearchResults.db";
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbName);
			System.out.println("start");
			JdbcHelper jdbc_helper = null;
			Context context = null;
			logger.debug("Start creation of document");
			try(InputStream input = ResourceHelper.getResourcePathInputStream(templateName)) {//1
				
	            context = new Context();
	    		jdbc_helper = new JdbcHelper(conn);
	    		context.putVar("jdbc", jdbc_helper);
	    		context.putVar("query", sql);
				JxlsHelper.getInstance().processTemplate(input, outStream, context); // 3
	
			} catch (Exception exception) {
	    		System.out.println("jdbc_helper"+jdbc_helper.toString());
	    		System.out.println("OutString:"+outStream);
	    		System.out.println("Context:"+context.toString());
				System.out.println("Error"+exception);
				logger.error("Not able to generate document:", exception);
			} finally {
				closeAndFlushOutput(outStream); // 4
			}
		} catch (ClassNotFoundException e) {
			logger.error("Class Not found:", e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("SQL Exception:", e);
		}
	}

	
	private void closeAndFlushOutput(OutputStream outStream) {
		try {
			outStream.flush();
			outStream.close();
		} catch (IOException exception) {
			logger.error("Fail to flush and close the output", exception);
		}
	}
}