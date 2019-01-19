package com.stackroute.datamunger.query;

public class DataTypeDefinitions {

	/*
	 * This class should contain a member variable which is a String array, to hold
	 * the data type for all columns for all data types
	 */
	private String[] columns;

	public DataTypeDefinitions(){

	}

	//Setter method for Columns
	public void setColumns(String[] columns){
		this.columns = columns;
	}

	//Getter method for DataTypes
	public String[] getDataTypes() {
		return columns;
	}
}
