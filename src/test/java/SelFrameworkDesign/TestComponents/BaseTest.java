package SelFrameworkDesign.TestComponents;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SelFrameworkDesign.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initDriver() throws IOException {

		// properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//SelFrameworkDesign//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}

		driver.manage().window().maximize();
		return driver;

	}
	
	public List<HashMap<String, String>> getJSONDataToMap(String filePath) throws IOException {
		// read json to string
		// it has to be File, so get the JSon file path: right click - properties
		String jsonContent = FileUtils.readFileToString(new File(
				filePath), StandardCharsets.UTF_8);
		//String to HashMap Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		// in SubmitOrderTest return new Object[][] { { map }, { map1 } };
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
	}

	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver = initDriver();
		// driver.get("https://rahulshettyacademy.com/client");
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		// driver.findElement(By.id("userEmail")).sendKeys("fiko@gmail.com");
		// driver.findElement(By.id("userPassword")).sendKeys("Fiko12345*");
		// driver.findElement(By.id("login")).click();
		
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}

}
