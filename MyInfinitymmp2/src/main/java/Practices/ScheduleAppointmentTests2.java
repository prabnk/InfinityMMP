package Practices;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;

import javax.crypto.SealedObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScheduleAppointmentTests2 {

	WebDriver driver;
	String Month;
	String day;
	String Year;
	String Month_App;
	String Year_App;
	String AppTimeExp;
	String DocName_Expected;
	String ExpectedReason;
	String ExpectedDate;
	String ActualDate;
	String ActualTime;
	String ActualReason;
	String ActualDoc;

	@Test
	public void bookAppointment() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		driver.findElement(By.id("username")).sendKeys("ria1");
		driver.findElement(By.id("password")).sendKeys("Ria12345");
		driver.findElement(By.name("submit")).click();
		String actual = driver.findElement(By.xpath("//h3[normalize-space()='ria1']")).getText().trim();
		String expected = "ria1";
		Assert.assertEquals(actual, expected);

		driver.findElement(By.xpath("//span[normalize-space()='Schedule Appointment']")).click();
		driver.findElement(By.xpath("//input[@value='Create new appointment']")).click();

		driver.findElement(By.xpath(
				"//h4[text()='Dr.Smith']/parent::li/div/p[text()='Description:Orthopedic']/ancestor::ul/following-sibling::button"))
				.click();

		DocName_Expected = driver.findElement(By.xpath("//tbody/tr/td[3]/ul[1]/li[1]/h4[1]")).getText()
				.replace("Dr.", "").trim();

		// switching to the frame using id

		driver.switchTo().frame("myframe");
		driver.findElement(By.xpath("//input[@id='datepicker']")).click();
		
		// getting the date five days from today

		fetchdate(5);
	
		
		// Checking whether the month and year matches and select the exact date

		
		
		while(! driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText().contains(Month)
				&& driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText().contains(Year))
				{
			driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
		        }
		
		selectDate();
		
	
		// Choosing the time from the drop down

		WebElement time = driver.findElement(By.id("time"));
		Select TimeSelect = new Select(time);
		TimeSelect.selectByIndex(3);

		// fetching the time selected and store it in a variable

		WebElement Aptime = TimeSelect.getFirstSelectedOption();
		AppTimeExp = Aptime.getText();
		driver.findElement(By.id("ChangeHeatName")).click();
		
		// switching to default frame
		driver.switchTo().defaultContent();
		
		ExpectedReason = "Leg Pain";
		driver.findElement(By.id("sym")).sendKeys(ExpectedReason);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		validateApp();

	}

	private String fetchdate(int no_of_days) {
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.DAY_OF_MONTH, no_of_days);
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM/DD/YYYY");
		String date = sdf.format(calender.getTime());
		String dateArr[] = date.split("/");
		Month = dateArr[0];
		day = dateArr[1].replaceFirst("^0", "").trim();
		Year = dateArr[2];
        return date;
        
        
	}

	private void selectDate() {
		// TODO Auto-generated method stub
		Month_App = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
		Year_App = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();

		if (Month_App.equals(Month) && Year_App.equals(Year)) {

			List<WebElement> allDates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//td"));

			for (WebElement appdate : allDates) {

				if (appdate.getText().equals(day)) {

					appdate.click();

				}
			}

		}

	}

	private void validateApp() {
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.DAY_OF_MONTH, 5);

		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/DD/YYYY");
		ExpectedDate = sdf1.format(calender.getTime());
		ActualDate = driver.findElement(By.xpath("//table[@class='table']//tbody/tr[1]/td[1]")).getText().trim();
		ActualTime = driver.findElement(By.xpath("//table[@class='table']//tbody/tr[1]/td[2]")).getText().trim();
		ActualReason = driver.findElement(By.xpath("//table[@class='table']//tbody/tr[1]/td[3]")).getText().trim();
		ActualDoc = driver.findElement(By.xpath("//table[@class='table']//tbody/tr[1]/td[4]")).getText().trim();

		// Checking the actual data with expected data

		Assert.assertEquals(ActualDate, ExpectedDate);
		Assert.assertEquals(ActualTime, AppTimeExp);
		Assert.assertEquals(ActualReason, ExpectedReason);
		Assert.assertEquals(ActualDoc, DocName_Expected);

	}

}
