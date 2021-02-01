package com.revature.gc;

import java.util.LinkedList;
import java.util.List;

public class GarbageCollecting {

	public static void main(String[] args) {
		//noGarbageCollection();
		garbageCollection();
	}
	
	private static void noGarbageCollection() {
		List<Garbage> garbage = new LinkedList<Garbage>();
		int i = 0;
		while (true) {
			garbage.add(new Garbage((i++)+""));
			System.out.println(i);
		}
	}
	
	private static void garbageCollection() {
		Garbage g = null;
		for (int i = 0; i < 1000000; i++) {
			g = new Garbage(i+"");
			//System.gc(); // encourages the garbage collector to do its job
		}
		System.out.println("kept a reference to: " + g);
	}

}
