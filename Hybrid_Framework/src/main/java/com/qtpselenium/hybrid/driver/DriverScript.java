package com.qtpselenium.hybrid.driver;

import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;
import java.util.Properties;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qtpselenium.hybrid.keywords.AppKeywords;
import com.qtpselenium.hybrid.utility.Constants;
import com.qtpselenium.hybrid.utility.XLS_Reader;
import java.lang.reflect.Method;


public class DriverScript {

	public static void executeTest(String testName, AppKeywords app, Hashtable<String, String> data,  XLS_Reader xls, Properties prop, Properties envProp, ExtentTest test) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		System.out.println(xls);
		System.out.println("Starting " + testName);
		test.log(Status.INFO, "Starting" + testName);
//		AppKeywords app = new AppKeywords();
		app.setProp(prop);
		app.setEnvProp(envProp);
		app.setData(data);
		app.setTest(test);
		int rowCount = xls.getRowCount(Constants.SHEET_KEYWORDS);
		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
			if(testName.equals(xls.getCellData(Constants.SHEET_KEYWORDS, Constants.COL_TCID, rowNum))) {
			String keyword = xls.getCellData(Constants.SHEET_KEYWORDS, Constants.COL_KEYWORDS, rowNum);
			String objectKey = xls.getCellData(Constants.SHEET_KEYWORDS, Constants.COL_OBJECT, rowNum);
			String dataKey = xls.getCellData(Constants.SHEET_KEYWORDS, Constants.COL_DATA, rowNum);
			app.setDataKey(dataKey);
			app.setObjectKey(objectKey);
			Method method = app.getClass().getMethod(keyword);
			method.invoke(app);


	}
		
	}
}
}
