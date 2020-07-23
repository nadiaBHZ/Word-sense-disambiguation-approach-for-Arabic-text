package desambiguation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ExtractParag {

	public static  List<String> get (String filePath)
	{	String line;
		   Integer i=0;
		   List <String> parag= new ArrayList<>();
		   	try{
		   InputStreamReader in = new InputStreamReader(new FileInputStream(filePath),Charset.forName("UTF-8"));
		   BufferedReader buff = new BufferedReader(in);
				 
		   try {
		   	while ((line = buff.readLine())!= null) {
		    parag.add(i,line);
		    i++;	
		  	}
		   	} finally {
		  	buff.close();
		   	}
		 	} catch (IOException ioe) {
		   	System.out.println("Erreur --" + ioe.toString());
		 	}
		   	return parag;
	}
}
