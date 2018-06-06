package com.singletonpattern;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverSingleton {

	private static WebDriver driver;
	private WebElement element;

	private WebDriverSingleton() {
	}

	public static WebDriverSingleton getDriverInstance(Browsers browserName)  {
		DesiredCapabilities caps = null;

		if (driver == null) {
			switch (browserName) {
			case CHROME:
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				options.addArguments("--disable-infobars");
				caps = DesiredCapabilities.chrome();
				caps.setCapability(ChromeOptions.CAPABILITY, options);
				caps.setCapability("pageLoadStrategy", "none");

				ChromeDriverService service = new ChromeDriverService.Builder()
						.usingDriverExecutable(new File("/driver/chromedriver.exe")).usingAnyFreePort().build();
				options.merge(caps);
				driver = new ChromeDriver(service, options);
				break;
			case EDGE:
				caps = DesiredCapabilities.internetExplorer();
				EdgeDriverService edgeService = new EdgeDriverService.Builder()
		         .usingDriverExecutable(new File("/driver/MicrosoftWebDriver.exe"))
		         .usingAnyFreePort()
		         .build();
				try {
					edgeService.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				EdgeOptions option =new EdgeOptions();
				option.merge(caps);
				driver = new EdgeDriver(edgeService,option);
				break;
			case FIREFOX:
				caps = DesiredCapabilities.firefox();
				FirefoxProfile Profile = new FirefoxProfile();
				Profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/xml");
				driver = new FirefoxDriver(caps);
				break;
			default:
				break;
			}
		}
		return (WebDriverSingleton) driver;
	}
	
	public void openURL(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	public void quit() {
		driver.quit();
	}
	
	
	public WebElement findElement(Locator locator, String value) {
		
		switch (locator) {
		case CLASSNAME:
			element =  driver.findElement(By.className(value));
			break;
		case CSS:
			element =  driver.findElement(By.cssSelector(value));
			break;
		case ID:
			element =  driver.findElement(By.id(value));
			break;
		case LINKTEXT:
			element =  driver.findElement(By.linkText(value));
			break;
		case NAME:
			element =  driver.findElement(By.name(value));
			break;
		case PARTIALLINKTEXT:
			element =  driver.findElement(By.partialLinkText(value));
			break;
		case TAGNAME:
			element =  driver.findElement(By.tagName(value));
			break;
		case XPATH:
			element =  driver.findElement(By.xpath(value));
			break;
		default:
			break;
		}
		return element;
	}
	
	
	

}
