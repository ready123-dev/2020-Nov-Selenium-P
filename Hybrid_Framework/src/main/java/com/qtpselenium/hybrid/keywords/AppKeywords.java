package com.qtpselenium.hybrid.keywords;

import java.io.IOException;

import com.aventstack.extentreports.Status;

public class AppKeywords extends GenericKeywords {
	public void validateLogin() throws IOException {
		System.out.println("Validating Login");
		test.log(Status.INFO, "Validating Login");
		if(isElementPresent(objectKey)){
			if(data.get(dataKey).equals("Success")) {
				reportFailure("Expected succcess, but got failure");
			}
		} else {
			if(data.get(dataKey).equals("Failure")) {
				reportFailure("Expected Failure, but got success");
			}
		}
		System.out.println("Validation complete");
		test.log(Status.INFO, "Validation complete");
	}

	



}
