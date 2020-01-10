package genaric;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
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
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import freemarker.template.SimpleDate;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestForExtent {
	public static ExtentHtmlReporter htmlreport;
	public static ExtentReports extent;
	public  ExtentTest logger;
	public WebDriver driver;
	static {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeTest
	public void preCondition() {
		htmlreport = new ExtentHtmlReporter(new File("./reports/DemoReport.html"));
		htmlreport.config().setDocumentTitle("Automation Test Report");//To set theReport title
		htmlreport.config().setReportName("Functional Report");//To set the Report name
		htmlreport.config().setTheme(Theme.DARK);//tO set the Theme
	
		extent = new ExtentReports();
		extent.attachReporter(htmlreport);
		extent.setSystemInfo("OS", "Win10");
		extent.setSystemInfo("QA Name", "RS");
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
	
	
	@AfterMethod
	public void tearDown(ITestResult res) throws IOException
	{
		if (res.getStatus() == ITestResult.FAILURE) {
			
			logger.log(Status.FAIL, "Test is failed"+res.getName());
			String pic = getScreenShot(driver, res.getName());
			logger.log(Status.FAIL,  "Test is failed"+ res.getThrowable()+logger.addScreenCaptureFromPath(pic));
			
			//logger.addScreenCaptureFromPath(pic);
		}
		else if(res.getStatus() == ITestResult.SKIP)
		{
			logger.log(Status.SKIP, "Test is skipped"+ res.getName());
		}
		else if(res.getStatus() == ITestResult.SUCCESS)
		{
			logger.log(Status.PASS, "Test is passed"+  res.getName());
		}
	
	}
	
	
	public static  String getScreenShot(WebDriver driver ,String name) throws IOException
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
