package j2se.data;

import java.io.*;
import java.util.Random;

public class GetData {
	public static void main(String[] args) {
		Random random = new Random();
		String[] keys = keys();
		
		
		try {
			File file = new File("E:\\Workspaces\\resource\\TestData\\kv-bigdata100.txt");
			if(file.exists() == false){
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file);
			
			BufferedWriter bw = new BufferedWriter(fw);
			
			for (int i = 0; i < 1000000; i++) {
				bw.write(keys[random.nextInt(120)] + "\t" + (random.nextInt(9000) + 1000) + "\r\n");
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private static void getData() {
		Random random = new Random();
		for (int i = 0; i < 210; i++) {
			String year = (random.nextInt(30) + 1970) + "-";
			String manth = "";
			int temp = random.nextInt(12);
			if(temp < 10){
				manth = "0" + temp + "-";
			}else{
				manth = temp + "-";
			}
			String date = "";
			temp = random.nextInt(30);
			if(temp < 10){
				date = "0" + temp;
			}else{
				date = temp + "";
			}
			System.out.println(year + manth + date);
		}
	}
	
	public static String[] keys(){
		Random random = new Random();
		
		String[] keys = new String[120];
		
		try {
			File file = new File("E:\\Workspaces\\resource\\TestData\\bigdatakey.txt");
			
			if(file.exists() == false){
				System.out.println("bigdata.txt文件不存在");
				return null;
			}
			InputStreamReader input = new InputStreamReader(new FileInputStream(file));
			
			BufferedReader br = new BufferedReader(input);
			
			String line = null;
			int index = 0;
			while((line = br.readLine()) != null){
				keys[index] = line;
				index++;
			}
			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*System.out.print("[");
		for (int i = 0; i < keys.length; i++) {
			if(i + 1 ==  keys.length)
				System.out.print(keys[i]);
			else
				System.out.print(keys[i] + ", ");
		}
		System.out.print("]");*/
		
		return keys;
	}
	
}
