package com.stackroute.datamunger.query;

//header class
public class Header {

	/*
	 * This class should contain a member variable which is a String array, to hold
	 * the headers.
	 */
	public String[] header;

	public Header(){

	}

	//Setter method for header
	public void setHeader(String[] header ){
		this.header = header;
	}

	//Getter method for header
	public String[] getHeaders()
	{
		return header;
	}

}
