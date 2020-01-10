package com.tests;


import java.util.concurrent.TimeUnit;

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
	static
	{
		WebDriverManager.chromedriver().setup();
	}
	 private static Logger log = LogManager.getLogger(Demo.class.getName());
	WebDriver driver;
	@BeforeMethod
	public void preCondition()
	{
		log.info("Opening the browser");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		log.info("Maximised the browser");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demo.actitime.com/login.do");
		log.info("Entered url");
	}
	
	@Test(priority = 1)
	public void testA()
	{
		log.info("Fetching the Title");
		String aTitle = driver.getTitle();
		String eTitle = "actiTIME - Login";
		try 
		{
			Assert.assertEquals(aTitle, eTitle);
			log.info("Both title is matching");
			
		}
		catch (Exception e)
		{
			log.error("Both title is not  matching");
		}
		
	}
	
	@Test(priority = 2)
	public void testB()
	{
		log.info("finding the   ele");
		boolean loginBtn = driver.findElement(By.id("loginButton")).isDisplayed();
		try 
		{
			Assert.assertTrue(loginBtn);
			log.info("ele is displated");
			
			
		}
		catch (Exception e)
		{
			log.error("ele is  not displated");
		}
		
	}
	

	
	@AfterMethod
	public void postCondition()
	{
		log.info("Closing the driver");
		driver.close();
	}

}
