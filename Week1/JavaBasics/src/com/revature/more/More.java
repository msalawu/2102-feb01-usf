package com.revature.more;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.revature.basics.Cheddar;
import com.revature.basics.Cheese;
import com.revature.basics.Mozzarella;
import com.revature.gc.Garbage;

public class More {

	public static void main(String[] args) {
		System.out.print("hello ");
		//threadsExample();
		lambdas();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print("world");
	}
	
	private static void lambdas() {
		Runnable runnableLambda = () -> {
			System.out.println("this is a thread created by a lambda!");
		};
		
		Thread lambdaThread = new Thread(runnableLambda);
		lambdaThread.start();
		
		Pettable dog = (name) -> {
			System.out.println(name + " the dog enjoys the pet.");
		};
		
		dog.pet("Doug");
		dog.adopt();
		
		Predicate<Pettable> petTest = (pet) -> {
			pet.pet("name");
			return true;
		};
		
		petTest.test(dog);
		
		Predicate<Integer> lessThanTen = (n) -> n < 10;
		
		System.out.println(lessThanTen.test(5));
		System.out.println(lessThanTen.test(15));
		
		Predicate<Integer> greaterThanFive = (n) -> n > 5;
		
		System.out.println(lessThanTen.and(greaterThanFive).test(8));
		System.out.println(lessThanTen.and(greaterThanFive).test(2));
		
		Supplier<Garbage> garbageSupplier = () -> new Garbage("fresh");
		Garbage g = garbageSupplier.get();
		
		Consumer<Cheese> cheeseConsumer = (cheese) -> {
			cheese.eatCheese();
		};
		
		cheeseConsumer.accept(new Mozzarella());
		cheeseConsumer.accept(new Cheddar());
	}
	
	private static void threadsExample() {
		Thread runnableThread = new Thread(new MyRunnableThread());
		System.out.println(runnableThread.getState());
		runnableThread.start();
		System.out.println(runnableThread.getState());
		
		//System.out.println(runnableThread.isAlive());
		
		Thread threadThread = new MyThreadThread();
		threadThread.start();
		
		try {
			runnableThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Thread runnableThread2 = new Thread(new MyRunnableThread());
		runnableThread2.start();
		
		//System.out.println(runnableThread.isAlive());
		System.out.println(runnableThread.getState());
		
		//Thread threadThread = new MyThreadThread();
		//System.out.println(threadThread.getState());
		//threadThread.start();
		//System.out.println(threadThread.getState());
	}

}

class MyRunnableThread implements Runnable {

	@Override
	public void run() {
		System.out.println("this is a runnable thread");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end of the runnable thread");
	}
	
}

class MyThreadThread extends Thread {
	
	@Override
	public void run() {
		System.out.println("this is a thread thread");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end of the thread thread");
	}
	
}

// this does the same thing as line 61
//class CheeseConsumer implements Consumer<Cheese> {
//
//	@Override
//	public void accept(Cheese cheese) {
//		cheese.eatCheese();
//	}
//	
//}