package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


import testCases.BaseTestCase;



public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		
//		SimpleDateFormat df=new SimpleDateFormat("yyy.MM.dd.HH.mm.ss");
//		Date dt =new Date();
//		String currentdatetimestamp=df.format(dt);
		
		//date and time
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		repName="Test-Report-" + timestamp+ ".html";	//This line includes name of the report+time+extension.
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);	//reports folder and report name
		
		sparkReporter.config().setDocumentTitle("Orange Hrm Automation Report");
		sparkReporter.config().setReportName("Orangehrm functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "OrangeHrm");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Login", "admin");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		//which are the os and browser names you are passing from xml file that we can capture here through testContext in onstart method
		String os=testContext.getCurrentXmlTest().getParameter("os");	//which operating system
		extent.setSystemInfo("Operating System", os);
		
		String browser=testContext.getCurrentXmlTest().getParameter("browser");//which browser
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();  //same getting included groups in xml file
		if(!includedGroups.isEmpty()) {	//not empty that means if groups is there then add information
			extent.setSystemInfo("Groups", includedGroups.toString()); //here adding will done
		}
			
	}
	
	public void onTestSuccess(ITestResult result) {	//result indicates actual result of the test case for that we can get class name nad groups
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); //getting groups displayed in the result 
		test.log(Status.PASS, result.getName()+"got sucessfully executed"); //this si normal logger msg
		try {
			String imagepath=new BaseTestCase().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imagepath);
		}
		catch(IOException e1) {
			e1.printStackTrace();
		}
	}
	
		
		
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+" got Failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		try {
			String imagepath=new BaseTestCase().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imagepath);
		}
		catch(IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" got Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
		String pathOfTheExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfTheExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());//open the report browser automatically
		}catch(IOException e1) {
			e1.printStackTrace();
		}
	}

}
