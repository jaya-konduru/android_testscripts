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
	
	RemoteWebDriver wd;
	
	@BeforeTest
	public void setUp() throws MalformedURLException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appium-version", "1.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4");
		capabilities.setCapability("deviceName", "Nexus");
		capabilities.setCapability("app", "/Users/jkonduru/Downloads/app-release-unsigned.apk");
//		capabilities.setCapability("appPackage", "com.bitrise_io.sample_apps_android_simple");
//		capabilities.setCapability("appActivity", "com.aetn.watch.activities.SplashActivity");
		wd = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
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
