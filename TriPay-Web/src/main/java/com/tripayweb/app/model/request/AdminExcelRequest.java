package com.tripayweb.app.model.request;

import java.util.List;

public class AdminExcelRequest extends PQTransaction {

	private List<AdminUserDetails> list;

	public List<AdminUserDetails> getList() {
		return list;
	}

	public void setList(List<AdminUserDetails> list) {
		this.list = list;
	}

}
