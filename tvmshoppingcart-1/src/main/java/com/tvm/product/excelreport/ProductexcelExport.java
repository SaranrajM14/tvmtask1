package com.tvm.product.excelreport;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.*;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import com.tvm.product.model.Product;




public class ProductexcelExport {
	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	
	private List<Product> listProduct;
	
	
	public  ProductexcelExport(List<Product> listProduct) {
		this.listProduct=listProduct;
		workbook = new HSSFWorkbook();
		
	}
	
	private void createCell(Row row,int columnCount, Object value,CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell=row.createCell(columnCount);
		if(value instanceof Long) {
			cell.setCellValue((Long) value);
		}else if(value instanceof Integer) {
			cell.setCellValue((Integer) value);
		}else if(value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		}else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}
	private void writeHeaderLine() {
		sheet=workbook.createSheet("Product");
		
		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		HSSFFont font=workbook.createFont();
		font.setBold(true);
		font.setFontHeight((short)(7.5*20));
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		createCell(row,0,"Product Information",style);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
		font.setFontHeightInPoints((short)(10));
		
		row=sheet.createRow(1);
		font.setBold(true);
        font.setFontHeight((short)(7.5*20));
        style.setFont(font);
        createCell(row, 0, "Product Id", style);
        createCell(row, 1, "Product Name", style);
        createCell(row, 2, "Product Description", style);
        createCell(row, 3, "Product Price", style);
        createCell(row, 4, "Product Offer", style);
        
	}
	
	private void writeDataLines() {
		int rowCount=2;
		
		CellStyle style=workbook.createCellStyle();
		HSSFFont font=workbook.createFont();
		font.setFontHeight((short)(7.5*20));
		style.setFont(font);
		
		for(Product stu:listProduct) {
			Row row=sheet.createRow(rowCount++);
			int columnCount=0;
			createCell(row, columnCount++, stu.getProductid(), style);
			createCell(row, columnCount++, stu.getProductname(), style);
			createCell(row, columnCount++, stu.getProductprice(), style);
			createCell(row, columnCount++, stu.getProductavilable(), style);
			
		
		}
		Date date=new Date();
	      DateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	      String currentDateTime = dateformatter.format(date);
	      Row row = sheet.createRow(listProduct.size()+2);
	     
	      Cell cell = row.createCell(0);
	     cell.setCellValue(currentDateTime);
	     row.createCell(1).setCellValue(" Product count " +listProduct.size());
		
		
		
		
	}
	
	public void export(HttpServletResponse response) throws IOException{
		writeHeaderLine();
		writeDataLines();
		
		ServletOutputStream outputStream=response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
	
	
}



