package com.java.design.pattern.singleton;

// Double Check Locking Principle
public class SingletonClassDemo4 {

	// Static attribute.
	private static SingletonClassDemo4 instance = null;

	// Private constructor.
	private SingletonClassDemo4() {

	}

	// Static function.
	public static SingletonClassDemo4 getInstance() {
		// Double check locking principle.
		// If there is no instance available, create new one (i.e. lazy initialization).
		if (instance == null) {

			// To provide thread-safe implementation.
			synchronized(SingletonClassDemo4.class) {

				// Check again as multiple threads can reach above step.
				if (instance == null) {
					instance = new SingletonClassDemo4();
				}
			}
		}
		return instance;
	}
}