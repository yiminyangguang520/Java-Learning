package com.java.design.pattern.singleton;

// Double Check Locking Principle
public class SingletonClassDemo5 {

	// Static and Volatile attribute.
	private static volatile SingletonClassDemo5 instance = null;

	// Private constructor.
	private SingletonClassDemo5() {

	}

	// Static function.
	public static SingletonClassDemo5 getInstance() {
		// Double check locking principle.
		// If there is no instance available, create new one (i.e. lazy initialization).
		if (instance == null) {

			// To provide thread-safe implementation.
			synchronized(SingletonClassDemo5.class) {

				// Check again as multiple threads can reach above step.
				if (instance == null) {
					instance = new SingletonClassDemo5();
				}
			}
		}
		return instance;
	}
}