package com.revature.thursday;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;

import com.revature.basics.Bean;
import com.revature.gc.Garbage;

public class ExampleApp {

	public static void main(String[] args) {
		generics();
		maps();
		//scanners();
		lambdas();
		
		//threads();
		factoryDesignPattern();
	}
	
	private static void generics() {
		// using generics
		Chef<Bean> beanChef = new Chef<>();
		Bean b = new Bean("kidney", "red", 4, false);
		beanChef.cookFood(b);
		System.out.println(b);
		
		Chef<Garbage> gourmetChef = new Chef<>();
		Garbage g = new Garbage("fresh");
		gourmetChef.cookFood(g);
			
		Chef<Object> objectChef = new Chef<>();
		objectChef.cookFood(new ExampleApp());
				
		
		List<Bean> beanList = new ArrayList<>();
	}
	
	private static void maps() {
		// maps
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "hello");
		map.put(2, "world");
		map.put(2, "!");
		
		Set<Integer> keySet = map.keySet();
		
		for (Integer i : keySet) {
			System.out.println(map.get(i));
		}
	}
	
	private static void scanners() {
		// you only ever want to have one scanner input
		Scanner scan = ScannerSingleton.getScannerSingleton().getScanner();
		
		String str = scan.next();
		String str2 = scan.nextLine();
		Integer num = scan.nextInt();
		Integer num3 = Integer.valueOf(scan.nextLine());
		Double num2 = scan.nextDouble();
		
		System.out.println(str + ", " + str2 + ", " + num + ", " + num2);
		
		// always make sure that you close your scanner when you're done with it
		// (at the end of the application, generally)
		scan.close();
	}
	
	private static void lambdas() {
		PetFunction<Dragon> petDragon = (d) -> {
			System.out.println("petting the dragon, " + d.name);
		};
		
		//PetDragon petDragonClass = new PetDragon();
		
		Dragon d = new Dragon("Dino");
		Dragon d2 = new Dragon("Toothless");
		
		petDragon.pet(d);
		petDragon.pet(d2);
		
//		Predicate<Integer> predicate = (i) -> {
//			return i > 10;
//		};
		
		Predicate<Integer> predicate = (i) -> i > 10;
		
		System.out.println(predicate.test(5));
	}
	
	private static void threads() {
		Thread runnableThread = new Thread(new MyRunnableThread());
		
		System.out.println(runnableThread.getState());
		// the start method on Thread allows us to call the run
		// method and have it run simultaneously to other threads
		runnableThread.start();
		System.out.println(runnableThread.getState());
		
		try {
			Thread.sleep(2500);
			System.out.println(Thread.currentThread().getState());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(runnableThread.getState());
		
		try {
			// waits for the thread
			runnableThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(runnableThread.getState());
		
		Thread threadThread = new MyThreadThread();
		
		threadThread.start();
//		try {
//			threadThread.wait();
//			System.out.println(threadThread.getState());
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		threadThread.notify();
//		System.out.println(threadThread.getState());
	}
	
	private static void factoryDesignPattern() {
		PettableFactory factory = new PettableFactory();
		Random rand = new Random();
		Pettable pet = factory.getPettable(rand.nextInt(3));
		System.out.println(pet.pet());
	}

}

interface PetFunction<T> {
	public void pet(T t);
}

// this does the same thing as the lambda
//class PetDragon implements PetFunction<Dragon> {
//	public void pet(Dragon d) {
//		System.out.println("petting the dragon, " + d.name);
//	}
//}

class MyRunnableThread implements Runnable {
	public void run() {
		System.out.println("A runnable thread!");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("The runnable thread is over.");
	}
}

class MyThreadThread extends Thread {
	// extending Thread means that you could override the .start() method,
	// but doing so incorrectly can break your multithreading
	public void run() {
		System.out.println("A thread thread!");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("The thread thread is over.");
	}
}
