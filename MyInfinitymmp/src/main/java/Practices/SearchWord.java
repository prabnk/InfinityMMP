package Practices;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class SearchWord {

	public static void main(String[] args) throws Exception {
		Scanner sc1=new Scanner(System.in);
		System.out.println("Enter the word");
		String word=sc1.next();
		boolean flag=false;
		int count=0;
		Scanner sc2=new Scanner(new FileInputStream("C:/Prabha/test.txt"));
		while(sc2.hasNextLine()){
			String line=sc2.nextLine();
			if (line.indexOf(word) !=-1)
					{
				flag=true;
				count=count+1;
						
					}
				}
		if (flag)
		{
			System.out.println("the number of times that word exists: "    + count);
		}
		else
		{
			System.out.println("The word doesn't exist");
			
		}
	}

}
