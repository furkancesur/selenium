package SelFrameworkDesign.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

	WebDriver driver;
	//Constructor
	/* 
	 * Constructors are almost similar to methods except for two things - its name is the same as the class name and it has no return type
	 */
	public AbstractComponent(WebDriver driver) {
		//driver works in this scope so create a global object
		this.driver = driver;
		
	}

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//By.cssSelector(".mb-3"))
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy);
	}

}
