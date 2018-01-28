package com.bigdata.hadoop.mapreduce_original;

import org.apache.hadoop.conf.Configuration;
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

import java.io.IOException;

/**
 * Created by Administrator on 2016/10/29.
 */
public class WordCountMapReduce {
    // 第一步：Mapper Class
    /**
     * public class Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT>
     */
    public static class WordCountMapper extends
            Mapper<LongWritable, Text, Text, IntWritable>{
        private Text mapOutputKey = new Text();
        private final static IntWritable mapOutputValue = new IntWritable(1);



        /**
         * protected void map(KEYIN key, VALUEIN value,Context context)
         */
        @Override
        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            //line value
            String line = value.toString();

            //split
            String[] strs = line.split(" ");

            //iterator
            for (String str : strs) {
                //set map output key
                mapOutputKey.set(str);

                //output value
                context.write(mapOutputKey, mapOutputValue);
            }
        }
    }

    //第二步：Reducer Class
    /**
     * public class Reducer<KEYIN,VALUEIN,KEYOUT,VALUEOUT>
     */
    public static class WordCountReducer extends
            Reducer<Text, IntWritable, Text, IntWritable>{
        private IntWritable outputValue = new IntWritable();

        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException {
            //temp : sum
            int sum = 0;

            for (IntWritable value : values) {
                //total
                sum += value.get();
            }

            //set output value
            outputValue.set(sum);
            System.out.println("Reduce : " + key.toString() + " => " + sum);

            context.write(key, outputValue);
        }
    }

    //第三步：Driver
    public int run(String[] args) throws Exception {
        //第一步：得到配置文件的信息
        Configuration conf = new Configuration();


        //第二步：创建Job
        Job job = Job.getInstance(conf, this.getClass().getSimpleName());
        job.setJarByClass(this.getClass());

        //第三步：设置Job
        //input  --> map  --> reduce  -->output
        //3.1 input
        Path inPuth = new Path(args[0]);
        FileInputFormat.addInputPath(job, inPuth);

        //3.2: mapper
        job.setMapperClass(WordCountMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //3.3: reducer
        job.setReducerClass(WordCountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //set Reduce number
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

        //第五步：
    }

    public static void main(String[] args) throws Exception{
        args = new String[]{
            "hdfs://spark.don.com:8020/user/dong/data/word-blank.txt",
                "hdfs://spark.don.com:8020/user/dong/hadoop/mapred/output5"
        };

        //run job
        int status = new WordCountMapReduce().run(args);

        System.exit(status);
    }
}
