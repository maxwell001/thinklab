package com.think.test.mapreduce;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class MainClass {
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
//		conf.set("mapreduce.framework.name", "yarn");  
//		conf.set("yarn.resourcemanager.address", "192.168.37.110:8032");  
		GenericOptionsParser optionParser = new GenericOptionsParser(conf, args);
		String[] remainingArgs = optionParser.getRemainingArgs();
		if (!(remainingArgs.length != 2 || remainingArgs.length != 4)) {
			System.err.println("Usage: wordcount <in> <out> [-skip skipPatternFile]");
			System.exit(2);
		}
		Job job = Job.getInstance(conf, "word count");
		job.setJarByClass(MainClass.class);
		job.setMapperClass(WordCountMapper.class);
		job.setCombinerClass(WordCountReducer.class);
		job.setReducerClass(WordCountReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		List<String> otherArgs = new ArrayList<String>();
		for (int i=0; i < remainingArgs.length; ++i) {
			if ("-skip".equals(remainingArgs[i])) {
//				job.addCacheFile(new Path(remainingArgs[++i]).toUri());
				job.getConfiguration().setBoolean("wordcount.skip.patterns", true);
			} else {
				otherArgs.add(remainingArgs[i]);
			}
		}
		otherArgs.add(0, "/user/think/input");
		otherArgs.add(1, "output");
		FileInputFormat.addInputPath(job, new Path(otherArgs.get(0)));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs.get(1)));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
