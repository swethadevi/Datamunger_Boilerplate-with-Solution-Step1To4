package com.stackroute.datamunger.reader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {
	private String fileName;

	/*
	 * parameterized constructor to initialize filename. As you are trying to
	 * perform file reading, hence you need to be ready to handle the IO Exceptions.
	 */
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {
		this.fileName = fileName;

		BufferedReader bufferedReader=new BufferedReader(new FileReader(fileName));

	}

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
	 * This method will be used in the upcoming assignments
	 */
	@Override
	public void getDataRow() {


	}

	/*
	 * implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. In
	 * the previous assignment, we have tried to convert a specific field value to
	 * Integer or Double. However, in this assignment, we are going to use Regular
	 * Expression to find the appropriate data type of a field. Integers: should
	 * contain only digits without decimal point Double: should contain digits as
	 * well as decimal point Date: Dates can be written in many formats in the CSV
	 * file. However, in this assignment,we will test for the following date
	 * formats('dd/mm/yyyy',
	 * 'mm/dd/yyyy','dd-mon-yy','dd-mon-yyyy','dd-month-yy','dd-month-yyyy','yyyy-mm-dd')
	 */
	@Override
	public DataTypeDefinitions getColumnType() throws IOException {
		// TODO Auto-generated method stub
		
		// checking for Integer
		
		// checking for floating point numbers
				
		// checking for date format dd/mm/yyyy
		
		// checking for date format mm/dd/yyyy
		
		// checking for date format dd-mon-yy
		
		// checking for date format dd-mon-yyyy
		
		// checking for date format dd-month-yy
		
		// checking for date format dd-month-yyyy
		
		// checking for date format yyyy-mm-dd
		
		//return null;
		File file=new File(fileName);

		FileReader fileReader = new FileReader(file);

		BufferedReader bufferedReader=new BufferedReader(fileReader);

		String HreaderString=bufferedReader.readLine();

		DataTypeDefinitions dataTypeDefinitions=new DataTypeDefinitions();

		String DataTypeString=bufferedReader.readLine();

		String[] dataValues=DataTypeString.split(",");

		List<String> list= new ArrayList<>();

		Pattern pattern = Pattern.compile("[0-9]+");

		Pattern pattern1 = Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$");

		for(int i=0;i<dataValues.length;i++)
		{
			if(pattern.matcher(dataValues[i]).matches())
			{
				list.add("java.lang.Integer");
			}
			else if(pattern1.matcher(dataValues[i]).matches())
			{
				list.add("java.util.Date");
			}
			else
				list.add("java.lang.String");
		}

		list.add("java.lang.Object");

		String[] stockArr = new String[list.size()];

		stockArr = list.toArray(stockArr);

		dataTypeDefinitions.setColumns(stockArr);

		return dataTypeDefinitions;
	}
	
	

	
	

}
