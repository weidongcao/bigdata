package bigdata.hadoop.mapreduce_original;

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
public class MapReduceTemple extends Configured implements Tool{
    // 第一步：Mapper Class
    /**
     * public class Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT>
     */
    public static class TempleMapper extends
            Mapper<LongWritable, Text, Text, IntWritable>{
        @Override
        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            //TODO
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
    public static class TempleReducer extends
            Reducer<Text, IntWritable, Text, IntWritable>{

        @Override
        public void reduce(Text key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException {
            //TODO
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
        job.setMapperClass(TempleMapper.class);
        //TODO
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        /** ---------------------- Shuffle ---------------------------------*/
        //1:partitioner
        //extends org.apache.hadoop.mapreduce.Partitioner
//        job.setPartitionerClass(cls);

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
//        job.setGroupingComparatorClass(cls);


        /** ---------------------- Shuffle ---------------------------------*/
        //3.3: reducer
        job.setReducerClass(TempleReducer.class);
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
                "hdfs://spark.don.com:8020/user/dong/data/word-blank.txt",
                "hdfs://spark.don.com:8020/user/dong/hadoop/mapred/output"
        };
        Configuration conf = new Configuration();

        //run job
        int status = ToolRunner.run(
                conf,
                new MapReduceTemple(),
                args
        );

        //exit program
        System.exit(status);
    }
}
