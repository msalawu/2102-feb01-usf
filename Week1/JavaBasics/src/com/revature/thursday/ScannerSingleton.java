package com.revature.thursday;

import java.util.Scanner;

public class ScannerSingleton {
	// private static object
	private static ScannerSingleton scannerSingleton;
	private Scanner scan;
	
	// private constructor
	private ScannerSingleton() {
		scan = new Scanner(System.in);
	}
	
	// public static synchronized accessor method
	public static synchronized ScannerSingleton getScannerSingleton() {
		if (scannerSingleton == null)
			scannerSingleton = new ScannerSingleton();
		return scannerSingleton;
	}
	
	public Scanner getScanner() {
		return scan;
	}
	
	public void close() {
		scan.close();
	}
}
