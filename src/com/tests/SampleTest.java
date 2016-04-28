package com.tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SampleTest {
	
	public static String username = "jkonduru";
	public static String accesskey = "819eb1ee-41c8-43c0-967b-5c818b0953b4";
	RemoteWebDriver wd;
	
	@BeforeTest
	public void setUp() throws MalformedURLException{
		
		String APK = System.getProperty("app");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appiumVersion", "1.4.16");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4");
		capabilities.setCapability("deviceName", "Nexus");
//		capabilities.setCapability("app", "/Users/jkonduru/Downloads/app-release-unsigned.apk");
		capabilities.setCapability("app", APK);

		//wd = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		
		wd = new RemoteWebDriver(new URL("http://" + username + ":" + accesskey + "@ondemand.saucelabs.com:80/wd/hub"), capabilities);
		
		wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	@AfterTest
    public void tearDown() throws Exception {
		wd.quit();
    }
	
	@Test
	public void DemoTest(){
		wd.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.RelativeLayout[1]/android.widget.Button[1]")).click();
		wd.findElement(By.xpath("//android.widget.Button[1]")).click();
		wd.findElement(By.name("More options")).click();
		wd.findElement(By.xpath("//android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")).click();
		//(JavascriptExecutor)wd.executeScript("mobile: scrollTo", new HashMap<String, String>() {{ put("element", wd.findElement(By.name("(null)")).getId()); }});
		wd.close();
	}
}
