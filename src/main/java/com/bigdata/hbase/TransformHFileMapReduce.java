package com.bigdata.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.io.hfile.HFile;
import org.apache.hadoop.hbase.mapreduce.HFileOutputFormat2;
import org.apache.hadoop.hbase.mapreduce.PutSortReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

public class TransformHFileMapReduce extends Configured implements Tool {

    public static final String COLUMN_FAMILY = "info";
    public static final String[] COLUMNS = new String[]{
            "rowkey", "name", "age", "sex", "address", "phone"
    };

    public static class TransformHFileMapper extends
            Mapper<LongWritable, Text, ImmutableBytesWritable, Put> {

        //, ImmutableBytesWritable, KeyValue
        private ImmutableBytesWritable rowkey = new ImmutableBytesWritable();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //line value
            String line = value.toString();

            //split
            String[] arr = line.split(",");

            //create rowkey
            rowkey.set(Bytes.toBytes(arr[0]));

            //create put instance
            Put put = new Put(rowkey.get());


            for (int i = 0; i < arr.length; i++) {
                put.addColumn(Bytes.toBytes(COLUMN_FAMILY), Bytes.toBytes(COLUMNS[i]), Bytes.toBytes(arr[i]));
            }

            //context write
            context.write(rowkey, put);
        }
    }

    @Override
    public int run(String[] args) throws Exception {
        //get configuration
        Configuration conf = this.getConf();

        //create job
        Job job = Job.getInstance(conf, this.getClass().getSimpleName());

        //set run job class
        job.setJarByClass(TransformHFileMapReduce.class);

        //set job
        //step 1: set input
        Path inpath = new Path(args[1]);
        FileInputFormat.addInputPath(job, inpath);

        //step 2: set map class
        job.setMapperClass(TransformHFileMapper.class);

        job.setMapOutputKeyClass(ImmutableBytesWritable.class);
        job.setMapOutputValueClass(Put.class);

        //step 3 : set reduce class
        job.setReducerClass(PutSortReducer.class);
        job.setOutputKeyClass(ImmutableBytesWritable.class);
        job.setOutputValueClass(KeyValue.class);

        //step 4 : ouput
        Path outputDir = new Path(args[2]);
        FileOutputFormat.setOutputPath(job, outputDir);

        //step 5 :
        // get table instance
        HTable table = new HTable(conf, args[0]);

        HFileOutputFormat2.configureIncrementalLoadMap(job, table);

        //submit job
        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        //get configuration
        Configuration conf = HBaseConfiguration.create();

        //run job
        int status = ToolRunner.run(conf, new TransformHFileMapReduce(), args);

        //exit program
        System.exit(status);

    }

}
