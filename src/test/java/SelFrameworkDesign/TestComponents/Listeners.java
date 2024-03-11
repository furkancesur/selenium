package SelFrameworkDesign.TestComponents;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import SelFrameworkDesign.resources.ExtentReporterNG;

public class Listeners implements ITestListener{
	ExtentTest test; 
	ExtentReports extent = ExtentReporterNG.getReportObject();
	
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		
		// Screenshot
	}
}
