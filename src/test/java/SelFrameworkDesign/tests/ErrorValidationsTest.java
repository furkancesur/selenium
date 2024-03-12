package SelFrameworkDesign.tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SelFrameworkDesign.TestComponents.BaseTest;
import SelFrameworkDesign.TestComponents.Retry;
import SelFrameworkDesign.pageobjects.CartPage;
import SelFrameworkDesign.pageobjects.CheckoutPage;
import SelFrameworkDesign.pageobjects.ConfirmationPage;
import SelFrameworkDesign.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		landingPage.loginApplication("f@gmail.com", "F12345*");
		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());

	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		// Login info: fiko@gmail.com, Fiko12345*
		// Login info-2: csr@gmail.com, Csr12345-

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("csr@gmail.com", "Csr12345-");

		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 3");
		Assert.assertFalse(match);

	}

}
