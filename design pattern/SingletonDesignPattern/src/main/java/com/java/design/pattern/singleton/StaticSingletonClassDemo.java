package com.java.design.pattern.singleton;

// Static Initialization
public class StaticSingletonClassDemo {

	// Static attribute.
	private static StaticSingletonClassDemo instance;

	// Private constructor.
	private StaticSingletonClassDemo() {

	}

	// Static block initialization for exception handling.
	static {
		try {
			instance = new StaticSingletonClassDemo();
		} catch(Exception ex) {
			throw new RuntimeException("Exception occurred in creating the singleton instance ...!");
		}
	}

	public static StaticSingletonClassDemo getInstance() {
		return instance;
	}
}