package org.iit.healthcare.patientmodule.tests;

import java.util.HashMap;

import org.iit.healthcare.mmp.pages.HomePage;
import org.iit.healthcare.mmp.pages.ScheduleAppointmentPage;
import org.iit.healthcare.mmp.util.BaseClass;
import org.iit.healthcare.mmp.util.MMPLib;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ScheduleAppointmentDatePicker_Tests extends BaseClass{
	 
	
	//JSON : java script object notation
	@Parameters({"username","password"})
	@Test
	public void scheduleAppointmentTest(String username,String password)
	{
	 
		
	    MMPLib mmpLib = new MMPLib(driver);
	    mmpLib.launchBrowser(mmpProp.getProperty("patienturl"));
	    HomePage hPage = mmpLib.loginValidUser(username, password);
	    String actual = hPage.getPatientName(mmpProp.getProperty("patientusername"));
		String expected=mmpProp.getProperty("patientusername");
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actual,expected);
		
		ScheduleAppointmentPage sPage = new ScheduleAppointmentPage(driver);
		HashMap<String,String> expectedHMap = sPage.bookAppoinment(40);
		HashMap<String,String> actualHMap = hPage.fetchPatientData();
		System.out.println(expectedHMap);
		System.out.println(actualHMap);
		
		sa.assertEquals(actualHMap,expectedHMap);
		
		sa.assertAll();
	}
	 
	 
	 
}