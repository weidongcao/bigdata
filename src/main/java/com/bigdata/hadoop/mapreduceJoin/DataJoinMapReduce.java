package com.bigdata.hadoop.mapreduceJoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * MapReduce模板，其他所有的MapReduce编程都可以以些修改
 * @author Administrator
 *
 */

public class DataJoinMapReduce extends Configured implements Tool {
	//map class
	public static class DataJoinMapper extends
            Mapper<LongWritable, Text, LongWritable, DataJoinWritable> {
		
		//map output key
		private LongWritable mapoutputKey = new LongWritable();
		
		//map output Value
		private DataJoinWritable mapoutputValue = new DataJoinWritable();
		@Override
		public void setup(Context context) throws IOException,
		InterruptedException {
			// TODO
		}
		
		//TODO
		@Override
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			//line value
			String lineValue = value.toString();
			
			//split
			String[] vals = lineValue.split("\t");
			
			int length = vals.length;
			//invalid
			if((6 != length) && (5 != length)){
				return;
			}
			
			//get cid
			Long cid = Long.valueOf(vals[0]);
			
			if(null == cid){
				return;
			}
			
			//set Customer
			if(6 == length){
				String phone = vals[3] ;
				//set
				mapoutputKey.set(cid);
				mapoutputValue.set("customer", vals[1] + "\t" + phone);
			}
			
			//set order
			if(5 == length){
				String price = vals[3];
				String date = vals[2];
				//set
				mapoutputKey.set(Long.valueOf(vals[1]));
				mapoutputValue.set("order", cid + "\t" + price + "\t" + date);
			}
			context.write(mapoutputKey, mapoutputValue);
		}
		
		@Override
		public void cleanup(Context context) throws IOException,
				InterruptedException {
			// TODO 
		}
	}
	//reduce class
	public static class DataJoinReducer extends Reducer<LongWritable, DataJoinWritable, NullWritable, Text> {

		//output value
		private Text outputValue = new Text();
		@Override
		public void setup(Context context)
				throws IOException, InterruptedException {
			// TODO 
		}

		@Override
		public void reduce(LongWritable key, Iterable<DataJoinWritable> values, Context context)
				throws IOException, InterruptedException {
			String customerInfo = null;
			List<String> orderList = new ArrayList<String>();
			
			//iterator
			for (DataJoinWritable value : values) {
				if("customer".equals(value.getTag())){
					customerInfo = value.getData();
				}else if("order".equals(value.getTag())){
					orderList.add(value.getData());
				}
			}
			
			//output
			for (String order : orderList) {
				//set output value
				outputValue.set(key.get() + "\t" + customerInfo + "\t" + order);
				
				//output
				context.write(NullWritable.get(), outputValue);
			}
		}
		
		@Override
		public void cleanup(Context context)
				throws IOException, InterruptedException {
			// TODO 
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
		job.setMapperClass(DataJoinMapper.class);
		
		//LongWritable, DataJoinWritable
		job.setMapOutputKeyClass(LongWritable.class);
		job.setMapOutputValueClass(DataJoinWritable.class);
//==============================shuffle==================================
		//1:partitioner
		//extends org.apache.hadoop.mapreduce.Partitioner<KEY, Value>
//		job.setPartitionerClass(cls);
		
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
		job.setReducerClass(DataJoinReducer.class);
		
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
		
		//set reduce number
		//TODO
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
				"hdfs://hadoop-senior.carmon.com:8020/user/dong/input/MapReduceJoin",
				"hdfs://hadoop-senior.carmon.com:8020/user/dong/output/mapreduce/mapreduceJoin/output8"
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
				new DataJoinMapReduce(), //
				args//
		);
		
		System.exit(status);
	}

}
