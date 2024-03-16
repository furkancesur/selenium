package SelFrameworkDesign.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SelFrameworkDesign.pageobjects.CartPage;
import SelFrameworkDesign.pageobjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;

	// Constructor
	/*
	 * Constructors are almost similar to methods except for two things - its name
	 * is the same as the class name and it has no return type
	 */
	public AbstractComponent(WebDriver driver) {
		// driver works in this scope so create a global object
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;

	public CartPage goToCartPage() throws InterruptedException {
		//waitForWebElementToAppear(cartHeader);
		Thread.sleep(3000);
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrdersPage() throws InterruptedException {
		waitForWebElementToBeClickable(orderHeader);
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		// By.cssSelector(".mb-3"))
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForWebElementToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToDisappear(WebElement ele) {
		// driver.findElement so it has to be WebElement ele
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
		// OR
		// Thread.sleep(1000);
	}
	
	public void waitForWebElementToBeClickable(WebElement el) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(el));
	}
	
	public void waitForVisibilityOfAllElements(List<WebElement> elements) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

}
