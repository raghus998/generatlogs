package extent1;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import genaric1.BaseTest;

public class Demo extends BaseTest{
	@Test
	public void testA()
	{
		 logger = extent.createTest("testA","Verify Search Box ");
		logger.log(Status.INFO, "testA() started");
		logger.pass("Navigated to google");
		try 
		{
			driver.findElement(By.name("q")).sendKeys("qspiders");
			logger.pass("Entered qsp in Search box");
			driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
			logger.pass("Clicked on the enter");
			logger.log(Status.PASS, "testA() is passed");
			logger.pass("Pass testA()");
		} 
		catch (Exception e) 
		{
			Reporter.log(e.getMessage());
			logger.log(Status.FAIL, "testA() is failed");
			logger.fail("TestA() is failed");
			Assert.fail();
		}
	}
	@Test
	public void testB()
	{
		 logger = extent.createTest("testB","Verify Search Box ");
		logger.log(Status.INFO, "testB() started");
		logger.pass("Navigated to google");
		try 
		{
			driver.findElement(By.name("q")).sendKeys("qspiders");
			logger.pass("Entered qsp in Search box");
			driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
			logger.pass("Clicked on the enter");
			logger.log(Status.PASS, "testB() is passed");
			logger.pass("Pass testB()");
		} 
		catch (Exception e) 
		{
			Reporter.log(e.getMessage());
			logger.log(Status.FAIL, "testA() is failed");
			logger.fail("TestB() is failed");
			Assert.fail();
		}
	}
	
	@Test
	public void testC() throws IOException
	{
		 logger = extent.createTest("testC","Verify Search Box ");
		logger.log(Status.INFO, "testC() started");
		logger.pass("Navigated to google");
		try 
		{
			driver.findElement(By.name("q123")).sendKeys("qspiders");
			logger.pass("Entered qsp in Search box");
			driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
			logger.pass("Clicked on the enter");
			logger.log(Status.PASS, "testC() is passed");
			logger.pass("Pass testC()");
		} 
		catch (Exception e) 
		{
			Reporter.log(e.getMessage());
			logger.log(Status.FAIL, "testC() is failed");
			logger.fail("TestC() is failed");
			String path = getScreenShot(driver);
			logger.pass("Failed", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Assert.fail();
		}
	}


}
