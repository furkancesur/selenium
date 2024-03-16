package SelFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SelFrameworkDesign.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PageFactory
	// - How this annotation knows our driver: a method called which is initElements

	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		products.forEach(product -> waitForWebElementToAppear(product.findElement(By.cssSelector("b"))));

		return products;
	}

	public WebElement getProductByName(String productName) {
		// try to get the title of the each product using with Stream
		// product -> product.getText() but we can't say it because the text is not
		// written there directly so go further inside the code block
		/*
		 * getProductList().stream().forEach(product ->
		 * System.out.println(product.findElement(By.cssSelector("b")).getText()));
		 * 
		 * WebElement prod = getProductList().stream() .filter(product ->
		 * product.findElement(By.cssSelector("b")).getText().equals(productName)).
		 * findFirst() .orElse(null); return prod;
		 */
		WebElement prod = getProductList().stream().peek(product -> {
			String name = product.findElement(By.cssSelector("b")).getText();
			System.out.println("Product name: " + name);
			if (name.equals(productName)) {
				System.out.println("Match found: " + name);
			}
		}).filter(product -> product.findElement(By.cssSelector("b")).getText().trim().equals(productName.trim())).findFirst()
				.orElse(null);

		if (prod == null) {
			System.out.println("No product found with the name: " + productName);
		}
		return prod;

	}

	public void addProductToCart(String productName) throws InterruptedException {

		WebElement prod = getProductByName(productName);
		System.out.println(prod);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
}
