package com.lumanmed.activemq.log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * sql��־������
 */
public class SpySqlLogUtil {
	/**
	 * ��ȡ���ڵ�sql���
	 * @return
	 */
	public static String getPeriodSql(){
		String sql = "";
		try{
			File file = new File(Constant.LOGFILE_PATH+"spy.log");
			File bacFile = new File(Constant.BACK_LOGFILE_PATH+"spy.bac");
			String now = getNowString();
			if(bacFile.isFile()){
				long size = bacFile.length();
				long maxSize = Long.parseLong(Constant.BACK_LOGFILE_SIZE)*1024*1024;
				if(size>=maxSize){
					//�����µı����ļ�
					File recordTxt = new File(Constant.BACK_LOGFILE_PATH+"record.txt");
					BufferedReader brBac = new BufferedReader(new FileReader(recordTxt));
					String bacName = "";
					String temp = "";
					//��ȡ���±����ļ���
					while((temp=brBac.readLine())!=null){
						bacName = temp;
					}
					//�����µı����ļ���
					if("".equals(bacName)){
						bacName = "spy_" + now + "_" + 1 + ".bac";
					}else{
						bacName = "spy_" + now + "_"  + (Integer.parseInt(bacName.substring(bacName.lastIndexOf(".")-1, bacName.lastIndexOf(".")))+1) + ".bac";
					}
					bacFile = new File(Constant.BACK_LOGFILE_PATH+bacName);
					//д���¼txt�ļ�
					BufferedWriter bwBac = new BufferedWriter(new FileWriter(recordTxt,true));
					bwBac.write(Constant.BACK_LOGFILE_PATH+bacName);
					bwBac.newLine();
					brBac.close();
					bwBac.close();
				}
			}else{
				bacFile.createNewFile();
			}
			//��ȡsql���
			if(file.isFile()){
				BufferedReader br = new BufferedReader(new FileReader(file));
				BufferedWriter bwBacFile = new BufferedWriter(new FileWriter(bacFile,true));
				String temp = "";
				while((temp=br.readLine())!=null){
					//�����ļ�
					bwBacFile.write(temp);
					bwBacFile.newLine();
					if(!"".equals(temp) && temp.indexOf("|")>0){
						String[] temps = temp.split("\\|");
						if(temps.length==2){
							if("".equals(sql)){
								sql = temps[1];
							}else{
								sql = sql + ";" + temps[1];
							}
						}
					}
				}
				//���sql�ļ�����
				BufferedWriter bwFile = new BufferedWriter(new FileWriter(file,false));
				bwFile.write("");
				//�ر�io
				bwBacFile.close();
				bwFile.close();
				br.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return sql;
	}
	
	public static String getAllSql(){
		String sql = "";
		try{
			File recordTxt = new File(Constant.BACK_LOGFILE_PATH+"record.txt");
			if(recordTxt!=null && recordTxt.exists()){
				BufferedReader brBac = new BufferedReader(new FileReader(recordTxt));
				BufferedWriter bwBac = new BufferedWriter(new FileWriter(recordTxt,false));
				String bacName = "";
				//��ȡ�����ļ���
				while((bacName=brBac.readLine())!=null){
					File bacFile = new File(Constant.BACK_LOGFILE_PATH+bacName);
					if(bacFile!=null && bacFile.isFile()){
						BufferedReader br = new BufferedReader(new FileReader(bacFile));
						String temp = "";
						while((temp=br.readLine())!=null){
							if(!"".equals(temp) && temp.indexOf("|")>0){
								String[] temps = temp.split("|");
								if(temps.length==2){
									if("".equals(sql)){
										sql = temps[1];
									}else{
										sql = sql + ";" + temps[1];
									}
								}
							}
						}
						//ɾ������sql�ļ�
						bacFile.delete();
						br.close();
					}
				}
				//��ռ�¼
				bwBac.write("");
				brBac.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return sql;
	}
	
	public static String getNowString(){
		String now = "";
		try{
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			now = df.format(new Date());
		}catch(Exception e){
			e.printStackTrace();
		}
		return now;
	}
}
