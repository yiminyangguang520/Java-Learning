package com.java.design.pattern.singleton;

// Thread-Safe Implementation
public class SingletonClassDemo3 {

	// Static attribute.
	private static SingletonClassDemo3 instance = null;

	// Private constructor.
	private SingletonClassDemo3() {

	}

	// Static function.
	// Only one thread can execute this at a time.
	public static synchronized SingletonClassDemo3 getInstance() {
		// If there is no instance available, create new one (i.e. lazy initialization).
		if (instance == null) {
			instance = new SingletonClassDemo3();
		}
		return instance;
	}
}