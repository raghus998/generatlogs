package extent;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo {
	static {
		WebDriverManager.chromedriver().setup();
	}

	@Test
	public void testA() throws IOException {
		// To create the ReportFile
		ExtentHtmlReporter htmlreport = new ExtentHtmlReporter("./Reports/DemoReport.html");
		// to attache the created report
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlreport);
		ExtentTest test = extent.createTest("This Is google test", "Simple test to verify google search box");
		test.log(Status.INFO, "Test Case is started");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		test.pass("Opened the chrome browser");

		driver.get("https://www.google.com/");
		test.pass("Naviagted to google.com");
		driver.findElement(By.name("q")).sendKeys("qspiders");
		test.pass("Entered text in search box");

		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		test.pass("Clicked on the enter");
		String path = Demo.getScreenShot(driver);
		test.pass("Pass", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		driver.close();
		test.pass("closed the chrome browser");
		
		extent.flush();

	}
	

public static String getScreenShot(WebDriver driver) throws IOException
{
	String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	File dest = new File("./screenshots/"+date+".png");
	FileUtils.copyFile(src,dest );
	String path = dest.getAbsolutePath();
	return path;
}
	
}
