package com.bigdata.hadoop.mapreduce_original;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class UserTimeWritable implements WritableComparable<UserTimeWritable> {

	private String userid;
	private String time;
	
	public UserTimeWritable() {
	}

	public UserTimeWritable(String userid, String time) {
		this.set(userid, time);
	}
	
	public void set(String userid, String time){
		this.setUserid(userid);
		this.setTime(time);
		
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}


	public int compareTo(UserTimeWritable o) {
		int comp = this.getUserid().compareTo(o.getUserid());
		
		if(0 != comp){
			return comp;
		}
		
		return this.getTime().compareTo(o.getTime());
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
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
		UserTimeWritable other = (UserTimeWritable) obj;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return userid + "\t" + time;
	}

	public void write(DataOutput out) throws IOException {
		out.writeUTF(this.getUserid());
		out.writeUTF(this.getTime());
		
	}

	public void readFields(DataInput in) throws IOException {
		this.setUserid(in.readUTF());
		this.setTime(in.readUTF());
		
	}
	
	
	
}
