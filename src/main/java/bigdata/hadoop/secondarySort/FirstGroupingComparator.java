package bigdata.hadoop.secondarySort;

import org.apache.hadoop.io.RawComparator;
import org.apache.hadoop.io.WritableComparator;

public class FirstGroupingComparator implements RawComparator<PairWritable> {

	// object compare
	public int compare(PairWritable o1, PairWritable o2) {
		
		return o1.getFirst().compareTo(o2.getFirst());
	}

	//bytes
	public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
		// TODO Auto-generated method stub
		return WritableComparator.compareBytes(b1, 0, l1 - 4, b2, 0, l2 - 4);
	}

}
