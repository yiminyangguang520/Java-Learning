package com.java.design.pattern.singleton;

// Eager Initialization
public class SingletonClassDemo2 {

	// Static attribute.
	// Making the "instance" attribute as "final" ensures that only one instance of the class exists.
	private static SingletonClassDemo2 instance = new SingletonClassDemo2();

	// Private constructor.
	private SingletonClassDemo2() {

	}

	// Static function.
	public static SingletonClassDemo2 getInstance() {
		return instance;
	}
}