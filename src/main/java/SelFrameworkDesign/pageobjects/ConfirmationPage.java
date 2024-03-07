package SelFrameworkDesign.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SelFrameworkDesign.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	WebDriver driver;

	@FindBy(css = ".hero-primary")
	WebElement ConfirmationMessage;

	private By confirmationMessageLocator = By.cssSelector(".hero-primary");

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getConfirmationMessage() {
		waitForElementToAppear(confirmationMessageLocator);
		return ConfirmationMessage.getText();
	}

}
