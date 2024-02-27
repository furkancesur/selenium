package SelFrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// Login info: fiko@gmail.com, Fiko12345*
		// TODO Auto-generated method stub
		// System.setProperty("webdriver.chrome.driver",
		// "/Users/furkancesur/Documents/chromedriver");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("fiko@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Fiko12345*");
		driver.findElement(By.id("login")).click();
		// getting the all items to find unique classname: .mb-3, on console: $(".mb-3")

		List<WebElement> products = driver.findElement(By.cssSelector(".mb-3"));
		// try to get the title of the each product using with Streams
		// product -> product.getText() but we can't say it because the text is not
		// written there directly so go further inside the code block
		WebElement prod = products.stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
	}

}
