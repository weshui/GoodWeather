package edu.illinois.cs425_mp1.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import edu.illinois.cs425_mp1.adapter.Adapter;

/**
 * Created by Wesley on 8/30/15.
 */
public class Console {
	
	private Adapter adapter = new Adapter(6753);
	
	public static void main(String[] args) {
		String line = null;
		while(true) {
			System.out.println("Choose the num of operation:");
			System.out.println("1. Query log. 0. Exit");
			line = read();
			int num = parseNum(line);
			switch(num) {
			case -1: System.out.println("Please enter a valid number.");
					continue;
			case 0: System.exit(0);
			case 1: logMenu();
					break;
			default: System.out.println("Invalid option");
					continue;
			}
			line = read();
			num = parseNum(line);
			
		}
	}
	
	private static String read() {
		try {
			BufferedReader br = 
                      new BufferedReader(new InputStreamReader(System.in));
			String input;
			if((input=br.readLine())!=null){
				return input;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Error";
	}
	
	private static int parseNum(String line) {
		try {
			int num = Integer.valueOf(line);
			return num;
		} catch (Exception e) {
			System.out.println("Invalid num");
			return -1;
		}
	}

	private static int logMenu() {
		System.out.println("Please enter the num of query:");
		System.out.println("1. grep 0. Return to main menu");
		String line = read();
		int num = parseNum(line);
		switch(num){
			case -1: System.out.println("Please enter a valid number");
					return logMenu();
			case 0: return 0;
			case 1: System.out.println("Please enter the argument after grep:");
					System.out.println("For example: \"-c blah\", no need to specify file name");
					line = read();
					//Unfinished
					return 0;
		}
	}
}
