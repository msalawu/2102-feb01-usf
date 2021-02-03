package com.revature.gc;

public class GarbageCollecting {
	public static void main(String[] args) {
		garbageCollection();
	}
	
	private static void garbageCollection() {
		Garbage g = null;
		for (int i = 0; i < 250000; i++) {
			g = new Garbage(i+"");
			//System.gc();
		}
		System.out.println("still have a reference to: " + g);
	}
}
