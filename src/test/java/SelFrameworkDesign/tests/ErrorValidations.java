package SelFrameworkDesign.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import SelFrameworkDesign.TestComponents.BaseTest;

public class ErrorValidations extends BaseTest {

	@Test
	public void submitOrder() throws IOException, InterruptedException {
		// Login info: fiko@gmail.com, Fiko12345*
		landingPage.loginApplication("f@gmail.com", "F12345*");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

}
