package edu.ccsf.proj.obit;

import java.io.*;
import java.util.*;

public class ObitMain   
{
   public static void main(String[] args) throws IOException 
   {
      File[] dataDirs, obitFiles;
      ArrayList<Obit> obits = new ArrayList<Obit>();
      String monthsList[] = mkMonthListArray();

      // get list of directories in the data folder
  //  String obitDataDir = "C:\\Users\\Chuck\\Dropbox\\workspace\\Obits\\src\\ObitDataFiles";
      String obitDataDir = "/home/chuck/Dropbox/workspace/Obits/src/ObitDataFiles";
      File dataDir = new File(obitDataDir);
     
      dataDirs = dataDir.listFiles();

      // Load up the obituaries in to an array list 
      for (File f : dataDirs) 
      {
       //System.out.println(f.getName());
         System.out.println("");
         System.out.println(f.getPath());
         
         obitFiles = f.listFiles();

         for (File obit : obitFiles)
        	 obits.add(new Obit(obit));
      }
      
     // Print out data about each obituary
      for (Obit obit : obits) 
      {
    	  Calendar date = obit.getDate();
    	  int year = date.get(Calendar.YEAR); 
    	  int month = date.get(Calendar.MONTH);
    	  int day = date.get(Calendar.DAY_OF_MONTH);
    	  String dateStr = (month +"/"+day+"/"+year);
    	  
    	  System.out.println(obit.getName() + " " + dateStr +
    			             "  " + obit.getPubDate() + " -- -- -- "+obit.getObitText());
      } 
      
    //  System.out.println("Death Toll: " + obits.size());
      
    // (String my : monthsList) System.out.println(my);

   //   wordByMonth(obits,"aids");

      
      
      
      // Tally the words in all the obituaries
   
      HashMap<String, Integer> tally = new HashMap<String, Integer>();
  	  TreeMap<String, Integer> sortedTally = new TreeMap<String, Integer>();
  	  
      tally = tallyWordCounts(obits);
      sortedTally.putAll(tally);
  /*    
      for (String word : sortedTally.keySet()) 
    	  if (tally.get(word) > 5)
    	     System.out.println(word + " - " + sortedTally.get(word));
*/
   }
   
   private static HashMap<String, Integer> tallyWordCounts(ArrayList<Obit> obits)
   {
	   HashMap<String, Integer> tally = new HashMap<String, Integer>();
	   HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
	   
	   for (Obit obit : obits)
	   {
		   wordCount = obit.getWordCount();
		   
		   Set<String> k = wordCount.keySet();
		  // System.out.println(k);
		   
			  for (String word : k) 
		      {
				 int x = 0, y = 0;
				 int z;
				 
				 if (tally.containsKey(word)) x = tally.get(word); 
				 if (wordCount.containsKey(word)) y = wordCount.get(word);
				 z = x + y;
				 tally.put(word, z);
		      }
		 
		}	   
	   return (tally);
   }

   static String[] mkMonthListArray()
   {
	   String[] monthList = new String[575];
	   int month = 1;
	   int year = 1970;
	   
	   for (int i = 0; i < monthList.length ; i++)
	   {
		   if (++month % 12 == 0) year++;
		   monthList[i] = (month % 12 + 1) + "/" + year;
	   }
	   return (monthList);
   }
   
   static void wordByMonth(ArrayList<Obit> obits, String word)
   {
	   HashMap<String, Integer> monthCount = new HashMap<String, Integer>();

	   for (Obit obit : obits)
	   {
		  String obitTxt = obit.getObitText();
		  System.out.println(obitTxt);
	   }
	   
   }
   
   
   
   
}
