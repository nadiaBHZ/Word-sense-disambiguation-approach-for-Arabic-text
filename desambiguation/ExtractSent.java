package desambiguation;

import java.util.ArrayList;
import java.util.List;

public class ExtractSent {
	public static  List<String> get(String Text) 
	 {
	   List<String> sent = new ArrayList<>();
	   int j= 0;
		String[] sentences = Text.split("[\\.\\!\\?]");  
	    for (int i= 0; i<sentences.length; i++)
	    {
	    	sent.add(j,sentences[i]);
	    	j++;
	    }
		 return sent;
	 }
}
