
import java.io.*;
import java.util.*;
// COMMENTS !
// new test from victor - sat 

public class Obit
{
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


   Obit(File f) throws IOException
   {
      setFilename(f.getName());
      setFilepath(f.getPath());
      
      pubDate = filename.substring(0,8);
      int year = Integer.parseInt(pubDate.substring(0,4));
      int month = Integer.parseInt(pubDate.substring(4,6));
      int day = Integer.parseInt(pubDate.substring(6));
 
      String datestr = (year + "/" + month + "/" + day);
      monthStr = (month + "/" + year);
      date.clear();
      date.set(year, month, day);
      
      name = filename.substring(9, filename.length() - 4);
      String fileText = "";
      Scanner sc;  
      
      FileReader inputStream = null;
   /*   
      try
      {
    	  inputStream = new FileReader(f);
    	  
    	  int c;
    	  while ((c = inputStream.read()) != -1)
    	     fileText += 'x';
      }
      finally { if (inputStream != null) inputStream.close();}
     */ 
      
      
      
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
      
       setObitText(fileText.replace('\n', ' '));

    this.obitText = fileText.toLowerCase();
  //  this.obitText = cleanText(obitText);

    this.obitText = obitText.replaceAll("[.,:!;\"{}()?]", "");
    this.obitText = obitText.trim();
    this.wordCount = setWordCount(obitText);
            
   }

   private String cleanText(String txt) 
   {
	   int n = txt.length();
	   String str = "";
	   
	   for (int i = 0; i < n; i++)
	   {
		   if (txt.charAt(i) > 31 && txt.charAt(i) < 127)
			   str += txt.charAt(i);
		   else 
			   str += ' ';
	   }
	return str;
}

private HashMap<String, Integer> setWordCount(String txt)
   {
      String[] words = txt.split(" ");
      HashMap<String, Integer> cnt = new HashMap<String, Integer>();

      for (String word : words)
      {
    	 if(! word.equals(" ") || ! word.equals(null))
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
    	 cnt.remove("");
    	 cnt.remove("-");
    	 
      }
      return cnt;
   }

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
   public void setMonthStr(String str) {this.monthStr = monthStr;}
}
