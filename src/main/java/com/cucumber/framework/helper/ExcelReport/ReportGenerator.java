package com.cucumber.framework.helper.ExcelReport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cucumber.framework.utility.ResourceHelper;

public class ReportGenerator {

	private static final Logger logger = LoggerFactory.getLogger(ReportGenerator.class);

	public void createDocument(OutputStream outStream, String templateName, String objStr, HotelSearchReport obj) {
		logger.debug("Start creation of document");
		List<HotelSearchReport> objList = new ArrayList();
		String pathTemplateName = "exceldata/"+templateName+".xls";
		System.out.println("Template:"+pathTemplateName);
		try(InputStream input = ResourceHelper.getResourcePathInputStream(pathTemplateName)) {//1
		
            Context context = new Context();
            context.putVar(objStr, objList.add(obj));
    		System.out.println("Template:"+outStream.toString());
            JxlsHelper.getInstance().processTemplate(input, outStream, context); // 3

		} catch (Exception exception) {
			logger.error("Fail to generate the document", exception);
		} finally {
			closeAndFlushOutput(outStream); // 4
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