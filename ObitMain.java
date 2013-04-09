

import java.io.*;
import java.util.*;

public class ObitMain   
{
   static ArrayList<Obit> obits = new ArrayList<Obit>();  // Container for holding all of the obituaries
   static TreeMap<String, Integer> sortedTally  = new TreeMap<String, Integer>();
   
   public static void main(String[] args) 
   {
      File[] dataDirs, obitFiles;                     // Arrays to hold data files and data directories
      String monthsList[] = mkMonthListArray();       // list of possible month/year combinations 

      
    // Chose your data directory      
    String obitDataDir = "C:\\Users\\Chuck\\Dropbox\\workspace\\Obits\\src\\ObitDataFiles2";
  //   String obitDataDir = "/home/chuck/Dropbox/workspace/Obits/src/ObitDataFiles2";

    
      File dataDir = new File(obitDataDir);     // create file object of the data directory
      dataDirs = dataDir.listFiles();           // get array of each of the data sub-directories
      
      for (File subDir : dataDirs)                   // for each sub-directory: 
      {                                         
         //System.out.println(subDir.getPath());       // print out the path of the sub-directory
         obitFiles = subDir.listFiles();               // get an array of every file in the sub-directory
         System.out.println("loading "+ obitFiles.length + " from the year " + subDir.getName());
         for (File dataFile : obitFiles)               // for each data file: 
         {
        //	 System.out.println(dataFile.getName());   // print the file name monitoring
        	 obits.add(new Obit(dataFile));            // create an Obit object from the data file 
         }                                             // and add it to the Obits Array List
      }
      
   // printAllObits(obits);                               
      System.out.println("Death Toll: "+obits.size()); // show how many files were processed
 
      sortedTally = tallyWordCounts(obits);    
      
      
     printAlphabeticTally(sortedTally, 100);
  
     // lets do some phrase tallies
     phraseByMonth("lover");
     phraseByMonth("celebration of life");
     phraseByMonth("aidsrelated");
     phraseByMonth("cancer");
     phraseByMonth("longtime companion");
     phraseByMonth("illness");
     phraseByMonth("aids");
     phraseByMonth("love");
     
     
     
      
  }  
   // This method checks the obits array and prints out a list of mm/yyyy and the 
   // frequency the given word appears.
   static void phraseByMonth(String word)
   //static void phraseByMonth(ArrayList<Obit> obits, String word)
   {
	   TreeMap<String, Integer> monthCount = new TreeMap<String, Integer>();

	   for (Obit obit : obits)
	   {
		  String obitTxt = obit.getObitText();
		  String mmyyyy = obit.getMonthStr();
		  
		  if (obitTxt.contains(word)) 
		  {
			  
			  if(monthCount.containsKey(mmyyyy))
	            {
	               int n = monthCount.get(mmyyyy);
	               n++;
	               monthCount.put(mmyyyy, n);
	            }
	            else 
	               monthCount.put(mmyyyy, 1);
		  }
	   }
	   
	   System.out.println("");
	   System.out.println("***** " + word + "*****");
	   System.out.println("The word '" + word +"' appears " + sortedTally.get(word) + " times.");
	   for (String mmyyyy : monthCount.keySet()) 
		   System.out.print(mmyyyy + " - " + monthCount.get(mmyyyy) + "\n");
   }
      
   
   // Print out data about each obituary on a single line
   // The Obit class has a toString method, but this method can be
   // used to print out the obituary on a single line.
   // It's main purpose is to check the obituaries for obvious 
   // errors in loading. 
   public static void printAllObits(ArrayList<Obit> obits)
   {
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
   } 
   
   // prints out the contents of the sorted word tally with a control integer 
   // to limit less frequent word counts.
   public static void printAlphabeticTally(TreeMap<String, Integer> sortedTally, int min)
   {
	   int cnt = 0;
      for (String word : sortedTally.keySet())
      {
 	     if (sortedTally.get(word) > min)
 	     {
 	        System.out.println(word + " - " + sortedTally.get(word)); 
 	        cnt++;
 	     }
      }
      System.out.println(cnt + " words appear more than " + min + " times.");
    }
   
   // This method returns an alphabetic tree map of all of the words found in 
   // all of the obituaries and the number of times each word appears.
   private static TreeMap<String, Integer> tallyWordCounts(ArrayList<Obit> obits)
   {
	   TreeMap<String, Integer> tally = new TreeMap<String, Integer>();
	   HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
	   
	   for (Obit obit : obits)
	   {
		   wordCount = obit.getWordCount();
		   Set<String> k = wordCount.keySet();
		   
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

   // This method creates a list of month and year combinations 
   // in the form of mm/yyyy.  The purpose of this method "just in case"
   // we need a listing of the months, which I think we will.
   static String[] mkMonthListArray()
   {
	   String[] monthList = new String[575];
	   int month = 1;
	   int year = 1970;
	   String leadingZed ="";
	   
	   for (int i = 0; i < monthList.length ; i++)
	   {
		   if (++month % 12 == 0) year++;
		   if (month %12 + 1 < 10) leadingZed = "0";
		   monthList[i] =    year + "/" + (month % 12 + 1);
		 // System.out.println(monthList[i]);
	   }
	   return (monthList);
   }
   

   
   
   
}
