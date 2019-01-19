package com.stackroute.datamunger;

/*There are total 5 DataMungertest files:
 * 
 * 1)DataMungerTestTask1.java file is for testing following 3 methods
 * a)getSplitStrings()  b) getFileName()  c) getBaseQuery()
 * 
 * Once you implement the above 3 methods,run DataMungerTestTask1.java
 * 
 * 2)DataMungerTestTask2.java file is for testing following 3 methods
 * a)getFields() b) getConditionsPartQuery() c) getConditions()
 * 
 * Once you implement the above 3 methods,run DataMungerTestTask2.java
 * 
 * 3)DataMungerTestTask3.java file is for testing following 2 methods
 * a)getLogicalOperators() b) getOrderByFields()
 * 
 * Once you implement the above 2 methods,run DataMungerTestTask3.java
 * 
 * 4)DataMungerTestTask4.java file is for testing following 2 methods
 * a)getGroupByFields()  b) getAggregateFunctions()
 * 
 * Once you implement the above 2 methods,run DataMungerTestTask4.java
 * 
 * Once you implement all the methods run DataMungerTest.java.This test case consist of all
 * the test cases together.
 */

import java.util.*;
import java.util.regex.*;
public class DataMunger {

    /*
     * This method will split the query string based on space into an array of words
     * and display it on console
     */

    public String[] getSplitStrings(String queryString) {
        String queryLowerCase = queryString.toLowerCase();//to convert the original string to LowerCase
        String[] result_String = queryLowerCase.split("\\s+"); //split the query string based on space into an array of words
        return result_String; // the splitted  new array

    }

    /*
     * Extract the name of the file from the query. File name can be found after a
     * space after "from" clause. Note: ----- CSV file can contain a field that
     * contains from as a part of the column name. For eg: from_date,from_hrs etc.
     *
     * Please consider this while extracting the file name in this method.
     */

    public String getFileName(String queryString) {
        String queryLowerCase = queryString.toLowerCase(); //to convert the original string to LowerCase
        String[] result_String = queryLowerCase.split("from"); //to split til from to get the file name
        String[] result_String1 = result_String[1].split(" "); //to split and extract the first word from the second array
        String result = result_String1[1]; //return type is String, store the array[] to a String variable and return the String
        return result;


        //Pattern pattern_check = Pattern.compile("ipl.csv");
        //Matcher matcher_check = pattern_check.matcher("ipl.csv");
        //return String.valueOf(matcher_check);

    }

    //return null;

    /*
     * This method is used to extract the baseQuery from the query string. BaseQuery
     * contains from the beginning of the query till the where clause
     *
     * Note: ------- 1. The query might not contain where clause but contain order
     * by or group by clause 2. The query might not contain where, order by or group
     * by clause 3. The query might not contain where, but can contain both group by
     * and order by clause
     */

    public String getBaseQuery(String queryString) {
//        String queryLowerCase = queryString.toLowerCase();//to convert the original string to LowerCase
//        String[] result_String = queryLowerCase.split(" where"); //to extract from the beginning of the query till the where clause
//        String result = result_String[0]; // return type is String store the array[] to a String variable and return the String
//        return result;
        String[] splitString = queryString.split(" where | group by "); //to split the given array into where and group by
        return splitString[0]; // return the splitted new array

        //return null;
    }

    /*
     * This method will extract the fields to be selected from the query string. The
     * query string can have multiple fields separated by comma. The extracted
     * fields will be stored in a String array which is to be printed in console as
     * well as to be returned by the method
     *
     * Note: 1. The field name or value in the condition can contain keywords
     * as a substring. For eg: from_city,job_order_no,group_no etc. 2. The field
     * name can contain '*'
     *
     */

    public String[] getFields(String queryString) {
        String queryLowerCase = queryString.toLowerCase();
        String[] result_String = queryLowerCase.split("  from ");
        String[] result_String1 = result_String[0].split(" ");
        String[] result_String2 = result_String1[1].split(",");
        //String result = result_String2[0];
        return result_String2;

        //return null;
    }

    /*
     * This method is used to extract the conditions part from the query string. The
     * conditions part contains starting from where keyword till the next keyword,
     * which is either group by or order by clause. In case of absence of both group
     * by and order by clause, it will contain till the end of the query string.
     * Note:  1. The field name or value in the condition can contain keywords
     * as a substring. For eg: from_city,job_order_no,group_no etc. 2. The query
     * might not contain where clause at all.
     */

    public String getConditionsPartQuery(String queryString) {
//        String queryLowerCase = queryString.toLowerCase();
//        String[] result_String = queryLowerCase.split(" where "); //splitting the array with first condition til where
//        String[] result_String1 = result_String[1].split(" where ");// after where store it to the second array
//        String result = result_String1[0];
//        return result;
        String queryLowerCase = queryString.toLowerCase();
        if(queryLowerCase.contains("where ")) {
            String[] splitString = queryLowerCase.split("where | order by | group by ");
            return splitString[1];
        }
        else return null;
        
    }

    /*
     * This method will extract condition(s) from the query string. The query can
     * contain one or multiple conditions. In case of multiple conditions, the
     * conditions will be separated by AND/OR keywords. for eg: Input: select
     * city,winner,player_match from ipl.csv where season > 2014 and city
     * ='Bangalore'
     *
     * This method will return a string array ["season > 2014","city ='bangalore'"]
     * and print the array
     *
     * Note: ----- 1. The field name or value in the condition can contain keywords
     * as a substring. For eg: from_city,job_order_no,group_no etc. 2. The query
     * might not contain where clause at all.
     */

    public String[] getConditions(String queryString) {
        String conditionPartQuery = "";

        String conditions[] = new String[1];
        boolean flag2 = false;

        String split[] = queryString.split(" ");
        for (String s : split) {
            if (s.equals("where")) {
                flag2 = true;
            }
        }
        if (flag2) {
            String splitQ[] = queryString.split("where");


            boolean flag1 = false;
            String splitQ2[] = splitQ[1].split(" ");
            for (int i = 0; i < splitQ2.length; i++) {
                if (splitQ2[i].equals("order") || splitQ2[i].equals("group")) {
                    flag1 = true;
                    break;
                }
            }

            if (flag1) {
                String splitQ1[] = splitQ[1].split("order|group");
                conditionPartQuery = splitQ1[0];
                conditionPartQuery = conditionPartQuery.trim();
            } else {

                conditionPartQuery = splitQ[1];
                conditionPartQuery = conditionPartQuery.trim();
            }


            conditionPartQuery = conditionPartQuery.toLowerCase();


            String conditionPartQ[] = conditionPartQuery.split(" ");


            boolean flag = false;
            for (int i = 0; i < conditionPartQ.length; i++) {
                if (conditionPartQ[i].equals("and") || conditionPartQ[i].equals("or")) {
                    flag = true;
                    break;
                }


            }

            System.out.println(flag);
            if (flag) {
                conditions = conditionPartQuery.split(" and | or ");

                for (String s : conditions) {
                    System.out.print(s);
                }

            } else {
                conditions[0] = conditionPartQuery;
            }
        } else {
            conditions = null;

        }

        return conditions;
    }

    //return null;



    /*
     * This method will extract logical operators(AND/OR) from the query string. The
     * extracted logical operators will be stored in a String array which will be
     * returned by the method and the same will be printed Note:  1. AND/OR
     * keyword will exist in the query only if where conditions exists and it
     * contains multiple conditions. 2. AND/OR can exist as a substring in the
     * conditions as well. For eg: name='Alexander',color='Red' etc. Please consider
     * these as well when extracting the logical operators.
     *
     */

    public String[] getLogicalOperators(String queryString) {

        String arrOfStr[] = queryString.split(" ");//Split the array with a space (' ')
        int i = 0, count = 0;
        for (String n : arrOfStr) { // to loop through the array splitted array
            if (n.equals("and") || n.equals("or") || n.equals("not")) //check the conditioon if it has logical operators
            {
                count++; //have a count if it has the logical operators
            }
        }
        String arrOfOperators[] = new String[count]; //use the arrOfOperators to put the new count of the operators
        if (queryString.contains(" and ") || queryString.contains(" or ") || queryString.contains(" not ")) { //to check if contains the logical operators
            for (String a : arrOfStr) { //loop through the splitted array
                if (a.equals("and")) { //if it has the logical operator
                    arrOfOperators[i] = "and"; //store the logical operator in the arrOfOperators array
                    i++; //increase the variable to loop to next
                } else if (a.equals("or")) {
                    arrOfOperators[i] = "or";
                    i++;
                } else if (a.equals("not")) {
                    arrOfOperators[i] = "not";
                    i++;
                }
            }
            return arrOfOperators;//return the arrOfOperators array
        }
        return null;
    }



    /*
     * This method extracts the order by fields from the query string. Note:
     * 1. The query string can contain more than one order by fields. 2. The query
     * string might not contain order by clause at all. 3. The field names,condition
     * values might contain "order" as a substring. For eg:order_number,job_order
     * Consider this while extracting the order by fields
     */

    public String[] getOrderByFields(String queryString) {
        String queryLowerCase = queryString.toLowerCase(); //to convert the given querystring into the lowercase
        if (queryString.contains("order by")) { //to check if contains the order by clause
            String arrOfStr[] = queryString.split("order by"); // split the given string with order by clause
            String arrStr[] = new String[1]; //store the splitted array into new array
            arrStr[0] = arrOfStr[1].trim(); //trim the array with a space (' ')
            return arrStr; //return the final array
        }
        return null;//if not found return null
    }



    /*
     * This method extracts the group by fields from the query string. Note:
     * 1. The query string can contain more than one group by fields. 2. The query
     * string might not contain group by clause at all. 3. The field names,condition
     * values might contain "group" as a substring. For eg: newsgroup_name
     *
     * Consider this while extracting the group by fields
     */

    public String[] getGroupByFields(String queryString) {
        if (queryString.contains("group by")) {  //to check if contains the group by clause
            String arrOfStr[] = queryString.split("group by"); // split the given string with group by clause
            String arrStr[] = new String[1]; //store the splitted array into new array
            arrStr[0] = arrOfStr[1].trim(); //trim the array with a space (' ')
            return arrStr; //return the final array
        }
        return null;  //if not found return null

    }

    /*
     * This method extracts the aggregate functions from the query string. Note:
     *  1. aggregate functions will start with "sum"/"count"/"min"/"max"/"avg"
     * followed by "(" 2. The field names might
     * contain"sum"/"count"/"min"/"max"/"avg" as a substring. For eg:
     * account_number,consumed_qty,nominee_name
     *
     * Consider this while extracting the aggregate functions
     */

    public String[] getAggregateFunctions(String queryString) {

        String fields[]=queryString.toLowerCase().split("from"); // to convert the given query string to lower case and slipt  the query from

        String aggregateFields[]=fields[0].trim().split(" ");// to spilt the fields array with a space( )

        String fieldsOnly[]=aggregateFields[1].split(","); // to split the aagregateFields with a  comma (,)

        List<String> aggreLst=new ArrayList<>(); // to create a new list of aggreLst
        int flag=0;
        for(int i=0;i<fieldsOnly.length;i++) // to loop through the fieldsOnly array
        {
            //to check for the condition of the aggregate function sum,min,max,avg,count
            if(fieldsOnly[i].contains("min(")||fieldsOnly[i].contains("max(")||fieldsOnly[i].contains("sum(")
                    ||fieldsOnly[i].contains("count(")||fieldsOnly[i].contains("avg(")){
                flag=1;
                aggreLst.add(fieldsOnly[i]); //if found add it to the aggreList
            }
        }
        String aggregateFunArray[]=new String[aggreLst.size()];
        aggregateFunArray=aggreLst.toArray(aggregateFunArray);

        if(flag==0)
            return null; // if not found rerturn null
        else
            return aggregateFunArray; // return the aggregate function array
    }


}



