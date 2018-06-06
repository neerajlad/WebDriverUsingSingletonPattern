package com.TestExample;

import org.junit.AfterClass;
import org.testng.annotations.Test;
import com.singletonpattern.Browsers;
import com.singletonpattern.Locator;
import com.singletonpattern.WebDriverSingleton;

public class Test_1 {

	WebDriverSingleton driver = null;
	
	public void Setup() {
		driver = WebDriverSingleton.getDriverInstance(Browsers.CHROME);
		driver.openURL("http://newtours.demoaut.com");
	}
	
	@Test
	public void TestCase_1() {
		
		driver.findElement(Locator.NAME, "userName").sendKeys("testuser");
		driver.findElement(Locator.NAME, "password").sendKeys("testpassword");
		driver.findElement(Locator.NAME, "login").click();
	}
	
	@AfterClass
	public void killDriver() {
		driver.quit();
	}
	
}
