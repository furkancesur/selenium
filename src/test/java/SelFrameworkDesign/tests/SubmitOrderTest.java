package SelFrameworkDesign.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.HashMap;
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

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
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

	@DataProvider
	public Object[][] getData() {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "fiko@gmail.com");
		map.put("password", "Fiko12345*");
		map.put("product", "ZARA COAT 3");

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "csr@gmail.com");
		map1.put("password", "Csr12345-");
		map1.put("product", "ADIDAS ORIGINAL");

		return new Object[][] { { map }, { map1 } };
	}

}
