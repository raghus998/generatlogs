package genaric1;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static ExtentHtmlReporter htmlreport;
	public static ExtentReports extent;
	public  ExtentTest logger;
	public WebDriver driver;
	static {
		WebDriverManager.chromedriver().setup();
	}
	@BeforeTest
	public void preCondition() {
		htmlreport = new ExtentHtmlReporter("./Reports/Test.html");
		htmlreport.config().setDocumentTitle("Automation Test Report");//To set the report titile
		htmlreport.config().setReportName("Regression test Report");//To set the Report name
		htmlreport.config().setTheme(Theme.DARK);//To set the Theme
		
		//To set Env information
		extent = new ExtentReports();
		extent.attachReporter(htmlreport);
		extent.setSystemInfo("Host", "169.13.169.36");
		extent.setSystemInfo("Os", "Win10");
		extent.setSystemInfo("QA","RS");
		extent.setSystemInfo("BrowserName","Chrome");
	}
	
	@AfterTest
	public void postCondition() {
		extent.flush();
	}
	
	@BeforeClass
	public void startService() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");

	}
	
	
	@AfterClass
	public void stopService() {
		driver.close();
	}
	
	/*@BeforeMethod
	public void beforeTest(ITestResult res)
	{
		String name = res.getName();
	}*/
	
	@AfterMethod
	public void afterTest(ITestResult res) throws IOException
	{
		String name = res.getName();
		if (res.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, "Test is failed"+res.getName());
			logger.log(Status.FAIL, res.getThrowable());
			String path = getScreenShot(driver, name);
			//logger.addScreenCaptureFromPath(path);
			logger.fail("Failed", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		/*else if(res.getStatus() == ITestResult.SKIP)
		{
			logger.log(Status.SKIP, "Test is skipped"+ res.getName());
		}*/
		else if(res.getStatus() == ITestResult.SUCCESS)
		{
			logger.log(Status.PASS, "Test is passed"+  res.getName());
		}
	}
	
	
	
	public  String getScreenShot(WebDriver driver ,String name) throws IOException
	{
		String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		
		//String dest = System.getProperty("user.dir") +"/screenshots/" +name +date+".png";
		//File screenShot = new File(dest);
		//FileUtils.copyFile(src, screenShot);
		File dest = new File("./screenshots/"+name+date+".png");
		FileUtils.copyFile(src, dest);
		String path = dest.getAbsolutePath();
		return path;
	}
	
	public  String getScreenShot(WebDriver driver) throws IOException
	{
		String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		
		//String dest = System.getProperty("user.dir") +"/screenshots/" +name +date+".png";
		//File screenShot = new File(dest);
		//FileUtils.copyFile(src, screenShot);
		File dest = new File("./screenshots/"+date+".png");
		FileUtils.copyFile(src, dest);
		String path = dest.getAbsolutePath();
		return path;
	}
	
	
	
	
	
	
	
	
	
	
	
}
