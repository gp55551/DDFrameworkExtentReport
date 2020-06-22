package MyNew.tests;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utilities.BasicExtentReport;

public class AppTest extends BasicExtentReport {
	BasicExtentReport ext = new BasicExtentReport();
	ITestResult results;
	@BeforeTest
	public void testAppstart() {
		ext.startReport("Windows", "Chrome");
	}

	@Test
	public void testCase1() {
		Assert.assertTrue(true);
		System.out.println("Test case 1");
		ext.startTest("Test Case 1", "PASSED test case");
	}
	
	@Test
	public void testCase2() {
		Assert.assertTrue(true);
		System.out.println("Test case 2");
		ext.startTest("Test Case 2", "PASSED test case");
	}
	
	@Test
	public void testCase3() {
		Assert.assertTrue(true);
		System.out.println("Test case 3");
		ext.startTest("Test Case 2", "PASSED test case");
	}

	
	
	@AfterMethod
	public void testAppendM(ITestResult result) {
		ext.getResult(result);
	}
	@AfterTest
	public void testAppendT() {
		
		ext.tearDown();
	}

}
