package Practices;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Hello world!
 *
 */
public class ReadXlsxfile 
{
    public static void main( String[] args ) throws IOException 
    {
        String fileName="C:\\Users\\prabn\\OneDrive\\Book.xlsx";
        String sheetName="Sheet1";
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
				 System.out.println(inputData[i][j]);
			}
			 System.out.println();
		}
    }
}
