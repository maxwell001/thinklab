package com.think.test.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<Object, Text, Text, IntWritable>  {

	public void map(Object key, Text value,Context context) 
		    throws IOException,InterruptedException {  
		String line = value.toString();
		System.out.println(line);
		if(line==null || line.equals("")){
			return;
		}
		
		Text text = new Text();
		String[] lines = line.split("\n");
		for(int i=0;i<lines.length;i++){
			String[] values = lines[i].split(",");
			text.set(values[1]);
			System.out.println(values[1]);
			context.write(text, new IntWritable(1));
		}
	}
}
