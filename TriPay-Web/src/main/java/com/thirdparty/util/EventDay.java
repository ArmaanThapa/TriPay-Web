package com.thirdparty.util;

public enum EventDay {

	CurrentDate(1),
	Tomorrow(2),
	ThisWeek(3),
	ThisWeekend(4),
	ThisMonth(5),
	AllTime(6),
	CustomDate(7);
	
	private int dayValue;
	EventDay(int dayValue) {
		this.dayValue = dayValue;
	}
	public int getDayValue() {
		return dayValue;
	}
	public void setDayValue(int dayValue) {
		this.dayValue = dayValue;
	}

}
