package desambiguation;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class ExtractWord {
	 public static  List<String> get(String Text) {
		    {
		        List<String> ListOfWords= new ArrayList<String>();
		          //print each word in order
		          BreakIterator boundary = BreakIterator.getWordInstance();
		          boundary.setText(Text);
		         //printEachForward(boundary, stringToExamine);
		          //print each sentence in reverse order
		          boundary = BreakIterator.getWordInstance(new Locale("ar", "SA"));
		          boundary.setText(Text);
		         
		          
		         int start = boundary.first();
		     for (int end = boundary.next();end != BreakIterator.DONE;start = end, end = boundary.next()) 
		     {
		         String word = Text.substring(start,end).trim();
		         if (word.length()==1)
		         {
		             if (Pattern.matches("\\p{Punct}", word))
		             {
		                continue;
		             }
		         }
		         
		         if(Text.substring(start,end).trim().equals("")==false)
		         {
		          ListOfWords.add(Text.substring(start,end));
		         }
		    }
		     return ListOfWords;
		    
		}
		}

}
