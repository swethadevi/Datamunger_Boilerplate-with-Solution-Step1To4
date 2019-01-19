package com.stackroute.datamunger.query.parser;

import java.util.List;
import java.util.ArrayList;


/*
 * This class will contain the elements of the parsed Query String such as conditions,
 * logical operators,aggregate functions, file name, fields group by fields, order by
 * fields, Query Type
 * */

public class QueryParameter {

	private String QueryString="";
	private String fileName="";
	private String baseQuery="";
	private List<String> orderByFields;
	private List<String> groupByFields;
	private List<String> logicalOperators;
	private List<String> fields;
	private List<AggregateFunction> aggregateFunctions=new ArrayList<AggregateFunction>();
	private List<Restriction> RestrictionFunctions=new ArrayList<Restriction>();


	/*File name setter*/
	public void setFileName(String fileName){

		this.fileName=fileName;
	}
	/*file name setter*/
	public String getFileName() { return fileName;}

	/*Base query setter*/
	public void setBaseQuery(String baseQuery){
		this.baseQuery=baseQuery;
	}
	/*Base Query getter*/
	public String getBaseQuery() { return baseQuery; }

	/* Set fields */
	public void setFields(List<String> fields) {
		this.fields=fields;
	}

	/* get fields */
	public List<String> getFields() {
		return fields;
	}


	/*Group By Setter*/
	public void setGroupByFields(List<String> groupByFields){
		this.groupByFields=groupByFields;
	}

	/*Group By getter*/
	public List<String> getGroupByFields() { return groupByFields; }

	/*Order By setter*/
	public void setOrderByFields(List<String> orderByFields){
		this.orderByFields=orderByFields;
	}

	/*OrderBy getter*/
	public List<String> getOrderByFields() {
		return orderByFields;
	}

	/*Logical operator Setter*/
	public void setLogicalOperators(List<String> logicalOperators){
		this.logicalOperators=logicalOperators;
	}

	/*Logical operator getter*/
	public List<String> getLogicalOperators () { return  logicalOperators; }

	/*Aggregate function Setter*/
	public void setAggregateFunctions(List<AggregateFunction> aggregateFunctions)
	{
		this.aggregateFunctions=aggregateFunctions;
	}

	/* Aggregate function getter*/
	public List<AggregateFunction> getAggregateFunctions() {
		return aggregateFunctions;
	}

	/*Restriction function Setter */
	public void setRestrictions(List<Restriction> RestrictionFunctions){
		this.RestrictionFunctions=RestrictionFunctions;
	}
	/*Restriction function getter */
	public List<Restriction> getRestrictions() {
		return RestrictionFunctions;
	}

}