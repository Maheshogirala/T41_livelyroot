package Utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Base.BaseClass;

public class ExtendReports implements ITestListener {

	 public ExtentSparkReporter sparkreporter; // Create the UI of the report 
	  public ExtentReports reports; //Common information will maintain in the  report
	  public ExtentTest test; // Script execution data will be enter into the report
	  
		public void onStart(ITestContext context) {
			// time stamp 
			String timestamp= new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
			
			
		    // specify  folder location  into the script 
			
			sparkreporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\ExtentReports\\"+timestamp+"Report.html");
			// Set the Document title 
			sparkreporter.config().setDocumentTitle("Automation Testing");
			// Set the Report Title
			sparkreporter.config().setReportName("Smoke Testing");
			// Set the document Theme 
			sparkreporter.config().setTheme(Theme.STANDARD);
			
			// ---- using Extentreports set the common info of the report 
			
			reports= new ExtentReports();
			
			// Attach the sparkreporter details into reports 
			reports.attachReporter(sparkreporter);
			// set the common info
			
			reports.setSystemInfo("Environment", "QA");
			reports.setSystemInfo("TestrName", "Mahesh");
			reports.setSystemInfo("OS", "windows");
			
			
		  }

		public void onTestSuccess(ITestResult result) {
		    test= reports.createTest(result.getName());
			test.log(Status.PASS, "Test is Passed ");
			
		  }
		
		public void onTestFailure(ITestResult result) {
		    test= reports.createTest(result.getName());
		    test.log(Status.FAIL, "Test is Faild - "+result.getName());
		    test.log(Status.FAIL, result.getThrowable());// what ever exceptions throws the system it will be collecting and print 
		    
		    try {
				String imagepath= new BaseClass().screenshot(result.getName());
				test.addScreenCaptureFromPath(imagepath);
			} catch (IOException e) {
				e.getMessage();
				e.printStackTrace();
			}
		
		}
		
		public void onTestSkipped(ITestResult result) {
		    test= reports.createTest(result.getName());
		    test.log(Status.SKIP, "Test is skipped - "+ result.getName());
		    test.log(Status.SKIP, result.getThrowable());
			
		  }
		
		
		public void onFinish(ITestContext context) {
		    
			reports.flush();
		  }
		
	
	
	
}
