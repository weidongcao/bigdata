package com.bigdata.hadoop.mapreduce_original;

import org.apache.hadoop.io.DataInputBuffer;
import org.apache.hadoop.io.RawComparator;

import java.io.IOException;

public class UserGroupingComparator implements RawComparator<UserTimeWritable> {

	public int compare(UserTimeWritable o1, UserTimeWritable o2) {
		return o1.getUserid().compareTo(o2.getUserid());
	}

	public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
		DataInputBuffer buffer = new DataInputBuffer();
		
		UserTimeWritable o1 = new UserTimeWritable(); 
		UserTimeWritable o2 = new UserTimeWritable();
		
		try{
			buffer.reset(b1, s1, l1); //parse key1
			o1.readFields(buffer); 
			
			buffer.reset(b2, s2, l2);
			o2.readFields(buffer);
		}catch (IOException e){
			throw new RuntimeException(e);
		}
		return o1.getUserid().compareTo(o2.getUserid());
	}


}
