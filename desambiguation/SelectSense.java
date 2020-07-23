/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desambiguation;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Naadia
 */
    public class SelectSense {
        List<List<List<String>>> MNA = new ArrayList<>();
        List<List<List<String>>> MA = new ArrayList<>();
        List<String> paragraph = new ArrayList<>();
    public SelectSense(String filePath)
          {
            paragraph = ExtractParag.get(filePath);   
          } 
        public List<List<List<String>>> getMNA()
        {
         for(int i=0; i<paragraph.size(); i++)
         {
             if(paragraph.get(i).isEmpty()==false)
         {        
         List<List<String>> tempMNA2 = new ArrayList<>();
         List<String> sentences = ExtractSent.get(paragraph.get(i));
         for (int j= 0; j<sentences.size();j++)
         {
         List<String> term = ExtractTerm.get(sentences.get(j));
         List<String> tempMNA1 = new ArrayList<>();
         for(int k=0; k <term.size();k++)
         {
         if (NmbrConcptAWN.get(term.get(k))==1 || NmbrConcptAWN.get(term.get(k))==2)
         tempMNA1.add(term.get(k));
         }
         tempMNA2.add(tempMNA1);
         }
         MNA.add(i,tempMNA2);
         }
         }
            return MNA;
        }
  
        public List<List<List<String>>> getMA()
        {
        for(int i=0; i<paragraph.size(); i++)
         {
             if(paragraph.get(i).isEmpty()== false)
             {
         List<List<String>> tempMA2 = new ArrayList<>();
         List<String> sentences = ExtractSent.get(paragraph.get(i));
         for (int j= 0; j<sentences.size();j++)
         {
           List<String> tempMA1 = new ArrayList<>();
           List<String> term = new ArrayList<>();
           term = ExtractTerm.get(sentences.get(j));
                for(int k=0; k <term.size();k++)
                  {
                    if (NmbrConcptAWN.get(term.get(k))>2)
                    tempMA1.add(term.get(k));
                  }
                tempMA2.add(tempMA1);
         }
           MA.add(i,tempMA2);
          }
         }
           return MA;
         }
}
    


