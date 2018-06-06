package com.TestExample;

import org.junit.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.singletonpattern.Browsers;
import com.singletonpattern.Locator;
import com.singletonpattern.WebDriverSingleton;

public class Test_1 {

//	WebDriverSingleton driver = null;
	
	@BeforeClass
	public void Setup() {
		WebDriverSingleton.initDriverInstance(Browsers.CHROME);
		WebDriverSingleton.openURL("http://newtours.demoaut.com");
	}
	
	@Test
	public void TestCase_1() {
		
		WebDriverSingleton.findElement(Locator.NAME, "userName").sendKeys("testuser");
		WebDriverSingleton.findElement(Locator.NAME, "password").sendKeys("testpassword");
		WebDriverSingleton.findElement(Locator.NAME, "login").click();
	}
	
	@AfterClass
	public void killDriver() {
		WebDriverSingleton.quit();
	}
	
}
