package bigdata.hbase;

import java.util.Random;

public class TransformHFileMapReduce {
	public static void main(String[] args) {
		String[] keys = {"aa", "bb", "cc", "dd", "ee", "ff"};
		Random random = new Random(90);
		Random ran = new Random(6);
		for (int i = 0; i < 100; i++) {
			String key = keys[ran.nextInt()];
			int value = random.nextInt() + 10;
			System.out.println(key + " " + value);
		}
	}
}
