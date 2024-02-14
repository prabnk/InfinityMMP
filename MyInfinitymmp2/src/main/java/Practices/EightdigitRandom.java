package Practices;

import java.util.Random;

public class EightdigitRandom {

	public static void main(String[] args) {
		
		Random random = new Random();  
		int digits=random.nextInt(99999999);
		System.out.println(String.format("%08d", digits));
	  
		}   
	
	
	}


