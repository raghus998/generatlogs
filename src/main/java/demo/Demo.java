package demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo
{
	private static Logger log = LogManager.getLogger(Demo.class.getName());
	WebDriver driver;
	static
	{
		WebDriverManager.chromedriver().setup();
	}
	@BeforeMethod
	public void preCondition()
	{
		 driver = new ChromeDriver();
		log.info("Browser Opened");
		driver.manage().window().maximize();
		log.info("Browser Maximized");
		driver.get("https://demo.actitime.com/login.do");
		log.info("Navigated to Actitime");
	}
	
	@Test(priority = 1)
	public void veriftTitle()
	{
		log.debug("Fetching the Title");
		String aTitle = driver.getTitle();
		String eTitle = "actiTIME - Login";
		try
		{
			Assert.assertEquals(aTitle, eTitle);
			log.info("Title is matching : Pass");
		} 
		catch (Exception e) 
		{
			log.error("Fail");
			
			Assert.fail();
		}
		
	}
	
	@Test(priority = 2)
	public void verifyElement()
	{
		log.debug("Finding the element");
		//boolean lgnBtn = driver.findElement(By.id("loginButton123")).isDisplayed();
		try
		{
			boolean lgnBtn = driver.findElement(By.id("loginButton123")).isDisplayed();
			Assert.assertTrue(lgnBtn);
			log.info("LogIn Btn is displayed :Pass");
		}
		catch (Exception e) 
		{
			log.error("Failed");
			
		}
	
	}
	
	@AfterMethod
	public void postCondition()
	{
		log.debug("Closing the browser");
		driver.close();
	}

}
