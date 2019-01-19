package com.stackroute.datamunger.query.parser;

/* This class is used for storing name of field, aggregate function for 
 * each aggregate function
 * generate getter and setter for this class,
 * Also override toString method
 * */


import java.util.ArrayList;
import java.util.List;

public class AggregateFunction {
	String field="";
	String functions="";
	// Write logic for constructor
	public AggregateFunction(String field, String function) {
		this.field=field;
		this.functions=function;
	}

	@Override
	public String toString() {
		return "AggregateFunction{" +
				"field='" + field + '\'' +
				", functions='" + functions + '\'' +
				'}';
	}
}

