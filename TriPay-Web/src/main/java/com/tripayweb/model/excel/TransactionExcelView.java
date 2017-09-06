package com.tripayweb.model.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.tripayweb.app.model.request.AdminUserDetails;
import com.tripayweb.app.model.request.PQTransaction;

public class TransactionExcelView extends AbstractExcelView {

	public TransactionExcelView() {
	}

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HSSFSheet excelSheet = workbook.createSheet("Transaction Report");
		setExcelHeader(excelSheet);

		List<AdminUserDetails> transactionReport = (List<AdminUserDetails>) model.get("transactionList");
		// for (PQTransaction pqTransaction : transactionReport) {
		// System.out.println("PQ @! ::" + pqTransaction.getDescription());
		// }
		setExcelRows(excelSheet, transactionReport);

	}

	public void setExcelHeader(HSSFSheet excelSheet) {
		HSSFRow excelHeader = excelSheet.createRow(0);
		excelHeader.createCell(0).setCellValue("Account Holder Name");
		excelHeader.createCell(1).setCellValue("Balance");
		excelHeader.createCell(2).setCellValue("Contact Number");
		excelHeader.createCell(3).setCellValue("Transaction ID");
		excelHeader.createCell(4).setCellValue("Transaction Date");
		excelHeader.createCell(5).setCellValue("Transaction Type");
		excelHeader.createCell(6).setCellValue("Debit");
		excelHeader.createCell(7).setCellValue("Credit");
		excelHeader.createCell(8).setCellValue("Commission");
		excelHeader.createCell(9).setCellValue("Status");

	}

	public void setExcelRows(HSSFSheet excelSheet, List<AdminUserDetails> transactionReports) {
		int record = 1;

		for (AdminUserDetails transaction : transactionReports) {
			// m System.out.println("PQ @ ::" + transaction.getDescription());
			HSSFRow excelRow = excelSheet.createRow(record++);
			excelRow.createCell(0).setCellValue(transaction.getUsername());
//			excelRow.createCell(1).setCellValue(transaction.getCurrentBalance());
//			excelRow.createCell(2).setCellValue(transaction.getContactNo());
//			excelRow.createCell(3).setCellValue(transaction.getTransactionRefNo());
//			excelRow.createCell(4).setCellValue(transaction.getDateOfTransaction());
//			excelRow.createCell(5).setCellValue(transaction.getServiceType());
//			excelRow.createCell(6).setCellValue(transaction.getDebit());
//			excelRow.createCell(7).setCellValue(transaction.getCredit());
//			excelRow.createCell(8).setCellValue(transaction.getCommision());
//			excelRow.createCell(9).setCellValue(transaction.getStatus());

			// m
			// excelRow.createCell(3).setCellValue(transaction.getStatus().getValue());

			// List<User> userList = (List<User>) userRepository.findAllUser();
			//
			// for (User user : userList) {
			// if
			// (transaction.getAccount().getId().equals(user.getAccountDetail().getId()))
			// {
			// excelRow.createCell(4).setCellValue(user.getUsername());
			//
			// }
			// }

		}
	}
}
