/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desambiguation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bouhriz
 */
public class NamedEntity {
    public List<String> get()
    {
                   String line;
                   String filePath = "\\Users\\nadia\\Documents\\EntityNommee.txt";
		   Integer i=0;
		   List<String> parag= new ArrayList<>();
		   	try{
		   InputStreamReader in = new InputStreamReader(new FileInputStream(filePath),Charset.forName("UTF-8"));
		   BufferedReader buff = new BufferedReader(in);		 
		   try{
		       while ((line = buff.readLine())!= null){
                           if(! line.equals(""))
		       parag.add(line);	
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