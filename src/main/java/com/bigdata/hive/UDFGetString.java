package com.bigdata.hive;


import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

/**
 * A User-defined function (UDF) for the use with Hive.
 *
 * New UDF classes need to inherit from this UDF class.
 *
 */

public class UDFGetString extends UDF {
	
	/**
	 * Implement one or more methods named "evaluate" which will be called by Hive.
	 * "evaluate" should never be a void method. However it can return "null" if
	 */
	public Text evaluate(Text str){
		
		if(null == str){
			return null;
		}
		//validate
		if(StringUtils.isBlank(str.toString())){
			return null;
		}
		
		String[] vals = str.toString().split("\\.");
		
		if(4 != vals.length){
			return null;
		}
		
		//lower
		return new Text(vals[0] + "." + vals[1]);
	}
	
	//测试
	public static void main(String[] args) {
		System.out.println(new UDFGetString().evaluate(new Text("192.168.1.106")));
	}
}
