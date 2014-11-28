package com.besttone.test.poi;

import java.util.List;

/**
 * 
 * 标签树excel操作
 */
public class LabelExcelAction {

	public void labelTreeToExcel(List<Object> labelData,String path,String type){
		try {
			//创建excel工作薄
			Workbook wb = new XSSFWorkbook();
			Sheet sheet = null;
			Row row = null;
			Row row1 = null;
			Cell cell = null;
			Map<String,String> map = new HashMap<String,String>();
			if(labelData!=null && labelData.size()>0){
				int sheetCount = 0;
				for(int i=0;i<labelData.size();i++){
					Object obj = labelData.get(i);
					if(obj!=null && LabelConstant.LABEL_TREE_TYPE_QY.equals(type)){
						LabelSystemQy lsqy = (LabelSystemQy)obj;
						String line = lsqy.getLine();
						if(line!=null && !"".equals(line)){
							int cellCount = 0;
							String levelStr = LabelLineUtil.getLineLevel(line);
							int level = levelStr.length()/2;
							//第一行表头
							if(level==1){
								//第一级
								sheet = wb.createSheet(lsqy.getName());
								wb.setSheetName(sheetCount, lsqy.getName());
								sheetCount = sheetCount + 1;
								row1 = sheet.createRow(1);
								cell = row1.createCell(1);
								cell.setCellValue(level+"级");
							}else{
								if(sheet==null){
									sheet = wb.createSheet();
								}
								row1 = sheet.createRow(1);
								cell = row1.createCell(level);
								cell.setCellValue(level+"级");
							}
							cellCount = level;
							row = sheet.createRow(i+2);
							cell = row.createCell(level);
							cell.setCellValue(lsqy.getName());
							
							Method[] methods = lsqy.getClass().getMethods();
							for(int j=0;j<methods.length;j++){
								String methodName = methods[j].getName();
								if(methodName.startsWith("get")){
									String fieldName = methodName.substring(0, methodName.length()).toLowerCase();
									Cell cell1 = row1.createCell(cellCount+1);
									cell1.setCellValue(fieldName);
									Cell cell2 = row.createCell(cellCount+1);
									String value = "";
									if(methods[j].invoke(lsqy)!=null){
										value = methods[j].invoke(lsqy).toString();
									}
									cell2.setCellValue(value);
									cellCount++;
								}
								
							}
							
						}
					}
				}
				
			}
			
			//淇濆瓨鍒癳xcel鏂囦欢
		    FileOutputStream fileOut;
			fileOut = new FileOutputStream(path);
			wb.write(fileOut);
		    fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	}
}
