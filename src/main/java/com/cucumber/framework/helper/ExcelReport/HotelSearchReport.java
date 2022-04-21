package com.cucumber.framework.helper.ExcelReport;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class HotelSearchReport {

	private LocalDateTime dateTime;
	private String hotelName;
	private String startDate;
	private String endDate;
	private LocalDateTime executionStart;
	private LocalDateTime executionEnd;

	private int numRecords;

	private BigDecimal lowestPrice;
	private BigDecimal execTime;

	public LocalDateTime getExecutionStart() {
		return executionStart;
	}



	public void setExecutionStart(LocalDateTime executionStart) {
		this.executionStart = executionStart;
	}



	public LocalDateTime getExecutionEnd() {
		return executionEnd;
	}



	public void setExecutionEnd(LocalDateTime executionEnd) {
		this.executionEnd = executionEnd;
	}



	public HotelSearchReport()
	{
		super();
	}


	
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
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

	public BigDecimal getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(BigDecimal lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public BigDecimal getExecTime() {
		return execTime;
	}

	public void setExecTime(BigDecimal execTime) {
		this.execTime = execTime;
	}

	public void setDiffExecutionTime()
	{
		Duration duration = Duration.between(this.executionStart, this.getExecutionEnd());
		BigDecimal d = new BigDecimal(duration.toSeconds());
		this.execTime= d;		
	}
	
	public Map<String, Object> getHashMap()
	{
		Map<String, Object> data = new HashMap<>();
		data.put("date", this.dateTime);
		data.put("place", this.hotelName);
		data.put("fromdate", this.startDate);
		data.put("todate", this.endDate);
		data.put("records", this.numRecords);
		data.put("exetime", this.execTime);
		return data;
	}
}