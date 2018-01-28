package com.bigdata.hive;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A User-defined function (UDF) for the use with Hive.
 *
 * New UDF classes need to inherit from this UDF class.
 *
 */

public class HiveUDFDate extends UDF {
	//31/Aug/2015:00:04:37 +0800
	private final SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
	
	private final SimpleDateFormat outputFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	
	/**
	 * Implement one or more methods named "evaluate" which will be called by Hive.
	 * "evaluate" should never be a void method. However it can return "null" if
	 */
	public Text evaluate(Text str){
		Text output = new Text();
		if(null == str){
			return null;
		}
		//validate
		if(StringUtils.isBlank(str.toString())){
			return null;
		}
		
		try {
			//parse
			Date parseDate = inputFormat.parse(str.toString());
			
			//transform
			String outputData = outputFormat.format(parseDate);
			
			//set
			output.set(outputData);
		} catch (Exception e) {
			e.printStackTrace();
			return output;
		}
		
		//lower
		return output;
	}
	
	//测试
	public static void main(String[] args) {
		System.out.println(new HiveUDFDate().evaluate(new Text("31/Aug/2015:00:04:37 +0800")));
	}
}
