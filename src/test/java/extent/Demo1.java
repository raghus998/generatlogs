package extent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo1
{
	static
	{
		WebDriverManager.chromedriver().setup();
	}
	ExtentHtmlReporter htmlReport;
	ExtentReports extent;
	WebDriver driver;
	@BeforeClass
	public void preCondition()
	{
		htmlReport = new ExtentHtmlReporter("./Reports/Report.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReport);
	}
	
	@Test
	public void test()
	{
		ExtentTest test = extent.createTest("Google Test","Verify Search Box");
		test.log(Status.INFO, "Test Case is started");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		test.pass("Browser is opened");
		driver.get("https://www.google.com/");
		test.pass("Navigated to Google.com");
		driver.findElement(By.name("q")).sendKeys("qspiders");
		test.pass("Entered qsp in Search box");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		test.pass("Clicked on the enter");
		driver.close();
		test.pass("closed the chrome browser");
		
		
	}
	
	@Test
	public void testA()
	{
		ExtentTest test1 = extent.createTest("Google Test1","Verify Search Box1");
		test1.log(Status.INFO, "Test Case is started");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		test1.pass("Browser is opened1");
		driver.get("https://www.google.com/");
		test1.pass("Navigated to Google.com1");
		driver.findElement(By.name("q")).sendKeys("qspiders");
		test1.pass("Entered qsp in Search box1");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		test1.pass("Clicked on the enter1");
		driver.close();
		test1.pass("closed the chrome browser1");
	}
	
	@AfterClass
	public void postCondition()
	{
		extent.flush();
	}


}
