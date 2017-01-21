package bigdata.hadoop.mapreduce_original;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by Administrator on 2016/11/8.
 */
public class CacheWritable implements WritableComparable<CacheWritable>{

    private String first;
    private int second;

    public CacheWritable() {}

    public CacheWritable(String first, int second) {
        this.set(first, second);
    }

    public void set(String first, int second) {
        this.setFirst(first);
        this.setSecond(second);
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public int getSecond() {
        return second - Integer.MAX_VALUE;
    }

    public void setSecond(int second) {
        this.second = second + Integer.MAX_VALUE;
    }

    @Override
    public int compareTo(CacheWritable o) {
        //compare first
        int comp = this.first.compareTo(o.getFirst());

        //equals
        if (0 != comp) {
            return comp;
        }
        /**
         * 利用这个来控制升序或降序
         * this本对象写在前面代表是升序
         * this本对象写在后面代表是降序
         */
        return Integer.valueOf(o.getSecond()).compareTo(Integer.valueOf(this.getSecond()));
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(first);
        out.writeInt(second);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.first = in.readUTF();
        this.second = in.readInt();
    }

    @Override
    public String toString() {
        return first + '\t' +second ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CacheWritable that = (CacheWritable) o;

        if (second != that.second) return false;
        return first != null ? first.equals(that.first) : that.first == null;

    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + second;
        return result;
    }
    /** A Comparator optimized for LongWritable. */
    public static class Comparator extends WritableComparator {
        public Comparator() {
            super(CacheWritable.class);
        }

        @Override
        public int compare(byte[] b1, int s1, int l1,
                           byte[] b2, int s2, int l2) {
            return WritableComparator.compareBytes(b2, s2, l2, b1, s1, l1);
        }
    }
    static {                                       // register default comparator
        WritableComparator.define(CacheWritable.class, new Comparator());
    }
}
