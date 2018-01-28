package com.bigdata.hadoop.mapreduce_original;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * Created by Administrator on 2016/11/8.
 */
public class FirstPartitioner extends Partitioner<CacheWritable, IntWritable> {
    @Override
    public int getPartition(CacheWritable key, IntWritable value, int numPartitions) {
        return (key.getFirst().hashCode() & Integer.MAX_VALUE) % numPartitions;
    }
}
