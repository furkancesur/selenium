package SelFrameworkDesign.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import SelFrameworkDesign.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		//from child to parent can send the variables : super and create a constuctor in AbsractComponent 
		//driver comes from test case  which is SubmitOrderTest
		super(driver);
		
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));
	// PageFactory
	// - How this annotation knows our driver: a method called which is initElements
	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement passwordEle;

	@FindBy(id = "login")
	WebElement submit;

	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		// sending product catalogue object
		return productCatalogue;
		
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
