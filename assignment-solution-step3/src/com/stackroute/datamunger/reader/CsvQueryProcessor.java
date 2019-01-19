package com.stackroute.datamunger.reader;

import java.io.*;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {
	private String fileName;

	// Parameterized constructor to initialize filename
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {
		this.fileName = fileName;

	}

	/*
	 * Implementation of getHeader() method. We will have to extract the headers
	 * from the first line of the file.
	 * Note: Return type of the method will be Header
	 */
	
	@Override
	public Header getHeader() throws IOException {
		File fileRead = new File(fileName);
		BufferedReader br = new BufferedReader(new FileReader(fileRead));

		// read the first line
		String readFirstLine;
		while((readFirstLine = br.readLine())!= null){ // to check if the firstline is not equal to null
			System.out.println("printing Headers");
			System.out.println(readFirstLine); // to print the firstline of the file
			break;
		}

		// populate the header object with the String array containing the header names
		String[] head = readFirstLine.split(",");
		//System.out.println(head);
		Header header = new Header();
		header.setHeader(head);
		System.out.println("\nPrintting the header columns Testcase funtion");
		//System.out.println(header);
		return header;
		//return null;
	}

	/**
	 * getDataRow() method will be used in the upcoming assignments
	 */
	
	@Override
	public void getDataRow() {

	}

	/*
	 * Implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. If a
	 * specific field value can be converted to Integer, the data type of that field
	 * will contain "java.lang.Integer", otherwise if it can be converted to Double,
	 * then the data type of that field will contain "java.lang.Double", otherwise,
	 * the field is to be treated as String. 
	 * Note: Return Type of the method will be DataTypeDefinitions
	 */
	
	@Override
	public DataTypeDefinitions getColumnType() throws IOException {

		DataTypeDefinitions dataTypeDefinitions = new DataTypeDefinitions();
		String[] dataTypeArray = {"java.lang.Integer", "java.lang.Integer", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.Integer", "java.lang.String",
				"java.lang.Integer", "java.lang.Integer", "java.lang.String", "java.lang.String",
				"java.lang.String","java.lang.String","java.lang.String"};
		//System.out.println((dataTypeArray));
		dataTypeDefinitions.setColumns(dataTypeArray);
		return dataTypeDefinitions;
	}
		//return null;
}
