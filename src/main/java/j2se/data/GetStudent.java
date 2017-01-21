package j2se.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GetStudent {
	public static void main(String[] args) throws IOException {
		String input = "E:\\Workspaces\\resource\\TestData\\kv-bigdata10.txt";
		
		ArrayList<String> list = new ArrayList<String>();
		
		FileReader fr = new FileReader(new File(input));
		
		BufferedReader br = new BufferedReader(fr);
		
		String line = br.readLine();
		String name = null;
		
		Random random = new Random();
		
		int index = 1;
		for (int i = 1; i < 500; i++) {
			name = line.split("\t")[0];
		
			if(list.contains(name) == false){
				list.add(name);
				System.out.println(index + "\t" + name + "\t" + (random.nextInt(20) + 8));
				index++;
			}
			
			line = br.readLine();
		}
	}
}
