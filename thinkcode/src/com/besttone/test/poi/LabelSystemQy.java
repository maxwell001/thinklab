package com.besttone.test.poi;

import java.util.Date;

public class LabelSystemQy {
	private Integer  label_id;
	private Integer  tag_id;
	private String  line;
	private String  code;
	private String  label_value_type;
	private String  name;
	private String  label_description;
	private String  note1;
	private String  bx_Name;
	private String  py_Name;
	private Date  label_startTime;
	private Date  label_endTime;
	private String  label_startTimeStr;
	private String  label_endTimeStr;
	private String  label_columnName;
	private Integer  create_empl_id;
	private String create_empl_name;
	private Date  create_time;
	@SuppressWarnings("unused")
	private String  create_time_str;
	private Integer  modify_empl_id;
	private String modify_empl_name;
	private Date  modify_time;
	@SuppressWarnings("unused")
	private String modify_time_str;
	private Integer  audit_empl_id;
	private String audit_empl_name;
	private Date  audit_time;
	@SuppressWarnings("unused")
	private String audit_time_str;
	private Integer  status;
	private String  audit_remake;
	private Integer  customer_number;
	private Integer  times_cited;
	private Integer  label_type;
	private String  label_txt;
	private String  label_syn;
	private String  label_startValue;
	private String  label_endValue;
	//融合标签包含的code集合
	private String fusion_codes;
	private String fusion_names;
	
	public Integer getLabel_id() {
		return label_id;
	}
	public void setLabel_id(Integer label_id) {
		this.label_id = label_id;
	}
	public Integer getTag_id() {
		return tag_id;
	}
	public void setTag_id(Integer tag_id) {
		this.tag_id = tag_id;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLabel_description() {
		return label_description;
	}
	public void setLabel_description(String label_description) {
		this.label_description = label_description;
	}
	public String getNote1() {
		return note1;
	}
	public void setNote1(String note1) {
		this.note1 = note1;
	}
	public String getBx_Name() {
		return bx_Name;
	}
	public void setBx_Name(String bx_Name) {
		this.bx_Name = bx_Name;
	}
	public String getPy_Name() {
		return py_Name;
	}
	public void setPy_Name(String py_Name) {
		this.py_Name = py_Name;
	}
	
	public Date getLabel_startTime() {
		return label_startTime;
	}
	public void setLabel_startTime(Date label_startTime) {
		this.label_startTime = label_startTime;
	}
	public Date getLabel_endTime() {
		return label_endTime;
	}
	public void setLabel_endTime(Date label_endTime) {
		this.label_endTime = label_endTime;
	}
	public String getLabel_columnName() {
		return label_columnName;
	}
	public void setLabel_columnName(String label_columnName) {
		this.label_columnName = label_columnName;
	}
	public Integer getCreate_empl_id() {
		return create_empl_id;
	}
	public void setCreate_empl_id(Integer create_empl_id) {
		this.create_empl_id = create_empl_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	
	public String getModify_empl_name() {
		return modify_empl_name;
	}
	public void setModify_empl_name(String modify_empl_name) {
		this.modify_empl_name = modify_empl_name;
	}
	public String getAudit_empl_name() {
		return audit_empl_name;
	}
	public void setAudit_empl_name(String audit_empl_name) {
		this.audit_empl_name = audit_empl_name;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getModify_empl_id() {
		return modify_empl_id;
	}
	public void setModify_empl_id(Integer modify_empl_id) {
		this.modify_empl_id = modify_empl_id;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public Integer getAudit_empl_id() {
		return audit_empl_id;
	}
	public void setAudit_empl_id(Integer audit_empl_id) {
		this.audit_empl_id = audit_empl_id;
	}
	public Date getAudit_time() {
		return audit_time;
	}
	public void setAudit_time(Date audit_time) {
		this.audit_time = audit_time;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAudit_remake() {
		return audit_remake;
	}
	public void setAudit_remake(String audit_remake) {
		this.audit_remake = audit_remake;
	}
	public Integer getCustomer_number() {
		return customer_number;
	}
	public void setCustomer_number(Integer customer_number) {
		this.customer_number = customer_number;
	}
	public Integer getTimes_cited() {
		return times_cited;
	}
	public void setTimes_cited(Integer times_cited) {
		this.times_cited = times_cited;
	}
	public Integer getLabel_type() {
		return label_type;
	}
	public void setLabel_type(Integer label_type) {
		this.label_type = label_type;
	}
	public String getLabel_txt() {
		return label_txt;
	}
	public void setLabel_txt(String label_txt) {
		this.label_txt = label_txt;
	}
	public String getLabel_syn() {
		return label_syn;
	}
	public void setLabel_syn(String label_syn) {
		this.label_syn = label_syn;
	}
	public String getLabel_startValue() {
		return label_startValue;
	}
	public void setLabel_startValue(String label_startValue) {
		this.label_startValue = label_startValue;
	}
	public String getLabel_endValue() {
		return label_endValue;
	}
	public void setLabel_endValue(String label_endValue) {
		this.label_endValue = label_endValue;
	}
	public String getLabel_value_type() {
		return label_value_type;
	}
	public void setLabel_value_type(String label_value_type) {
		this.label_value_type = label_value_type;
	}
	
	public String getFusion_codes() {
		return fusion_codes;
	}
	public void setFusion_codes(String fusion_codes) {
		this.fusion_codes = fusion_codes;
	}
	
	public String getFusion_names() {
		return fusion_names;
	}
	public void setFusion_names(String fusion_names) {
		this.fusion_names = fusion_names;
	}
	
	public String getCreate_empl_name() {
		return create_empl_name;
	}
	public void setCreate_empl_name(String create_empl_name) {
		this.create_empl_name = create_empl_name;
	}
	
}