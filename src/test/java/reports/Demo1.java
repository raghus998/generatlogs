package reports;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;

import genaric.BaseTestForExtent;

public class Demo1  extends BaseTestForExtent {
	@Test
	public void testD() throws IOException
	{
		
			 logger = extent.createTest("testD","Verify Search Box ");
			logger.log(Status.INFO, "testD() started");
			logger.pass("Navigated to google");
			try 
			{
				driver.findElement(By.name("q")).sendKeys("qspiders");
				logger.pass("Entered qsp in Search box");
				driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
				logger.pass("Clicked on the enter");

			} 
			catch (Exception e) 
			{
				Reporter.log(e.getMessage());
				logger.log(Status.FAIL, "testD() is failed");
				
				//MediaEntityModelProvider screen = MediaEntityBuilder.createScreenCaptureFromPath("Demo.png").build();
				//logger.fail("Failed ", screen);
				Assert.fail();
			}
		}
	@Test(dependsOnMethods = "testD")
	public void testE()
	{
		 logger = extent.createTest("testE","Verify Search Box ");
		logger.log(Status.INFO, "testE() started");
		logger.pass("Navigated to google");
		try 
		{
			driver.findElement(By.name("q")).sendKeys("qspiders");
			logger.pass("Entered qsp in Search box");
			driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
			logger.pass("Clicked on the enter");
		} 
		catch (Exception e) 
		{
			Reporter.log(e.getMessage());
			logger.log(Status.FAIL, "testA() is failed");
			Assert.fail();
		}
	}
	@Test
	public void testF()
	{
		 logger = extent.createTest("testF","Verify Search Box ");
		logger.log(Status.INFO, "testF() started");
		logger.pass("Navigated to google");
		try 
		{
			driver.findElement(By.name("q")).sendKeys("qspiders");
			logger.pass("Entered qsp in Search box");
			driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
			logger.pass("Clicked on the enter");
		} 
		catch (Exception e) 
		{
			Reporter.log(e.getMessage());
			logger.log(Status.FAIL, "testA() is failed");
			Assert.fail();
		}
	}
	}


