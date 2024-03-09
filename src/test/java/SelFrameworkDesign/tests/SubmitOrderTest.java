package SelFrameworkDesign.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import SelFrameworkDesign.TestComponents.BaseTest;
import SelFrameworkDesign.pageobjects.CartPage;
import SelFrameworkDesign.pageobjects.CheckoutPage;
import SelFrameworkDesign.pageobjects.ConfirmationPage;
import SelFrameworkDesign.pageobjects.LandingPage;
import SelFrameworkDesign.pageobjects.OrderPage;
import SelFrameworkDesign.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test
	public void submitOrder() throws IOException, InterruptedException {
		// Login info: fiko@gmail.com, Fiko12345*
		ProductCatalogue productCatalogue = landingPage.loginApplication("fiko@gmail.com", "Fiko12345*");

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("Germany");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmationMessage = confirmationPage.getConfirmationMessage();

		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() throws InterruptedException {
		// "ZARA COAT 3"
		ProductCatalogue productCatalogue = landingPage.loginApplication("fiko@gmail.com", "Fiko12345*");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}

}
