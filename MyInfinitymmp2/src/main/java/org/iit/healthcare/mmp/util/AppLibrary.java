package org.iit.healthcare.mmp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class AppLibrary {

	
	public static String getFutureDate(int noofDays)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, noofDays);
		Date d =calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("d/MMMM/YYYY");
		String date = sdf.format(d);
		String dateArr[] = date.split("/");
		System.out.println(dateArr[0]);
		System.out.println(dateArr[1]);
		System.out.println(dateArr[2]);
		return date;
	}
	public static long getRandomNumber(long n) {
		
//		double d =12.5;
//		long l =(long) d;
//		byte b =10;
////		short s=10;
////		b=s;
		
	    return (long)(Math.floor(Math.random() * n));
	}
	
//	public static String getRandomNumber(String format,int l)
//	{
//		int i = 9999999999;
//		 Random random = new Random();  
//		    String LN=String.format(format, random.nextLong(l));
//		    System.out.println(LN);
//		    return LN;
//	}

	public static String[][] readXLSX(String fileName,String sheetName) throws IOException 
	{
		File f = new File(fileName);
		FileInputStream fis = new FileInputStream(f);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetName);
		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println("number of rows" + rows);
		System.out.println("number of cols" + cols);
		String inputData[][] = new String[rows][cols];
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				 XSSFCell cell = sheet.getRow(i).getCell(j);
				 inputData[i][j]= cell.getStringCellValue();
				 System.out.print(inputData[i][j]);
			}
			 System.out.println();
		}
		return inputData;
		
	}
	
}
