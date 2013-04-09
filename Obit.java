
import java.io.*;
import java.util.*;

class Obit
{
// Declare attributes for the Obit Class
   private String filename;
   private String filepath;
   private String name;
   private String pubDate;
   private String obitText;
   private String adjustedObitText;
   private HashMap<String, Integer> wordCount;
   private HashMap<String, Integer> adjustedWordCount;
   private Calendar date = Calendar.getInstance();
   private String monthStr;


   Obit(File f)    // **** Constructor *****
   {
	  // Set the data file information
      setFilename(f.getName());
      setFilepath(f.getPath());
      
      // Gather date information
      // date info can be accesed by int, string or Calendar class 
      // so we can decide how to use it later.
      pubDate   = filename.substring(0,8);
      int year  = Integer.parseInt(pubDate.substring(0,4));
      int month = Integer.parseInt(pubDate.substring(4,6));
      int day   = Integer.parseInt(pubDate.substring(6));
      String datestr = (year + "/" + month + "/" + day);
      String leadingZed = "";
      if (month < 10) leadingZed = "0";
      monthStr = ( year + "/" +leadingZed + month);
      date.clear();
      date.set(year, month, day);
      
      // get the name of the deceased 
      name = filename.substring(9, filename.length() - 4);
      name = name.replace(".jpg", "");
      
      // Get the text of the obituary and load it into the class
      String fileText = "";
      Scanner sc;    
      try
      {
         sc = new Scanner(f);
         while (sc.hasNext())
         {
            fileText = fileText + " " + sc.next();
         }
         sc.close();
      }
      catch (FileNotFoundException fne)
      {
         fileText = (getFilename() + " can not be found");      
      }
      
      // System.out.println(fileText); 
      // System.out.println(date);
      
      // remove the newline charcaters so the text is one long string
      setObitText(fileText.replace('\n', ' '));

      // clean the text so it is easier to handle
      this.obitText = fileText.toLowerCase();
      this.obitText = obitText.replaceAll("[.'&,:!;\"{}()?]", "");
      this.obitText = obitText.trim();
      this.wordCount = setWordCount(obitText);          
   }

   
   // This method returns a hash map that has all of the words in the
   // obituary and how many times they appear.
   private HashMap<String, Integer> setWordCount(String txt)
   {
      String[] words = txt.split(" ");
      HashMap<String, Integer> cnt = new HashMap<String, Integer>();

      for (String word : words)
      {
         if(cnt.containsKey(word))
         {
            int n = cnt.get(word);
            n++;
            cnt.put(word, n);
         }
         else 
            cnt.put(word, 1);    	 
      }
      return cnt;
   }

   // The toString method is to print out the Obit object as needed
   public String toString()
   {
      HashMap<String, Integer> wordCount = getWordCount();
      String str = "File Name: " + getFilename() + "\n" +
                   "Path Name: " + getFilepath() + "\n" +
                   "Publication Date: " + getPubDate() + "\n" +
                   "Name: "+ name + "\n" +
                   "Word Count:" + "\n";

      if (wordCount != null)
      {
         for (String word : wordCount.keySet())
         str += word + " " + wordCount.get(word) + "\n";
      }
      str += (getObitText() + "\n");
      return (str);
   }

   // *** SETTERS AND GETTERS ****
   
   public String getFilename() {return filename;}
   public String getFilepath() {return filepath;}
   public String getName() {return name;}
   public String getPubDate() {return pubDate;}
   public String getObitText() {return obitText;}
   public String getAdjustedObitText() {return adjustedObitText;}
   public HashMap<String, Integer> getWordCount() {return wordCount;}
   public HashMap<String, Integer> getAdjustedWordCount() {return adjustedWordCount;}
   public Calendar getDate() {return date;}
   public String getMonthStr() {return monthStr;}
   
   public void setFilename(String filename) {this.filename = filename;}
   public void setFilepath(String filepath) {this.filepath = filepath;}
   public void setName(String name) {this.name = name;}
   public void setPubDate(String pubDate) {this.pubDate = pubDate;                                                                                                                          }
   public void setObitText(String obitText) {this.obitText = obitText;}
   public void setAdjustedObitText(String adjustedObitText) {this.adjustedObitText = adjustedObitText;}
   public void setWordCount(HashMap<String, Integer> wordCount) {this.wordCount = wordCount;}
   public void setAdjustedWordCount(HashMap<String, Integer> adjustedWordCount) {this.adjustedWordCount = adjustedWordCount;}
   public void setDate(Calendar date) {this.date = date;}
   public void setMonthStr(String monthStr) {this.monthStr = monthStr;}
}
