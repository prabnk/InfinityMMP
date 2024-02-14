
	package Practices;

	import java.time.Duration;
import java.util.Random;

import org.iit.healthcare.mmp.util.AppLibrary;
import org.iit.healthcare.mmp.util.BaseClass;
import org.iit.healthcare.mmp.util.MMPLib;
import org.openqa.selenium.Alert;
	import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class EditProfileTests2 extends BaseClass{
		WebDriver driver;
		 MMPLib mmpLib;
		/**
		 * 
		 * Validate LastName with valid and invalid
		 * Validate License - 8 Digit Random 
		 * 
		 */
		
		@BeforeClass
		public void instantiateDriver()
		{
			WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver();
		    mmpLib = new MMPLib(driver);
		    
		}
		
		@Test
		public void validatename()
		{
		     
			mmpLib.loginValidUser(mmpProp.getProperty("patientusername"),mmpProp.getProperty("patientpassword"));
			boolean resultFname = editFirstName();
			boolean resultLname = editLastName();
			Assert.assertTrue(resultFname);
			Assert.assertTrue(resultLname );
			
							
		}
		
		 
		
	@Test
		public void validatename_withInvalidData()
		{
			 
		    
		mmpLib.loginValidUser(mmpProp.getProperty("patientusername"),mmpProp.getProperty("patientpassword"));
		    boolean result = editFirstName_withInvalidData();
			boolean result1 = editLastName_withInvalidData();
			Assert.assertTrue(result);
			Assert.assertTrue(result1);
		}
@Test
	public void validateLicence() 
		{
			 
		    
	mmpLib.loginValidUser(mmpProp.getProperty("patientusername"),mmpProp.getProperty("patientpassword"));
			boolean result = editLicenceNumber();
			Assert.assertTrue(result);
			
		}
		
	@Test
	public void   validateLicence_InvalidData()
		{
			 
		    
		mmpLib.loginValidUser(mmpProp.getProperty("patientusername"),mmpProp.getProperty("patientpassword"));
			boolean result = editLicenceNumber_Invalid();
			Assert.assertTrue(result);
		}
@Test
	public void validateSSN()
		{
			 
		    
		    
			boolean result1=editSSNNumber();
			Assert.assertTrue(result1);
		}
	
@Test
public void validateSSN_InvalidData()
	{
		 
	    
	mmpLib.loginValidUser(mmpProp.getProperty("patientusername"),mmpProp.getProperty("patientpassword"));
		boolean result1=editSSNNumber_Invalid();
		Assert.assertTrue(result1);
	}
		@Test
	public void validateEditProfileFiels_NonEditable()
		{
			 
		    
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
	
	public boolean editLastName()
	{
		driver.findElement(By.xpath("//a[@href='profile.php']")).click();
		driver.findElement(By.xpath("//input[@id='Ebtn']")).click();
		driver.findElement(By.xpath("//input[@id='lname']")).clear();
		String expectedLNameValue = "LNAMEAUT"+generateRandomString();
		driver.findElement(By.id("lname")).sendKeys(expectedLNameValue);
		driver.findElement(By.id("Sbtn")).click();
		Alert alrt=driver.switchTo().alert();
	    alrt.accept();
	    String actualLNameValue= driver.findElement(By.id("lname")).getAttribute("value");
	    return expectedLNameValue.equals(actualLNameValue);
		
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
	
	public boolean editLastName_withInvalidData()
	{
		driver.findElement(By.xpath("//a[@href='profile.php']")).click();
		driver.findElement(By.xpath("//input[@id='Ebtn']")).click();
		driver.findElement(By.xpath("//input[@id='lname']")).clear();
		String expectedLNameValue = "LNAMEAUT"+generateRandomNumber();
		driver.findElement(By.id("lname")).sendKeys(expectedLNameValue);
		driver.findElement(By.id("Sbtn")).click();
		String actual= driver.findElement(By.id("lasterr1")).getText().trim();
		String expected="please enter only alphabets";
		return expected.equals(actual);
		
	}
	
	public boolean editLicenceNumber()
	{
		driver.findElement(By.xpath("//span[normalize-space()='Profile']")).click();
		driver.findElement(By.id("Ebtn")).click();
		driver.findElement(By.id("licn")).clear();
		String expectedLN =String.format("%08d",AppLibrary.getRandomNumber(100000000));
		driver.findElement(By.id("licn")).sendKeys( expectedLN );
		driver.findElement(By.id("Sbtn")).click();
		       
		Alert alrt = driver.switchTo().alert();
		alrt.accept();
		String actualLN = driver.findElement(By.id("licn")).getAttribute("value");
		return expectedLN.equals(actualLN );
		
	}
	
	
	public boolean editLicenceNumber_Invalid()
	{
		driver.findElement(By.xpath("//a[@href='profile.php']")).click();
		driver.findElement(By.xpath("//input[@id='Ebtn']")).click();
		driver.findElement(By.xpath("//input[@id='licn']")).clear();
		String expectedLN = generateRandomString();
		driver.findElement(By.id("licn")).sendKeys(expectedLN);
		driver.findElement(By.id("Sbtn")).click();
		String actual= driver.findElement(By.id("licerr")).getText().trim();
		String expected="Please enter valid 8-digit license number(Only digits)";
		return expected.equals(actual);
		
	}
	
	
public boolean editSSNNumber()
	{
		driver.findElement(By.xpath("//a[@href='profile.php']")).click();
		driver.findElement(By.xpath("//input[@id='Ebtn']")).click();
		driver.findElement(By.xpath("//input[@id='ssn']")).clear();
		String ExpectedSSN=String.format("%08d",AppLibrary.getRandomNumber(1000000000));
		driver.findElement(By.id("ssn")).sendKeys(ExpectedSSN);
		driver.findElement(By.id("Sbtn")).click();
		
			Alert alrt=driver.switchTo().alert();
	    alrt.accept();
	    String actualSSN= driver.findElement(By.id("ssn")).getAttribute("value");
	    return ExpectedSSN.equals(actualSSN);
		
		
	}
public boolean editSSNNumber_Invalid()
{

driver.findElement(By.xpath("//a[@href='profile.php']")).click();
driver.findElement(By.xpath("//input[@id='Ebtn']")).click();
driver.findElement(By.xpath("//input[@id='ssn']")).clear();
String expectedLN = generateRandomString();
driver.findElement(By.id("ssn")).sendKeys(expectedLN);
driver.findElement(By.id("Sbtn")).click();
String actual= driver.findElement(By.id("ssnerr")).getText().trim();
String expected="does not appear to be valid";
return expected.equals(actual);
}


		public String generateRandomString()
		{
			Random rand = new Random();
			int u = 65+ rand.nextInt(26);
			char upperCase = (char) u;
			
			
			//lower 97 to 122
			int l = 97+rand.nextInt(122-97+1); 
			char lowercase = (char) l;
		
			
			String randomString = upperCase+""+lowercase+"";
			return randomString;
			
		}
	public String generateRandomNumber()
		{
			Random rand = new Random();
			int u = 65+ rand.nextInt(26);
			char upperCase = (char) u;
			
			
			//lower 97 to 122
			int l = 97+rand.nextInt(122-97+1); 
			char lowercase = (char) l;
			String randomString = upperCase+lowercase+"";
			return randomString;
			
		}
		 

	}

	



