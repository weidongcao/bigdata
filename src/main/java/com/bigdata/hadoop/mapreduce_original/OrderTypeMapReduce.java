package com.bigdata.hadoop.mapreduce_original;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

public class OrderTypeMapReduce extends Configured implements Tool {
	//map class
	public static class OrderTypeMapper extends
            Mapper<LongWritable, Text, UserTimeWritable, Text> {
		
		private UserTimeWritable mapOutputKey = new  UserTimeWritable();
		private Text mapOutputValue  = new Text();
		@Override
		public void setup(Context context) throws IOException,
		InterruptedException {
			// TODO
		}
		
		@Override
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			//line value
			String lineValue = value.toString();
			//split
			String[] values = lineValue.split("\t");
			System.out.println(values.length);
			
			//validate
			if(5 != values.length){
				return;
			}
			
			//userid
			String userid = values[2];
			String time = values[1];
			String search = values[3];
			
			//set
			mapOutputKey.set(userid, time);
			mapOutputValue.set(search);
			context.write(mapOutputKey, mapOutputValue);
		}
		
		@Override
		public void cleanup(Context context) throws IOException,
				InterruptedException {
			// TODO 
		}
	}
	//reduce class
	public static class OrderTypeReducer extends Reducer<UserTimeWritable, Text, Text, IntWritable> {
		private int orderSingleWay = 0;
		private int orderRoundTrip = 0;
		
		
		@Override
		public void setup(Context context)
				throws IOException, InterruptedException {
			// TODO 
		}

		@Override
		public void reduce(UserTimeWritable key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			String[] arrValue = new String[6];  
			
			int count = 0;
			
			for (Text text : values) {
				arrValue[count++] = text.toString();
				
			}
			
			//validate
			if(3 != count){
				return;
			}
			
			if(arrValue[0].startsWith("search") 
					&& "detail".equals(arrValue[1])
					&& "submit".equals(arrValue[2])){
				if("search-singleWay".equals(arrValue[0])){
					orderSingleWay++;
				}else if("search-roundTrip".equals(arrValue[2])){
					orderRoundTrip++;
				}
			}
		}
		
		@Override
		public void cleanup(Context context)
				throws IOException, InterruptedException {
			//context.write(new IntWritable(orderSingleWay),NullWritable.get());
					
			//context.write(new IntWritable(orderRoundTrip),NullWritable.get());
					
		}
	}

	//step 3:Driver
	public int run(String[] args) throws Exception{
		//1. get Configuration 
		Configuration conf = this.getConf();
		
		//2. create job
		Job job = Job.getInstance(conf, this.getClass().getSimpleName());
		job.setJarByClass(this.getClass());
		
		//3. set job
		//3.1input -> map  ->  reduce ->  output
		Path inPath = new Path(args[0]);
		FileInputFormat.addInputPath(job, inPath);
		
		//3.2:mapper
		job.setMapperClass(OrderTypeMapper.class);
		
		job.setMapOutputKeyClass(UserTimeWritable.class);
		job.setMapOutputValueClass(Text.class);
//==============================shuffle==================================
		//1:partitioner
		//extends org.apache.hadoop.mapreduce.Partitioner<KEY, Value>
		job.setPartitionerClass(UserPartitioner.class);
		
		//2:sert
		//org.apache.hadoop.io.RawComparator<T>
//		job.setSortComparatorClass(cls);
		
		//3:combiner
		//job.setCombinerClass(WebPvMapReduce.class);
		
		//4:compress
		//configuration
		
		//5:group
		//org.apache.hadoop.io.RwaComparator<T>
//		job.setGroupingComparatorClass(cls);
		
		
		
		
//================================================================
		
		//3.3: reduce
		job.setReducerClass(OrderTypeReducer.class);
		
		//TODO
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		//set reduce number
//		job.setNumReduceTasks(tasks);
		
		
		//3.4: output
		Path outPath = new Path(args[1]);
		FileOutputFormat.setOutputPath(job, outPath);
		
		//4:submit job
		boolean isSuccess = job.waitForCompletion(true);
		
		
		return isSuccess ? 0 : 1;
	}
	
	public static void main(String[] args) throws Exception {
		args = new String[]{
				"hdfs://hadoop-senior.carmon.com:8020/user/dong/input/quer/order.txt",
				"hdfs://hadoop-senior.carmon.com:8020/user/dong/output/quer/output6"
		};
		
		//get configuration , parse *-default.xml and *-site.xml
		Configuration conf = new Configuration();
		
//===============================set conpress================================================
/*		//set conpress
		conf.set("mapreduce.map.output.compress", "true");
		conf.set(//
				"mapreduce.map.output.compress.codec", //
				"org.apache.hadoop.io.compress.SnappyCodec"//
		);
*/
//=================================set conpress==============================================
		
//		int result = new WordCountMapReduce().run(args);
		int status = ToolRunner.run(//
				conf, //
				new OrderTypeMapReduce(), //
				args//
		);
		
		System.exit(status);
	}

}
