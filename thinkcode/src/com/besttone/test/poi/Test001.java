package com.besttone.test.poi;

import java.util.ArrayList;
import java.util.List;

import com.besttone.io.ExcelAction;

public class Test001{ 
	public static void main (String args[]) { 
		ExcelAction la = new ExcelAction();
		List<Object> lsqys = new ArrayList<Object>();
		LabelSystemQy l1 = new LabelSystemQy();
		l1.setLine("01000000000000000000000000000000");
		l1.setName("基础属性");
		l1.setLabel_columnName("ORG_SERIAL_ID");
		LabelSystemQy l2 = new LabelSystemQy();
		l2.setLine("01020000000000000000000000000000");
		l2.setName("户名标签");
		l2.setLabel_columnName("ORG_SERIAL_ID");
		LabelSystemQy l3 = new LabelSystemQy();
		l3.setLine("01020100000000000000000000000000");
		l3.setName("政企全称");
		l3.setLabel_columnName("ORG_SERIAL_ID");
		lsqys.add(l1);
		lsqys.add(l2);
		lsqys.add(l3);
		
	}
}