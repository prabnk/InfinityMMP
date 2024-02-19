package org.iit.healthcare.patientmodule.tests;

import java.util.Random;

import org.iit.healthcare.mmp.util.BaseClass;
import org.iit.healthcare.mmp.util.MMPLib;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class EditProfileTests extends BaseClass{
	 
	@Test
	public void validateEditProfileTest()
	{
		MMPLib mmpLib = new MMPLib(driver);
	    mmpLib.launchBrowser(mmpProp.getProperty("patienturl"));
	    mmpLib.loginValidUser(mmpProp.getProperty("patientusername"),mmpProp.getProperty("patientpassword"));
		boolean result = editFirstName();
		Assert.assertTrue(result);
	}
	@Test
	public void validateFName_withInvalidData()
	{
		 
		MMPLib mmpLib = new MMPLib(driver);
	    mmpLib.launchBrowser(mmpProp.getProperty("patienturl"));
		  mmpLib.loginValidUser(mmpProp.getProperty("patientusername"),mmpProp.getProperty("patientpassword"));
		boolean result = editFirstName_withInvalidData();
		Assert.assertTrue(result);
	}
	@Test
	public void validateEditProfileFields_NonEditable()
	{
	    MMPLib mmpLib = new MMPLib(driver);
	    mmpLib.launchBrowser(mmpProp.getProperty("patienturl"));
	    mmpLib.loginValidUser(mmpProp.getProperty("patientusername"),mmpProp.getProperty("patientpassword"));
		boolean result = editProfile_nonEditableFields();
		Assert.assertTrue(result);
	}
	public boolean editProfile_nonEditableFields()
	{
		driver.findElement(By.xpath("//span[normalize-space()='Profile']")).click();
		String actual = driver.findElement(By.id("fname")).getAttribute("readonly");
		String expected="true";
		return expected.equals(actual);
	}
	public boolean editFirstName_withInvalidData()
	{
		driver.findElement(By.xpath("//span[normalize-space()='Profile']")).click();
		driver.findElement(By.id("Ebtn")).click();
		driver.findElement(By.id("fname")).clear();
		driver.findElement(By.id("fname")).sendKeys(generateRandomNumber());
		driver.findElement(By.id("Sbtn")).click();
		String actual = driver.findElement(By.id("firsterr1")).getText().trim();
		String expected="please enter only alphabets";
		return expected.equals(actual);
	}
	public boolean editFirstName()
	{
		driver.findElement(By.xpath("//span[normalize-space()='Profile']")).click();
		driver.findElement(By.id("Ebtn")).click();
		driver.findElement(By.id("fname")).clear();
		String expectedfNameValue = "FNAMEAUT"+generateRandomString();
		driver.findElement(By.id("fname")).sendKeys(expectedfNameValue);
		driver.findElement(By.id("Sbtn")).click();
		Alert alrt = driver.switchTo().alert();
		alrt.accept();
		String actualfNameValue = driver.findElement(By.id("fname")).getAttribute("value");
		return expectedfNameValue.equals(actualfNameValue);
		
	}
	public String generateRandomString()
	{
		Random rand = new Random();
		int u = 65+ rand.nextInt(26);
		char upperCase = (char) u;
		System.out.println("UpperCase::" + upperCase );
		
		//lower 97 to 122
		int l = 97+rand.nextInt(122-97+1); 
		char lowercase = (char) l;
		System.out.println("lowercase:: " + lowercase);
		
		String randomString = upperCase+""+lowercase+"";
		return randomString;
		
	}
	public String generateRandomNumber()
	{
		Random rand = new Random();
		int u = 65+ rand.nextInt(26);
		char upperCase = (char) u;
		System.out.println("UpperCase::" + upperCase );
		
		//lower 97 to 122
		int l = 97+rand.nextInt(122-97+1); 
		char lowercase = (char) l;
		System.out.println("lowercase:: " + lowercase);
		
		String randomString = upperCase+lowercase+"";
		return randomString;
		
	}
	 
	

}