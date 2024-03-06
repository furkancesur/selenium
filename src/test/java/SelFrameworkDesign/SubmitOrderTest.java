package SelFrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import SelFrameworkDesign.pageobjects.LandingPage;
import SelFrameworkDesign.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest {

	public static void main(String[] args) {
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
		landingPage.loginApplication("fiko@gmail.com", "Fiko12345*");

		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		// driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();

		Actions a = new Actions(driver);

		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "germany").build().perform();

		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[1]")).click();
		/*
		 * WebElement submit = driver.findElement(By.cssSelector(".action__submit"));
		 * 
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * 
		 * js.executeScript("arguments[0].click();", submit);
		 */

		driver.findElement(By.cssSelector(".action__submit")).click();

		//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".hero-primary")));

		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		driver.close();

	}

}
