package bigdata.hadoop.secondarySort;

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

/**
 * MapReduce模板，其他所有的MapReduce编程都可以以些修改
 * @author Administrator
 *
 */

public class SortedMapReduce extends Configured implements Tool {
	//map class
	public static class SortMapper extends
            Mapper<LongWritable, Text, Text, IntWritable> {
		
		private Text mapOutputKey = new Text();
		private IntWritable mapOutputValue = new IntWritable();
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
			String[] strs = lineValue.split(",");
			
			//invalid		}
			if(2 != strs.length){
				return ;
			}
			
			//set map ouput key
			mapOutputKey.set(strs[0]);
			
			//set map ouput value
			mapOutputValue.set(Integer.valueOf(strs[1]));
			
			//output
			context.write(mapOutputKey, mapOutputValue);
		}
		@Override
		public void cleanup(Context context) throws IOException,
				InterruptedException {
			// TODO 
		}
	}
	//reduce class
	public static class SortReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

		@Override
		public void setup(Context context)
				throws IOException, InterruptedException {
			// TODO 
		}

		//TODO
		@Override
		public void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {
			//sort
			//list store values
			//List<IntWritable> valueList = new ArrayList<IntWritable>();
			//iterator
			for (IntWritable value : values) {
				context.write(key, value);
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
		job.setMapperClass(SortMapper.class);
		
		//TODO
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
//==============================shuffle==================================
		//1:partitioner
		//extends org.apache.hadoop.mapreduce.Partitioner<KEY, Value>
//		job.setPartitionerClass(cls);
		
		//2:sort
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
		job.setReducerClass(SortReducer.class);
		
		//TODO
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
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
				"hdfs://hadoop-senior.carmon.com:8020/user/dong/input/wc_kv_comma.input",
				"hdfs://hadoop-senior.carmon.com:8020/user/dong/output/mapreduce/secondary/output05"
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
				new SortedMapReduce(), //
				args//
		);
		
		System.exit(status);
	}

}
