package com.bigdata.hadoop.mapreduce_original;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
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
 * Created by Administrator on 2016/10/29.
 */
public class SecondarySortMapReduce extends Configured implements Tool{
    // 第一步：Mapper Class
    /**
     * public class Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT>
     */
    public static class SecondarySortMapper extends
            Mapper<LongWritable, Text, CacheWritable, IntWritable>{
        public CacheWritable mapOutputKey = new CacheWritable();
        public IntWritable mapOutputValue = new IntWritable(0);

        @Override
        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            String[] fields = value.toString().split(",");

            if (fields.length != 2) {
                return;
            }
            mapOutputKey.set(fields[0], Integer.valueOf(fields[1]));
            mapOutputValue.set(Integer.valueOf(fields[1]));

            context.write(mapOutputKey, mapOutputValue);
        }

        @Override
        public void setup(Context context) throws IOException, InterruptedException {
            super.setup(context);
        }

        @Override
        public void cleanup(Context context) throws IOException, InterruptedException {
            super.cleanup(context);
        }
    }

    //第二步：Reducer Class
    /**
     * public class Reducer<KEYIN,VALUEIN,KEYOUT,VALUEOUT>
     */
    public static class SecondarySortReducer extends
            Reducer<CacheWritable, IntWritable, Text, IntWritable>{
        public IntWritable outputValue = new IntWritable(0);

        @Override
        public void reduce(CacheWritable key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException {
            Text outputKey = new Text();

            int sum = 0;
            for (IntWritable value : values) {
                outputKey.set(key.getFirst());
                context.write(outputKey, value);
            }


        }

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            super.setup(context);
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            super.cleanup(context);
        }
    }

    //第三步：Driver
    public int run(String[] args) throws Exception {
        //第一步：得到配置文件的信息
        Configuration conf = this.getConf();

        //第二步：创建Job
        Job job = Job.getInstance(conf, this.getClass().getSimpleName());
        job.setJarByClass(this.getClass());

        //第三步：设置Job
        //input  --> map  --> reduce  -->output
        //3.1 input
        Path inPuth = new Path(args[0]);
        FileInputFormat.addInputPath(job, inPuth);

        //3.2: mapper
        job.setMapperClass(SecondarySortMapper.class);
        //TODO
        job.setMapOutputKeyClass(CacheWritable.class);
        job.setMapOutputValueClass(IntWritable.class);
        /** ---------------------- Shuffle ---------------------------------*/
        //1:partitioner
        //extends org.apache.hadoop.mapreduce.Partitioner
        job.setPartitionerClass(FirstPartitioner.class);

        //2:sort
        //implements org.apache.hadoop.io.RawComparator
//        job.setSortComparatorClass(cls);

        //3:Combiner
//        job.setCombinerClass(MapReduceTemple.class);

        //4:compress
        //configuration
        conf.set("mapreduce.map.output.compress", "true");
        conf.set("mapreduce.map.output.compress.codec",
                "org.apache.hadoop.io.compress.SnappyCodec");

        //5:group
        //implements org.apache.hadoop.io.RawComparator
        job.setGroupingComparatorClass(FirstGroupingComparator.class);


        /** ---------------------- Shuffle ---------------------------------*/
        //3.3: reducer
        job.setReducerClass(SecondarySortReducer.class);
        //TODO
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // set reduce number
        //TODO
//        job.setNumReduceTasks(tasks);

        //3.4: output
        Path outPath = new Path(args[1]);
        // judge the output file is exist
        FileSystem fileSystem = outPath.getFileSystem(conf);
        if (fileSystem.exists(outPath)) {
            fileSystem.delete(outPath, true);
        }
        FileOutputFormat.setOutputPath(job, outPath);

        //第四步：提交任务
        boolean isSuccess = job.waitForCompletion(true);

        return isSuccess ? 0 : 1;
    }

    public static void main(String[] args) throws Exception{

        args = new String[]{
                "hdfs://spark.don.com:8020/user/dong/data/wc_kv_comma.input",
                "hdfs://spark.don.com:8020/user/dong/hadoop/mapred/output"
        };
        Configuration conf = new Configuration();

        //run job
        int status = ToolRunner.run(
                conf,
                new SecondarySortMapReduce(),
                args
        );

        //exit program
        System.exit(status);
    }
}
