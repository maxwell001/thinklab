package com.besttone.io;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * 标签树excel操作
 */
public class ExcelAction {

	public void labelTreeToExcel(String tagId,String path,String fileName){
		try {
			//创建excel工作薄
			Workbook wb = new XSSFWorkbook();
			Sheet sheet = null;
			Row row = null;
			Row row1 = null;
			Cell cell = null;
			Cell cell1 = null;
			Map<String,Integer> map = new HashMap<String,Integer>();
			//按标签树的第一级分批处理数据
			List<String> lsgrList1 = new ArrayList<String>();
			if(lsgrList1!=null && lsgrList1.size()>0){
				//sheet个数
				int sheetCount = 0;
				//标签包含的下级标签个数
				int lastRowNum = 1;
				//每个sheet的行步进计数
				int rowNumStep = 1;
				//通用单元格样式
				CellStyle style = wb.createCellStyle();
				style.setAlignment(CellStyle.ALIGN_CENTER);
				style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
				style.setBorderBottom(CellStyle.BORDER_THIN);
				style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
				style.setBorderLeft(CellStyle.BORDER_THIN);
				style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
				style.setBorderRight(CellStyle.BORDER_THIN);
				style.setRightBorderColor(IndexedColors.BLACK.getIndex());
				style.setBorderTop(CellStyle.BORDER_THIN);
				style.setTopBorderColor(IndexedColors.BLACK.getIndex());
				//表头单元格样式
				CellStyle style1 = wb.createCellStyle();
				style1.setAlignment(CellStyle.ALIGN_CENTER);
				style1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
				style1.setWrapText(true);
			    style1.setBorderBottom(CellStyle.BORDER_THIN);
			    style1.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			    style1.setBorderLeft(CellStyle.BORDER_THIN);
			    style1.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			    style1.setBorderRight(CellStyle.BORDER_THIN);
			    style1.setRightBorderColor(IndexedColors.BLACK.getIndex());
			    style1.setBorderTop(CellStyle.BORDER_THIN);
			    style1.setTopBorderColor(IndexedColors.BLACK.getIndex());
			    XSSFFont font1 = (XSSFFont) wb.createFont();
			    font1.setBoldweight(Font.BOLDWEIGHT_BOLD);
			    style1.setFont(font1);
			    //标签级别单元格样式
			    CellStyle style2 = wb.createCellStyle();
			    style2.setAlignment(CellStyle.ALIGN_CENTER);
			    style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			    style2.setWrapText(true);
			    style2.setBorderBottom(CellStyle.BORDER_THIN);
			    style2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			    style2.setBorderLeft(CellStyle.BORDER_THIN);
			    style2.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			    style2.setBorderRight(CellStyle.BORDER_THIN);
			    style2.setRightBorderColor(IndexedColors.BLACK.getIndex());
			    style2.setBorderTop(CellStyle.BORDER_THIN);
			    style2.setTopBorderColor(IndexedColors.BLACK.getIndex());
				for(int l=0;l<lsgrList1.size();l++){
					//获取数据
					List<String> labelData = new ArrayList<String>();
					//获取标签最大级别
					String maxLineLength = "";
					String line = "";
					if(labelData!=null && labelData.size()>0){
						for(int i=0;i<labelData.size();i++){
							if(labelData!=null){
								if(line!=null && !"".equals(line)){
									int rowNum = 0;
									String levelStr = "";
									int level = levelStr.length()/2;
									int cellCount = (new BigDecimal(maxLineLength).divide(new BigDecimal(2),BigDecimal.ROUND_HALF_UP)).intValue();
									//表头
									if(level==1){
										rowNumStep = 1;
										map = new HashMap<String,Integer>();
										//第一级表头，初始化sheet
										rowNum = 1;
										sheet = wb.createSheet();
										wb.setSheetName(sheetCount,"");
										sheetCount = sheetCount + 1;
										row1 = sheet.createRow(rowNum);
										cell1 = row1.createCell(1);
										cell1.setCellValue(level+"级");
										cell1.setCellStyle(style1);
										
									}else{
										if(sheet==null){
											sheet = wb.createSheet();
											row1 = sheet.createRow(1);
											cell = row1.createCell(level);
											cell.setCellValue(level+"级");
											style.setFont(font1);
											style.setWrapText(true);
											cell.setCellStyle(style);
										}else{
											//其他级别表头
											cell1 = row1.createCell(level);
											cell1.setCellStyle(style1);
											cell1.setCellValue(level+"级");
										}
									}
									//上一级别
									String preLevel = levelStr.substring(0,levelStr.length()-2);
									if(map.get(preLevel)!=null){
										//处理子标签
										rowNum = rowNumStep+1;
										row = sheet.createRow(rowNum);
										//设置单元格的值
										for(int c=1;c<=cellCount;c++){
											if(c==level){
												cell = row.createCell(level);
												cell.setCellStyle(style2);
												cell.setCellValue("");
											}else{
												//设置空白单元格边框
												cell = row.createCell(c);
												cell.setCellStyle(style);
											}
										}
										if(labelData.size()>i+1){
											//合并单元格
											String temp_line = "";
											int plSize = preLevel.length()/2;
											for(int k=plSize;k>0;k--){
												String ppLevel = preLevel.substring(0, k*2);
												if(temp_line.indexOf(ppLevel)<0){
													sheet.addMergedRegion(new CellRangeAddress(map.get(ppLevel), rowNum, k, k));
													//合并单元格样式
													setRegionStyle(sheet,new CellRangeAddress(map.get(ppLevel), rowNum, k, k),style);
												}
											}
											
										}else{
											//最后一个标签
											int plSize = preLevel.length()/2;
											for(int k=plSize;k>0;k--){
												String ppLevel = preLevel.substring(0, k*2);
												sheet.addMergedRegion(new CellRangeAddress(map.get(ppLevel), rowNum, k, k));
												//合并单元格样式
												setRegionStyle(sheet,new CellRangeAddress(map.get(ppLevel), rowNum, k, k),style);
											}
										}
									}else{
										rowNum = rowNumStep+1;
										row = sheet.createRow(rowNum);
										for(int c=1;c<=cellCount;c++){
											if(c==level){
												cell = row.createCell(level);
												cell.setCellStyle(style2);
												cell.setCellValue("");
											}else{
												//设置空白单元格边框
												cell = row.createCell(c);
												cell.setCellStyle(style);
											}
										}
									}
									//标签其他数据输出单元格
									Field[] fields = this.getClass().getDeclaredFields();
									boolean lineSetFlag = true;
									for(int j=0;j<fields.length;j++){
										String fieldName = fields[j].getName();
										if(fieldName.toLowerCase().endsWith("str")){
											continue;
										}else if(fieldName.equalsIgnoreCase("label_id")){
											continue;
										}
										cell1 = row1.createCell(cellCount+1);
										cell1.setCellStyle(style1);
										cell1.setCellValue(fieldName);
										Cell cell2 = row.createCell(cellCount+1);
										style.setFont(null);
										style.setWrapText(false);
										cell2.setCellStyle(style);
										String value = "";
										try{
											String getMethodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
											Method method = this.getClass().getDeclaredMethod(getMethodName);
											if(method!=null){
												if(method.invoke(this)!=null){
													value = method.invoke(this).toString();
												}
												cell2.setCellValue(value);
											}
										}catch(Exception e){
											e.printStackTrace();
											continue;
										}
										//设置单元格宽度自适应
										if(fieldName.equals("line") && lineSetFlag){
											sheet.autoSizeColumn(cellCount+1, true);
											lineSetFlag = false;
										}
										cellCount++;
									}
									lastRowNum = rowNum;
									map.put(levelStr,lastRowNum);
									rowNumStep++;
								}
							}
						}
					}
				}
			}
			//保存到excel文件
			File file = new File(path);
			if(!file.isDirectory()){
				file.mkdirs();
			}
		    FileOutputStream fileOut;
			fileOut = new FileOutputStream(new File(path+fileName));
			wb.write(fileOut);
		    fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 合并单元格样式
	 * @param sheet
	 * @param region
	 * @param cs
	 */
	private void setRegionStyle(Sheet sheet, CellRangeAddress region , CellStyle cs) {
		 for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
			 Row row = CellUtil.getRow(i, sheet);
			 for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
				 Cell cell = CellUtil.getCell(row, (short) j);
				 cell.setCellStyle(cs);
			 }
		 }
	}
}
