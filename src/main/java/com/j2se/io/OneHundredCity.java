package com.j2se.io;

import java.io.*;

public class OneHundredCity {
	public static void main(String[] args) throws IOException {
		String input = "E:\\Workspaces\\resource\\TestData\\OneHundredCityOfChina.txt";
		String output = "E:\\Workspaces\\resource\\TestData\\outputCity.txt";
		
		FileReader reader = new FileReader(new File(input));
		FileWriter writer = new FileWriter(new File(output));
		
		BufferedReader br = new BufferedReader(reader);
		BufferedWriter bw = new BufferedWriter(writer);
		
		String line = br.readLine();
		
		while(line != null){
			
			line = br.readLine();
		}
		
		
	}
}
