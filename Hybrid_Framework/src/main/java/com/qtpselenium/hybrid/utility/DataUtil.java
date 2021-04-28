package com.qtpselenium.hybrid.utility;

import java.util.Hashtable;

import com.sun.corba.se.impl.orbutil.closure.Constant;

public class DataUtil {
	public static boolean isTestSkipped(String testName, XLS_Reader xls) {
		for(int rowNum =2; rowNum<= xls.getRowCount(Constants.SHEET_TESTCASES); rowNum++) {
			if(!xls.getCellData(Constants.SHEET_TESTCASES, Constants.COL_TCID, rowNum).equals(testName)) {
				if(xls.getCellData(Constants.SHEET_TESTCASES, Constants.COL_RUNMODE, rowNum).equals("Y")) {
				return false;
			}
		}
	}
	return true;
		
	}
	
	public static Object[][] getTestData(String testName, XLS_Reader xls) {
		int testStartRowNumber = 1;
		while(!testName.equals(xls.getCellData(Constants.SHEET_DATA, 0 , testStartRowNumber))) {
			testStartRowNumber++;
		}
		int colStartRowNumber = testStartRowNumber + 1;
		int dataStartRowNumber = colStartRowNumber  + 1;
	
		int rowCount = 0;
		while(!xls.getCellData(Constants.SHEET_DATA, 0, (rowCount + dataStartRowNumber)).equals("")) {
			rowCount++;
			
		}
		
		int colCount = 0;
		while(!xls.getCellData(Constants.SHEET_DATA, colCount,  colStartRowNumber).equals("")) {
			colCount++;
			
		}
		
		Object[][] data = new Object[rowCount][1];
		for(int rowNum = 0; rowNum < rowCount; rowNum++) {
			Hashtable<String, String> datatable = new Hashtable<>();
			for(int colNum=0; colNum < colCount; colNum++) {
				String key = xls.getCellData(Constants.SHEET_DATA, colNum, colStartRowNumber);
				String value = xls.getCellData(Constants.SHEET_DATA, colNum, rowNum + dataStartRowNumber);
				datatable.put(key, value);

		}
			data[rowNum][0] = datatable;
		}
		
		return data;
		
	}	
		
}