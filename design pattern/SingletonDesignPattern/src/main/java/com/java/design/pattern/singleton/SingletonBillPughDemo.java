package com.java.design.pattern.singleton;

public class SingletonBillPughDemo {

	// Private constructor.
	private SingletonBillPughDemo() {

	}

	// Static inner class.
	private static class Lazyholder {
		// Static attribute.
		private static final SingletonBillPughDemo INSTANCE = new SingletonBillPughDemo();
	}

	// Static function.
	public static SingletonBillPughDemo getInstance() {
		return Lazyholder.INSTANCE;
	}
}