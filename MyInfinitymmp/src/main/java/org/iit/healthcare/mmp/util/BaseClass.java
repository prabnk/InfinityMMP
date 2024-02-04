	package org.iit.healthcare.mmp.util;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	protected WebDriver driver;
	protected Properties globalProp;
	protected Properties mmpProp;
	String browserType;
	String environment;
	ProjectConfiguration pConfig;

	@BeforeTest
	public void loadProperties() throws IOException
	{
		pConfig = new ProjectConfiguration();
		globalProp = pConfig.loadProperties("config/mmpglobal.properties");
		browserType = globalProp.getProperty("browserType");
		environment= globalProp.getProperty("environment");
	}
	@BeforeClass
	public void instantiateDriver() throws IOException  
	{

		if(environment.equals("qa") &&browserType.equals("chrome"))
		{
			pConfig = new ProjectConfiguration();
			mmpProp = pConfig.loadProperties("config/qa_mmp.properties");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);

		}
		else if(environment.equals("dev") &&browserType.equals("chrome"))
		{
			pConfig = new ProjectConfiguration();
			mmpProp = pConfig.loadProperties("config/dev_mmp.properties");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);

		}
	}
	@AfterClass
	public void tearDown() {

		driver.quit();
	}


}

	


