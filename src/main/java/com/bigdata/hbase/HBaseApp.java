package com.bigdata.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.*;
import java.util.Map.Entry;

public class HBaseApp {
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		List<Map<String, Object>> list = createData();
		putData(list);
	}
	
	private static List<Map<String, Object>> createData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		int age;
		int sex;
		for (int i = 0; i < 10; i++) {
			map = new HashMap<String, Object>();
			map.put("name", "friend" + i );
			Random random = new Random();
			age = random.nextInt(10000);
			age = age%100;
			if(age >= 4)
				map.put("age", age + "");
			sex = random.nextInt(1);
			if(sex == 0){
				map.put("sex", "female");
			}else if(sex == 1){
				map.put("sex", "male");
			}
			list.add(map);
		}

		return list;
	}

	private static void putData(List<Map<String, Object>> list) throws IOException, InterruptedIOException,
			RetriesExhaustedWithDetailsException {
		String tableName = "user";
		
		//Get table instance
		HTable table = getHTableByTableName(tableName);
		//Bytes.toBigDecimal(table.getEndKeys());
		//BigDecimal index = new BigDecimal(new String(table.getEndKeys()));
		
		//create put instance
		Put put = null;
		int index = 1015;
		for (Map<String, Object> map : list) {
			put = new Put(Bytes.toBytes(index + ""));
			for (Entry<String, Object> entry: map.entrySet()) {
				put.add(
						HBaseTableConstant.HBASE_TABLE_USER_CF,
						Bytes.toBytes(entry.getKey()),
						Bytes.toBytes((String)entry.getValue())
						);
			}
			table.put(put);
			index++;
		}
		table.close();	
	}

	private static void getData() throws IOException {
		String tableName = "user";
		
		HTable table = getHTableByTableName(tableName);
		
		Get get = new Get(Bytes.toBytes("1001"));
		
		Result result = table.get(get);
		
		for (Cell cell : result.rawCells()) {
			System.out.println(//
					Bytes.toString(CellUtil.cloneFamily(cell))
					+ " : " + 
					Bytes.toString(CellUtil.cloneQualifier(cell))
					 + " -> " + 
					 Bytes.toString(CellUtil.cloneValue(cell))
					+ "  " + 
					 cell.getTimestamp()
					);
			System.out.println("---------------------------------------------");
		}
		
		System.out.println(result);
		table.close();
	}

	private static HTable getHTableByTableName(String tableName) throws IOException {
		//get instance of Default configuration
		Configuration conf = HBaseConfiguration.create();
		
		//
		HTable table = new HTable(conf, tableName);
		
		return table;
	}
}
