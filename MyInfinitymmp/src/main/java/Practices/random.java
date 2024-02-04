package Practices;

import java.util.Random;

import org.iit.healthcare.mmp.util.AppLibrary;

public class random {

	public static void main (String[] args) {
		// TODO Auto-generated method stub
		String expectedLN =String.format("%08d",AppLibrary.getRandomNumber(100000000));
		String ExpectedSSN=String.format("%08d",AppLibrary.getRandomNumber(1000000000));
		
			 /*long n=1000000000;
				long   LN= (long)(Math.random() * n);*/
				System.out.println(expectedLN);
				System.out.println(ExpectedSSN);
				/*	 public String EightDigitRandom()
				 {
					 Random random = new Random();
					 
						String LN=String.format("%08d", random.nextInt(99999999));
						System.out.println(LN);
						return LN;
				
				 }
				
			public String NineDigitRandom()
				 {
					 Random random = new Random();  
						String LN=String.format("%09d", random.nextInt(999999999));
						System.out.println(LN);
						return LN;
				
				 }*/
				
				
					Random rand = new Random();
					int u = 65+ rand.nextInt(26);
					char upperCase = (char) u;
					
					
					//lower 97 to 122
					int l = 97+rand.nextInt(122-97+1); 
					char lowercase = (char) l;
				
					
					String randomString = upperCase+""+lowercase+"";
					System.out.println(randomString);
					String randomString1 = upperCase+lowercase+"";
					System.out.println(randomString1);
			
					/*Random rand = new Random();
					int u = 65+ rand.nextInt(26);
					char upperCase = (char) u;
					
					
					//lower 97 to 122
					int l = 97+rand.nextInt(122-97+1); 
					char lowercase = (char) l;
					String randomString = upperCase+lowercase+"";
					return randomString;*/
					
				
				 


		
		 }
	
}
		
	




