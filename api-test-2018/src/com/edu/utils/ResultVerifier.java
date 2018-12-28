package com.edu.utils;

import java.util.regex.Pattern;

public class ResultVerifier {


    public static boolean verifyStringsByEqual(final String expectedValue, final String actualValue) {
		return expectedValue != null && expectedValue.equals(actualValue);
	}
    
    public static boolean verifyStringsByEqualAndExist(final String expectedValue, final String actualValue) {
		boolean flag=false;
    	if(expectedValue != null && expectedValue.equals(actualValue))
		{
    		flag=true;
    		return flag;
		}
    	if(expectedValue != null && actualValue.contains(expectedValue))
    	{
    		flag=true;
    		return flag;
    	}
    	return flag;
	}
    public static void main(String args[])
    {
    	System.out.print(ResultVerifier.verifyStringsByEqualAndExist("hello", "hello world"));

    }
}