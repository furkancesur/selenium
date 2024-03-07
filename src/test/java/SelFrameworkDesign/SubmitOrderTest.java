package SelFrameworkDesign;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import SelFrameworkDesign.pageobjects.CartPage;
import SelFrameworkDesign.pageobjects.CheckoutPage;
import SelFrameworkDesign.pageobjects.ConfirmationPage;
import SelFrameworkDesign.pageobjects.LandingPage;
import SelFrameworkDesign.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest {

	public static void main(String[] args) throws InterruptedException {
		// Login info: fiko@gmail.com, Fiko12345*
		// System.setProperty("webdriver.chrome.driver",

		String productName = "ZARA COAT 3";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		// driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		// driver.findElement(By.id("userEmail")).sendKeys("fiko@gmail.com");
		// driver.findElement(By.id("userPassword")).sendKeys("Fiko12345*");
		// driver.findElement(By.id("login")).click();
		ProductCatalogue productCatalogue = landingPage.loginApplication("fiko@gmail.com", "Fiko12345*");

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("Germany");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmationMessage =  confirmationPage.getConfirmationMessage();

		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		driver.close();

	}

}
