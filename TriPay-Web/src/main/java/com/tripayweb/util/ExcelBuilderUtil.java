package com.tripayweb.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ExcelBuilderUtil extends AbstractExcelView {

//	@Override
//	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		// get data model which is passed by the Spring container
//		// List<PQTransaction> pqTransactions = (List<PQTransaction>)
//		// model.get("transactionList");
//
//		List<PQTransaction> pqTransactions = (List<PQTransaction>) model.get("transactionList");
//		// create a new Excel sheet
//		HSSFSheet sheet = workbook.createSheet("PayQwik");
//		sheet.setDefaultColumnWidth(30);
//
//		// create style for header cells
//		CellStyle style = workbook.createCellStyle();
//		Font font = workbook.createFont();
//		font.setFontName("Arial");
//		style.setFillForegroundColor(HSSFColor.BLACK.index);
//		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//		font.setColor(HSSFColor.WHITE.index);
//		style.setFont(font);
//
//		// create header row
//		HSSFRow header = sheet.createRow(0);
//
//		header.createCell(0).setCellValue("Order Id");
//		header.getCell(0).setCellStyle(style);
//
//		header.createCell(1).setCellValue("Amount");
//		header.getCell(1).setCellStyle(style);
//
//		header.createCell(2).setCellValue("Status");
//		header.getCell(2).setCellStyle(style);
//
//		// create data rows
//
//		int rowCount = 1;
//
//		for (PQTransaction pqTransaction : pqTransactions) {
//			HSSFRow aRow = sheet.createRow(rowCount++);
//			aRow.createCell(0).setCellValue(pqTransaction.getTransactionRefNo());
//			aRow.createCell(1).setCellValue(pqTransaction.getAmount());
//			aRow.createCell(2).setCellValue(pqTransaction.getStatus() + "");
//		}
//	}

	@Override
	protected void buildExcelDocument(Map<String, Object> arg0, HSSFWorkbook arg1, HttpServletRequest arg2,
			HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
