package com.stackroute.datamunger.query.parser;

/*
 * This class is used for storing name of field, condition and value for 
 * each conditions
 * generate getter and setter for this class,
 * Also override toString method
 * */


public class Restriction {
	String name;
	String value;
	String condition;
	// Write logic for constructor
	public Restriction(String name, String value, String condition) {
		this.name=name;
		this.value=value;
		this.condition=condition;
	}

	@Override
	public String toString() {
		return "Restriction{" +
				"name='" + name + '\'' +
				", value='" + value + '\'' +
				", condition='" + condition + '\'' +
				'}';
	}
}