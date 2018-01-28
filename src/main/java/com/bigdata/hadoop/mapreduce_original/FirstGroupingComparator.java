package com.bigdata.hadoop.mapreduce_original;

import org.apache.hadoop.io.RawComparator;
import org.apache.hadoop.io.WritableComparator;

/**
 * Created by Administrator on 2016/11/8.
 */
public class FirstGroupingComparator implements RawComparator<CacheWritable> {
    @Override
    public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
        return WritableComparator.compareBytes(b1, 0, l1-4, b2, 0, l2-4);
    }

    @Override
    public int compare(CacheWritable o1, CacheWritable o2) {
        return o1.getFirst().compareTo(o2.getFirst());
    }
}
