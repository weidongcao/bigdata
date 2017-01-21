package bigdata.hadoop.secondarySort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PairWritable implements WritableComparable<PairWritable> {

	private String first; 
	private int second;
	
	
	
	
	public PairWritable() {
	}
	
	

	public PairWritable(String first, int second) {
		this.set(first, second);
	}

	public void set(String first, int second){
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

	public void write(DataOutput out) throws IOException {
		out.writeUTF(first);
		out.writeInt(second);
		
	}

	public void readFields(DataInput in) throws IOException {
		this.first = in.readUTF();
		this.second = in.readInt();
	}

	public int compareTo(PairWritable o) {
		//compare first
		int comp = this.first.compareTo(o.getFirst());
		
		//equals
		if(0 != comp){
			return comp;
		}
		
		//compare second
		return Integer.valueOf(this.getSecond()).compareTo(Integer.valueOf(o.getSecond()));
	}



	@Override
	public String toString() {
		return first + "\t" + second ;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + second;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PairWritable other = (PairWritable) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (second != other.second)
			return false;
		return true;
	}

	/** A Comparator optimized for LongWritable. */ 
	public static class Comparator extends WritableComparator {
	    public Comparator() {
	      super(PairWritable.class);
	    }

	    @Override
	    public int compare(byte[] b1, int s1, int l1,
	                       byte[] b2, int s2, int l2) {
	      long thisValue = readLong(b1, s1);
	      long thatValue = readLong(b2, s2);
	      return WritableComparator.compareBytes(b1, s1, l1, b2, s2, l2);
	    }
	}
	static {   // register default comparator
		WritableComparator.define(PairWritable.class, new Comparator());
	}

	
}
