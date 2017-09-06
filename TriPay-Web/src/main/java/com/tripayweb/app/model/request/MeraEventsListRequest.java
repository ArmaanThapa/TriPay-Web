package com.tripayweb.app.model.request;

import java.util.Date;

import com.thirdparty.util.EventDay;
import com.thirdparty.util.EventsType;

public class MeraEventsListRequest extends MeraEventsCommonRequest{
	
	private int cityId;
	private int categoryId;
	private EventDay day;
	private String dateValue;
	private EventsType type;
	private Date timestamp;
	private int limit;
	private int page;
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public EventDay getDay() {
		return day;
	}
	public void setDay(EventDay day) {
		this.day = day;
	}
	public String getDateValue() {
		return dateValue;
	}
	public void setDateValue(String dateValue) {
		this.dateValue = dateValue;
	}
	public EventsType getType() {
		return type;
	}
	public void setType(EventsType type) {
		this.type = type;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
}
