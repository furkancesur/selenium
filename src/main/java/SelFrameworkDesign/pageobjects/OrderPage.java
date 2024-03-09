package SelFrameworkDesign.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SelFrameworkDesign.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;

	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public Boolean VerifyOrderDisplay(String productName) throws InterruptedException {
		waitForVisibilityOfAllElements(productNames);

		// Debugging output
		// productNames.forEach(product -> System.out.println(product.getText()));

		Boolean match = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}

}
