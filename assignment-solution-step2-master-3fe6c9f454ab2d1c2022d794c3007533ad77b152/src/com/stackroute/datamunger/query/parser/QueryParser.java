package com.stackroute.datamunger.query.parser;

/*There are total 4 DataMungerTest file:
 *
 * 1)DataMungerTestTask1.java file is for testing following 4 methods
 * a)getBaseQuery()  b)getFileName()  c)getOrderByClause()  d)getGroupByFields()
 *
 * Once you implement the above 4 methods,run DataMungerTestTask1.java
 *
 * 2)DataMungerTestTask2.java file is for testing following 2 methods
 * a)getFields() b) getAggregateFunctions()
 *
 * Once you implement the above 2 methods,run DataMungerTestTask2.java
 *
 * 3)DataMungerTestTask3.java file is for testing following 2 methods
 * a)getRestrictions()  b)getLogicalOperators()
 *
 * Once you implement the above 2 methods,run DataMungerTestTask3.java
 *
 * Once you implement all the methods run DataMungerTest.java.This test case consist of all
 * the test cases together.
 */

import java.util.ArrayList;
import java.util.List;

public class QueryParser {

	private QueryParameter queryParameter = new QueryParameter();

	/*
	 * This method will parse the queryString and will return the object of
	 * QueryParameter class
	 */
	public QueryParameter parseQuery(String queryString) {
		queryParameter.setFileName(getFile(queryString));
		queryParameter.setBaseQuery(getBase(queryString));
		queryParameter.setOrderByFields(orderByFields(queryString));
		queryParameter.setGroupByFields(groupByFields(queryString));
		queryParameter.setLogicalOperators(getLogicalOperators(queryString));
		queryParameter.setFields(fieldsExtraction(queryString));
		queryParameter.setAggregateFunctions(aggregateFunctionsExtractor(queryString));
		queryParameter.setRestrictions(RestrictionFunctionsExtractor(queryString));


		return queryParameter;
	}

	/*
	 * Extract the name of the file from the query. File name can be found after the
	 * "from" clause.
	 */
	public String getFile(String queryString) {
		String queryLowerCase = queryString.toLowerCase(); //to convert the original string to LowerCase
		String[] result_String = queryLowerCase.split("from"); //to split til from to get the file name
		String[] result_String1 = result_String[1].split(" "); //to split and extract the first word from the second array
		String result = result_String1[1]; //return type is String, store the array[] to a String variable and return the String
		return result;
	}

	/*
	 *
	 * Extract the baseQuery from the query.This method is used to extract the
	 * baseQuery from the query string. BaseQuery contains from the beginning of the
	 * query till the where clause
	 */

	public String getBase(String queryString) {
		String[] splitString = queryString.split(" where | group by "); //to split the given array into where and group by
		return splitString[0]; // return the splitted new array

	}

	/*
	 * extract the order by fields from the query string. Please note that we will
	 * need to extract the field(s) after "order by" clause in the query, if at all
	 * the order by clause exists. For eg: select city,winner,team1,team2 from
	 * data/ipl.csv order by city from the query mentioned above, we need to extract
	 * "city". Please note that we can have more than one order by fields.
	 */
	public List<String> orderByFields(String queryString) {
		String conditionQuery[] = queryString.toLowerCase().split("from");
		int countOrderBy = conditionQuery[1].trim().indexOf(" order by ");
		//	System.out.println(conditionQuery[1].trim());

		if (countOrderBy == -1)
			return null;
		String splitedCondition[] = conditionQuery[1].trim().split(" order by ");
		String orderByFields[] = splitedCondition[1].trim().split(",");

		List<String> splitLst = new ArrayList<>();
		for (int i = 0; i < orderByFields.length; i++) {
			splitLst.add(orderByFields[i].trim());
		}
		return splitLst;
	}

	/*
	 * Extract the group by fields from the query string. Please note that we will
	 * need to extract the field(s) after "group by" clause in the query, if at all
	 * the group by clause exists. For eg: select city,max(win_by_runs) from
	 * data/ipl.csv group by city from the query mentioned above, we need to extract
	 * "city". Please note that we can have more than one group by fields.
	 */
	public List<String> groupByFields(String queryString) {
		String conditionQuery[] = queryString.toLowerCase().split(" from ");

		int countGroupBy = conditionQuery[1].trim().indexOf(" group by ");
		//	System.out.println(conditionQuery[1].trim());

		if (countGroupBy == -1)
			return null;
		String splitedCondition[] = conditionQuery[1].trim().split(" group by ");

		String splitOrderBy[] = splitedCondition[1].trim().split(" order by ");

		String groupByFields[] = splitOrderBy[0].trim().split(",");

		List<String> splitLst = new ArrayList<>();
		for (int i = 0; i < groupByFields.length; i++) {
			splitLst.add(groupByFields[i].trim());
		}
		return splitLst;
	}



	/*
	 * Extract the selected fields from the query string. Please note that we will
	 * need to extract the field(s) after "select" clause followed by a space from
	 * the query string. For eg: select city,win_by_runs from data/ipl.csv from the
	 * query mentioned above, we need to extract "city" and "win_by_runs". Please
	 * note that we might have a field containing name "from_date" or "from_hrs".
	 * Hence, consider this while parsing.
	 */

	//Get fields Extraction
	public List<String> fieldsExtraction(String queryString) {

		queryString = queryString.toLowerCase();
		String[] spiltFrom = queryString.split(" from"); // to split the array with from
		String[] splitSelect = spiltFrom[0].split("select "); // to split the array with select
		String[] resultString = splitSelect[1].split(","); // to split the array with (,)

		List<String> fieldsList = new ArrayList<String>(); //to create a new arraylist
		for (int i = 0; i < resultString.length; i++) // to loop through the resultString array
			fieldsList.add(resultString[i]); //add the array to the list

		return fieldsList; //to return the fieldlist array
	}


	/*
	 * Extract the conditions from the query string(if exists). for each condition,
	 * we need to capture the following: 1. Name of field 2. condition 3. value
	 *
	 * For eg: select city,winner,team1,team2,player_of_match from data/ipl.csv
	 * where season >= 2008 or toss_decision != bat
	 *
	 * here, for the first condition, "season>=2008" we need to capture: 1. Name of
	 * field: season 2. condition: >= 3. value: 2008
	 *
	 * the query might contain multiple conditions separated by OR/AND operators.
	 * Please consider this while parsing the conditions.
	 *
	 */

	public String ConditionsPartQueryExtractor(String queryString) {
		if(queryString.contains(" where ")) {  // to check if query string contains 'where'
			//queryString = queryString.toLowerCase();
			String[] trimFrom = queryString.split("where ");
			queryString=trimFrom[1];
			String[] splitOrderby = queryString.split(" order by | group by ");
			queryString = splitOrderby[0];
			return queryString;
		}
		else
			return queryString;
	}


	public List<Restriction> RestrictionFunctionsExtractor(String queryString) {
		List<Restriction> list= new ArrayList<Restriction>();
		if(queryString.contains("where")) {
			queryString = ConditionsPartQueryExtractor(queryString);
			String[] trimOrderby = queryString.split(" order by | group by ");
			queryString = trimOrderby[0];
			String[] finalStr = {queryString};
			//System.out.println(queryString);
			if (queryString.contains("and") || queryString.contains("or")) ;
			{
				String strLeft, strRight;

				finalStr = queryString.split(" and | or ");

				for (int i = 0; i < finalStr.length; i++) {
					if (finalStr[i].contains("=")) {
						String[] tokens1 = finalStr[i].split("=");
						Restriction res1 = new Restriction(tokens1[0].trim().replaceAll("'", ""), tokens1[1].trim().replaceAll("'", ""), "=");

						list.add(res1);

					} else {
						String[] tokens1 = finalStr[i].split(" ");
						Restriction res1 = new Restriction(tokens1[0].trim().replaceAll("'", ""), tokens1[2].trim().replaceAll("'", ""), tokens1[1].trim().replaceAll("'", ""));

						list.add(res1);
					}

				}

			}

			return list;
		}
		else
			return null;
	}


	/*
	 * Extract the logical operators(AND/OR) from the query, if at all it is
	 * present. For eg: select city,winner,team1,team2,player_of_match from
	 * data/ipl.csv where season >= 2008 or toss_decision != bat and city =
	 * bangalore
	 *
	 * The query mentioned above in the example should return a List of Strings
	 * containing [or,and]
	 */


	public List<String> getLogicalOperators(String queryString) {
//		String arrOfStr[] = queryString.split(" ");//Split the array with a space (' ')
//		int i = 0;
//		List<String> list = new ArrayList<String>(); // to create  a new list of String
//		if (queryString.contains(" and ") || queryString.contains(" or ") || queryString.contains(" not ")) { //to check if contains the logical operators
//			for (String a : arrOfStr) //to loop through the array of string
//			{
//				if (a.equals("and")) { //to check if the array string contains 'and'
//					list.add("and"); // if found add it to the list
//				} else if (a.equals("or")) {  //to check if the array string contains 'or'
//					list.add("or");  // if found add it to the list
//				} else if (a.equals("not")) {
//					{  //to check if the array string contains 'not'
//						list.add("and"); // if found add it to the lists
//					}
//				}
//
//				return list; //return the final list
//			}
//			return list;//return the final list
//		}
//		return list;
		if(queryString.contains("where"))
		{
			List<String> finalResList=new ArrayList<String>();

			queryString = ConditionsPartQueryExtractor(queryString);
			String[] strArr=queryString.split(" ");
			for(int i=0;i<strArr.length;i++) {
				if (strArr[i].equals("and")) {
					finalResList.add("and");
				}
				else if (strArr[i].equals("or")) {
					finalResList.add("or");
				}
			}
			return finalResList;
		}
		else
			return null;

	}


	/*
	 * Extract the aggregate functions from the query. The presence of the aggregate
	 * functions can determined if we have either "min" or "max" or "sum" or "count"
	 * or "avg" followed by opening braces"(" after "select" clause in the query
	 * string. in case it is present, then we will have to extract the same. For
	 * each aggregate functions, we need to know the following: 1. type of aggregate
	 * function(min/max/count/sum/avg) 2. field on which the aggregate function is
	 * being applied.
	 *
	 * Please note that more than one aggregate function can be present in a query.
	 *
	 *
	 */
	public List<AggregateFunction> aggregateFunctionsExtractor(String queryString) {
		List<AggregateFunction> aggregateList = new ArrayList<AggregateFunction>();

		String[] trimSelect = queryString.split("select ");
		String[] trimFrom = trimSelect[1].split(" from");
		String[] trimAggregate = trimFrom[0].split(",");
		ArrayList list = new ArrayList();
		int k = 0;
		for (int i = 0; i < trimAggregate.length; i++) { // to loop through the Aggregate Array

			if (trimAggregate[i].contains("sum(") || trimAggregate[i].contains("count(") || trimAggregate[i].contains("min(") || trimAggregate[i].contains("max(") || trimAggregate[i].contains("avg(")) { // check the condition if it has sum,min,max,avg,count
				String[] trimLeft = trimAggregate[i].split("\\(");
				if (trimLeft[0].contains("sum")) {

					String[] trimRight = trimLeft[1].split("\\)");
					AggregateFunction obj = new AggregateFunction(trimRight[0], "sum");
					aggregateList.add(obj);
				} else if (trimLeft[0].contains("count")) {
					String[] trimRight = trimLeft[1].split("\\)");
					AggregateFunction obj = new AggregateFunction(trimRight[0], "count");
					aggregateList.add(obj);
				} else if (trimLeft[0].contains("min")) {
					String[] trimRight = trimLeft[1].split("\\)");
					AggregateFunction obj = new AggregateFunction(trimRight[0], "min");
					aggregateList.add(obj);
				} else if (trimLeft[0].contains("max")) {
					String[] trimRight = trimLeft[1].split("\\)");
					AggregateFunction obj = new AggregateFunction(trimRight[0], "max");
					aggregateList.add(obj);
				} else if (trimLeft[0].contains("avg")) {
					String[] trimRight = trimLeft[1].split("\\)");
					AggregateFunction obj = new AggregateFunction(trimRight[0], "avg");
					aggregateList.add(obj);
				}
			}

		}

		return aggregateList;

	}



}


