package com.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
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
	public static String app_path;
	
	@BeforeTest
	public void setUp() throws MalformedURLException, FileNotFoundException{
		
		String APK = System.getProperty("app");
		
		String platformName = "Android";
		Response result = uploadAPPs(APK, platformName);
		System.out.println(result);
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appiumVersion", "1.4.16");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4");
//		capabilities.setCapability("deviceName", "Nexus");
		capabilities.setCapability("deviceName", "Samsung Galaxy S4 Emulator");
//		capabilities.setCapability("app", "/Users/jkonduru/Downloads/app-release-unsigned.apk");
		capabilities.setCapability("app", "sauce-storage:watchApp.apk");

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
	}
	
	private static Response uploadAPPs(String a_path, String platformName) throws FileNotFoundException {
		HttpAuthenticationFeature auth = HttpAuthenticationFeature.basic(username, accesskey);
		Client client = ClientBuilder.newClient();
		if (platformName == "Android") {
			app_path = "rest/v1/storage/jkonduru/watchApp.apk";
		} else {
			app_path = "rest/v1/storage/jkonduru/watchApp.zip";
		}
		WebTarget target = client.target("https://saucelabs.com").path(app_path).queryParam("overwrite", "true")
				.register(auth);
		Response response = target.request(MediaType.APPLICATION_OCTET_STREAM_TYPE)
				.post(Entity.entity(new FileInputStream(a_path), MediaType.APPLICATION_OCTET_STREAM_TYPE));

		return response;
	}
}
