package tests;


import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utility.helper;

public class testBase {

	public static WebDriver driver;

	public static ChromeOptions chromeOption()
	{
		ChromeOptions options=new ChromeOptions();
		HashMap<String, Object> chromePrefs= new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("profile.managed_default_content_settings.geolocation", 2);
		options.setExperimentalOption("prefs", chromePrefs);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		options.addArguments("--disable-blink-features=AutomationControlled");

		return options;
	}


	@BeforeSuite
	public void startDriver() 
	{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
		driver= new ChromeDriver(chromeOption());
		driver.navigate().to("https://www.bestbuy.ca");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	//Take screenshot when test case fail and add it to scrrenshots's folder
	@AfterMethod
	public void screenShotOnFailure(ITestResult result)
	{
		if(result.getStatus()== ITestResult.FAILURE)
		{
			System.out.println("Failed!");
			System.out.println("Taking screenshot.....");
			helper.captureScreenShot(driver, result.getName());	
		}
	}

	@AfterSuite
	public void closeDriver()
	{
		driver.quit();
	}




}
