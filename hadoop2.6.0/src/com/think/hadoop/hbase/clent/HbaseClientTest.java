package com.think.hadoop.hbase.clent;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseClientTest {
	private static Connection conn = null;
	private static Configuration configuration = null;
	
	static{
		try {
			if(configuration==null){
				configuration = HBaseConfiguration.create();
				if(conn==null){
						conn = ConnectionFactory.createConnection(configuration);
					} 
				}
			}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createTable(String tableName,List<String> columns){
		try{
			Admin admin = conn.getAdmin();
			HTableDescriptor table = new HTableDescriptor(TableName.valueOf(tableName));
			if(columns!=null && columns.size()>0){
				for(String column: columns){
					table.addFamily(new HColumnDescriptor(column));
				}
			}
			admin.createTable(table);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	 
	public void dorpTable(String tableName){
		try{
			Admin admin = conn.getAdmin();
			admin.disableTable(TableName.valueOf(tableName));
			admin.deleteTable(TableName.valueOf(tableName));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Result getRowValue(String tableName,String rowId){
		Result result = null;
		try{
			Table table = conn.getTable(TableName.valueOf(tableName));
			Get get = new Get(Bytes.toBytes(rowId));
			result = table.get(get);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public Result getColumnValue(String tableName,String rowId,String columnFamily,String column){
		Result result = null;
		try{
			Table table = conn.getTable(TableName.valueOf(tableName));
			Get get = new Get(Bytes.toBytes(rowId));
			get.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column));
			result = table.get(get);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public void put(String tableName,String rowId,String columnFamily,String column,String data){
		try{
			Table table = conn.getTable(TableName.valueOf(tableName));
			Put put = new Put(Bytes.toBytes(rowId));
			put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(data));
			table.put(put);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		HbaseClientTest client = new HbaseClientTest();
		client.put("user", "123456", "baseinfo", "name", "zhangsan");
		
	}
}
