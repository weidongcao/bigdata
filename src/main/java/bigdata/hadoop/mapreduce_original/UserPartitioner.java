package bigdata.hadoop.mapreduce_original;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class UserPartitioner extends Partitioner<UserTimeWritable, Text> {

	@Override
	public int getPartition(UserTimeWritable key, Text value, int numPartitions) {
		
		return (key.getUserid().hashCode() & Integer.MAX_VALUE) % numPartitions;
	}

}
