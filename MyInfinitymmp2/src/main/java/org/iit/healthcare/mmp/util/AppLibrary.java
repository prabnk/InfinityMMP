package org.iit.healthcare.mmp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
	public static String[][] getDBValues(String uname,String pword,String dbname,String tableName,String hostip) throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
	//	Driver driver = new Driver();
		/*
		 * url a database url of the form jdbc:subprotocol:subnameuser 
		 * the database user on whose behalf the connection is being madepassword 
		 * the user's password
		 */
		String url="jdbc:mysql://"+hostip+":3306/"+dbname;
		String username=uname;
		String password=pword;
		
		Connection con = DriverManager.getConnection(url, username, password);
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
											 ResultSet.CONCUR_READ_ONLY);
		//int  value = stmt.executeUpdate("INSERT INTO  mmp.patient_data VALUES (12,'Joy','2021-11-12');");
		//System.out.println("The rows are updated "+ value);
	    stmt.executeUpdate("INSERT INTO " +dbname+"."+tableName+" VALUES " +"(13,'Jessie','2021-10-12')");
		ResultSet rs =  stmt.executeQuery("Select * from "+dbname+"."+tableName);
		rs.last();
		
		int rows = rs.getRow();
		System.out.println("Number of rows " + rows);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		System.out.println("Number of cols: "+ cols);
		
		String [][] data= new String[rows][cols];
		int i=0;
		rs.beforeFirst();
		while(rs.next())
		{
			for(int j=0;j<cols;j++)
			{
				data[i][j]=rs.getString(j+1);
				System.out.println(data[i][j]);
				//System.out.println("i :::"  + i +"@@@@"+"j:::::" + j);
			}
			System.out.println();
			i++;
		}	
		return data;
	}

	
}
