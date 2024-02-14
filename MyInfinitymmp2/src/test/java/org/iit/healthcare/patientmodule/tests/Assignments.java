package org.iit.healthcare.patientmodule.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignments {
	WebDriver driver;
	@Test
	public void validateRediffStocks()
	{
		
		/**
		 * chrome browser version
		 * chrome executable version
		 * selenium driver version
		 * System.setProperty("webdriver.chrome.driver","path to the chromedriver.exe");
		 */
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
		driver.get("https://money.rediff.com/gainers");
		String stockName=driver.findElement(By.xpath("//tbody/tr[1]/td[1]")).getText() ;
		System.out.println("Fetching the values for the stockName::: " + stockName);
		boolean result = getStockDetails(stockName);
		Assert.assertTrue(result);
	}
	public boolean getStockDetails(String stockName)
	{
		
		String prevClose = driver.findElement(By.xpath("//a[normalize-space()='"+stockName+"']/parent::td/following-sibling::td[2]")).getText();
		String currClose = driver.findElement(By.xpath("//a[normalize-space()='"+stockName+"']/parent::td/following-sibling::td[3]")).getText();
		String change =  driver.findElement(By.xpath("//a[normalize-space()='"+stockName+"']/parent::td/following-sibling::td[4]")).getText();
		
		System.out.println(prevClose +"===" + currClose +"==="+change);
		/**
		 * Formula
		 * (CurrentPrice-PreviousClose)
		 * --------------------------------- X 100
		 * (PreviousClose)
		 */
		double actual = Double.parseDouble(change.replace("+","").trim());
		double expectedValue = (Double.parseDouble(currClose)-Double.parseDouble(prevClose))*100;
		double expected =expectedValue/Double.parseDouble(prevClose);
		System.out.println("Actual" + actual);
		System.out.println("Expected"+ String.format("%.2f", expected));
		boolean result = (actual==expected);
		return result;
	}
}