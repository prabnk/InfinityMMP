package Practices;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Filereader_word {

	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub
	HashMap<String, Integer> wordmap=new HashMap<String, Integer>();
	FileReader file=new FileReader("C:/Prabha/test.txt");
	 BufferedReader reader=new BufferedReader(file);
      String CurrentLine=reader.readLine();
      while (CurrentLine !=null)
      {
    	String words[]=  CurrentLine.toLowerCase().split(" ");
    	 for (String word: words)
    	 {
    		 if(!word.isBlank())
    		 {
    		 if  (wordmap.containsKey(word))
    		 {
    			 wordmap.put(word,wordmap.get(word)+1);
    		 }
    		 else
    		 {
    			 wordmap.put(word,1);
    			 
    		 }
    		
    	 }
    	 }
    	 
    	 CurrentLine=reader.readLine();
    	 
    	
      }
      
      wordmap.forEach((k,v) -> System.out.println(k + ": "+v));
      HashMap<String , Integer> MaxMap=new HashMap<String, Integer>();
      wordmap
      .entrySet()
      .stream()
      .filter(entry->entry.getValue()==Collections.max(wordmap.values()))
      .forEach(e -> MaxMap.put(e.getKey(),e.getValue()));
      System.out.println("===========");
      System.out.println(MaxMap);
	}

}
