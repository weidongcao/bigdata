package com.bigdata.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/**
 * Created by Cao Wei Dong on 2017-05-07.
 */
public class User2BasicMapReduce extends Configured implements Tool {
    //step 1: Mapper
    public static class ReadUserMapper extends TableMapper<Text, Put> {
        @Override
        protected void map(ImmutableBytesWritable key, Result value, Context context)
                throws IOException, InterruptedException {
            Text mapOutputKey = new Text();

            //get rowkey
            String rowkey = Bytes.toString(key.get());

            //set map output key
            mapOutputKey.set(rowkey);

            // create Put
            Put put = new Put(key.get());
            //iterator
            for (Cell cell:
                 value.rawCells()) {
                // add family : info
                if ("info".equals(Bytes.toString(CellUtil.cloneFamily(cell)))) {
                    // add column : name
                    if ("name".equals(Bytes.toString(CellUtil.cloneQualifier(cell)))) {
                        put.add(cell);
                    }else if ("age".equals(Bytes.toString(CellUtil.cloneQualifier(cell)))) {
                        put.add(cell);
                    }
                }
            }
            // context write
            context.write(mapOutputKey, put);

        }
    }

    //step 2: Reducer
    public static class WriteBasicReduce extends TableReducer<Text, Put, ImmutableBytesWritable> {
        @Override
        protected void reduce(Text key, Iterable<Put> values, Context context)
                throws IOException, InterruptedException {
            for (Put put :
                    values) {
                context.write(null, put);
            }
        }
    }

    //step 3: Driver
    public int run(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        // 1) Configuration
        Configuration config = this.getConf();

        // 2) create job
        Job job = Job.getInstance(config, this.getClass().getSimpleName());

        // 3) set job
        job.setJarByClass(User2BasicMapReduce.class);     // class that contains mapper and reducer

        Scan scan = new Scan();
        scan.setCaching(500);        // 1 is the default in Scan, which will be bad for MapReduce jobs
        scan.setCacheBlocks(false);  // don't set to true for MR jobs
        // set other scan attrs

        TableMapReduceUtil.initTableMapperJob(
                "user",        // input table
                scan,               // Scan instance to control CF and attribute selection
                ReadUserMapper.class,     // mapper class
                Text.class,         // mapper output key
                Put.class,  // mapper output value
                job);

        TableMapReduceUtil.initTableReducerJob(
                "basic",        // output table
                WriteBasicReduce.class,    // reducer class
                job);

        job.setNumReduceTasks(1);   // at least one, adjust as required

        boolean b = job.waitForCompletion(true);
        if (!b) {
            throw new IOException("error with job!");
        }

        return b ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        //get configuration , parse *-default.xml and *-site.xml
        Configuration conf = HBaseConfiguration.create();

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
                new User2BasicMapReduce(), //
                args//
        );

        System.exit(status);
    }
}
