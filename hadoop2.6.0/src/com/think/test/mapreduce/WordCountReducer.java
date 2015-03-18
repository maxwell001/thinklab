package com.think.test.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	private IntWritable intw = new IntWritable();
	
	public void reduce(Text _key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		System.out.println("key:"+_key);
		System.out.println("values:"+values);
		// process values
		int sum = 0;
		for (IntWritable val : values) {
			System.out.println("val:"+val);
			sum = sum + val.get();
		}
		System.out.println("sum:"+sum);
		intw.set(sum);
		context.write(_key, intw);
	}

}
