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
public class PathtoRoot {
    
    public static List<List<String>> get(String ItemID)       
    {
      AWN awn = new AWN("\\Users\\nadia\\Documents\\upc_db_dic.xml" ,false);
      List<List<String>> setSynset = new ArrayList<>();
      List<List<String>> temp = new ArrayList<>();
      temp = awn.returnPathToRoot(ItemID);
      if (temp.size()!= 0)
      { 
        List<String> temp1 = temp.get(0);
        for(int i =0; i<temp1.size(); i++)
         {
          List<String> wordId = awn.Get_List_Word_Id_From_Synset_ID(temp1.get(i));
          List<String> temp2 = new ArrayList<>();
         for(int j =0; j<wordId.size(); j++)
         {
          temp2.add(awn.Get_Word_Value_From_Word_Id(wordId.get(j)));
         }
      setSynset.add(temp2);
       }
      return setSynset;
      }
      else 
      {
      return temp;
      }  
}
}
