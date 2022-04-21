package com.cucumber.framework.helper.DatabaseHelper;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Searchreport {

	private String dateTime;
	private int time;
	private String module;
	private String place;
	private String startDate;
	private String endDate;
	private String executionStart;
	private String executionEnd;
	private String corelationId;
	private int numRecords;

	private String lowestPrice;
	private BigDecimal execTime;

	public String getExecutionStart() {
		return executionStart;
	}

	public void setModule(String module) {
		this.module= module;
	}



	public String getModule() {
		return module;
	}



	public void setExecutionStart(String executionStart) {
		this.executionStart = executionStart;
	}



	public String getExecutionEnd() {
		return executionEnd;
	}



	public void setExecutionEnd(String executionEnd) {
		this.executionEnd = executionEnd;
	}



	public Searchreport()
	{
		super();
	}


	
	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getStartDate() {
		return startDate;
	}



	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}



	public String getEndDate() {
		return endDate;
	}



	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}



	public int getNumRecords() {
		return numRecords;
	}

	public void setNumRecords(int numRecords) {
		this.numRecords = numRecords;
	}

	public String getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(String lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public BigDecimal getExecTime() {
		return execTime;
	}

	public void setExecTime(BigDecimal execTime) {
		this.execTime = execTime;
	}
	
	
	public Map<String, Object> getHashMap()
	{
		Map<String, Object> data = new HashMap<>();
		data.put("date", this.dateTime);
		data.put("place", this.place);
		data.put("fromdate", this.startDate);
		data.put("todate", this.endDate);
		data.put("records", this.numRecords);
		data.put("exetime", this.execTime);
		return data;
	}

	public String getCorelationId() {
		return corelationId;		
	}


	public void setCorelationId(String id) {
		this.corelationId=id;		
	}
	
	public void setDiffExecutionTime()
	{
		Duration duration = Duration.between(LocalDateTime.parse(executionStart), LocalDateTime.parse(this.executionEnd));
		this.setExecTime(new BigDecimal(duration.toSeconds()));
	}
}