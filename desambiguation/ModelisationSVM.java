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
 * @author nadia
 */
public class ModelisationSVM {
     
     AWN awn = new AWN("\\Users\\nadia\\Documents\\upc_db_dic.xml" ,false);
     List<List<String>> ContxGlobal = new ArrayList<>(); //contient les vecteurs associés pour chaque mot non ambigus
                                                         //calculé à partir de SVM
     List<Integer> IndiceParagMa = new ArrayList<>();
     List<String> SVM = new ArrayList<>(); // contient les sysnsetId des mots non ambigus du texte
     List<List<String>> SVMLocal = new ArrayList<>();
     List<String> SetMNA = new ArrayList<>();// l'ensemble des mots non ambigus de la totalité du texte
     List<List<String>> SetMNALocal = new ArrayList<>();
     List<List<List<String>>> VectorSens = new ArrayList<>();//contient pour chaque sens d'un mot ambigu de chaque paragraphe
                                                             //le vecteur associé, 
                                                             //calculé à partir de SVM
     List<List<List<String>>> VectorSensSVM = new ArrayList<>();//contient les vecteurs des mots ambigus
                                                               // calculé à partir de SVM = globale 
     List<List<List<String>>> ContxLocal = new ArrayList<>();//contient les vecteurs associés (calculé à partir de SVM)
                                                             //aux mots non ambigus présent
                                                             //dans le voisinage des mots ambigus de chaque paragraphe.
     List<List<String>> SynsetIDVectorSens = new ArrayList<>();// contient les vecteurs associés aux mots ambigus du texte
     List<String> SetMA = new ArrayList<>();
     List<List<String>> SetMNAContxLocalMA = new ArrayList<>(); // Ensemble des mots non ambigues présents au voisinage
                                                                 // des mots ambigus des paragraphes.
    public ModelisationSVM(List<List<List<String>>> MNA, List<List<List<String>>> MA)
     {
      List<List<String>> setMNAparag = new ArrayList<>();
      List<List<String>> MAtemp = new ArrayList<>();
      
        //Extraire tous les  mots non ambigus du texte
         for(int i=0; i<MNA.size();i++)
         {
             for (int j = 0; j<MNA.get(i).size(); j++)
             {
                 for (int k=0; k<MNA.get(i).get(j).size(); k++)
                   {
                   SetMNA.add(MNA.get(i).get(j).get(k)); 
                   }
             }
            // List<String> tempSVMLocal = new ArrayList<>();
            // for(int j= 0;j<SetMNALocal.get(i).size(); j++)
             //        {
              //       List<String> wordId = awn.Get_List_Word_Id_From_Value(SetMNALocal.get(i).get(j));
               //      String synID = awn.Get_Synset_ID_From_Word_Id(wordId.get(0));
                //     tempSVMLocal.add(synID);
                 //    }
             //SVMLocal.add(i,tempSVMLocal);
          }
  //supprimer les mots redondantdans dans le SetMNA
  for(int i =0; i<SetMNA.size(); i++)
    {
      for(int j=i+1; j<SetMNA.size(); j++)
      {
     if(SetMNA.get(i).equalsIgnoreCase(SetMNA.get(j)))
       SetMNA.remove(j);
       }
    }
//Extraire tous les synsetID des concepts contenue dans la liste de SetMNA et les mettre dans la liste SVM
    for(int i = 0; i<SetMNA.size();i++)
    {
      List<String> wordId = awn.Get_List_Word_Id_From_Value(SetMNA.get(i));
      String synID = awn.Get_Synset_ID_From_Word_Id(wordId.get(0));
      SVM.add(synID);
    }
    
    //construire le context global qui contient les vecteurs des mots non ambigus du texte construit suivant SVM
    for(int i =0; i<SVM.size(); i++)
    {
      List<String> tempCntxGlobal = new ArrayList<>();
      for(int j = 0; j<SVM.size(); j++)
      {
        double k = (int)Math.round(awn.Get_word_similirty_wuP(SVM.get(i),SVM.get(j))*1000)/(double)1000;
        tempCntxGlobal.add(String.valueOf(k));
      }
     ContxGlobal.add(i,tempCntxGlobal);
      }
//Extraire  l'ensemble de MNA et de MA des phrases
    for(int i = 0; i<MNA.size(); i++)
    {  List<String> tempMA = new ArrayList<>();
       List<String> tempMNA = new ArrayList<>();
       
    for(int j = 0; j<MNA.get(i).size(); j++)
    {   for(int k =0; k<MNA.get(i).get(j).size(); k++)
           {
            tempMNA.add(MNA.get(i).get(j).get(k));
           }
     for(int l =0; l<MA.get(i).get(j).size(); l++)
           {tempMA.add(MA.get(i).get(j).get(l));
           }
    }
    setMNAparag.add(tempMNA);
    for(int l = 0; l<tempMA.size(); l++)
    {SetMA.add(tempMA.get(l));
     SetMNAContxLocalMA.add(tempMNA);
     IndiceParagMa.add(i);
    }
    }
   
    // Extraire l'ensemble de vectorsens et l'ensemble de ContxLocal 
    for(int i =0; i<SetMA.size(); i++)
    {
      List<String> wordId = awn.Get_List_Word_Id_From_Value(SetMA.get(i));
      List<List<String>> temp1 = new ArrayList<>();
      List<String> tempSynID = new ArrayList<>();
         for (int j=0; j<wordId.size(); j++)
         {
             List<String> temp = new ArrayList<>();
             String synID = awn.Get_Synset_ID_From_Word_Id(wordId.get(j));
             tempSynID.add(synID);
                     for(int k=0; k<SVM.size(); k++)
                     { 
                        double l = (int)Math.round(awn.Get_word_similirty_wuP(synID, SVM.get(k))*1000)/(double)1000;
                        temp.add(String.valueOf(l));
                     }
             temp1.add(temp);         
          }
         SynsetIDVectorSens.add(i,tempSynID);// le sysnset id associé aux sens du mot ambigu i 
         VectorSens.add(i,temp1);// le vecteur associé aux sens du mot ambigu i 
         }
    
    //Extraire le contexte local de chaque mot ambigu en utilsant l'ensemble de SetMNAContxLocalMA
    for(int i =0; i<setMNAparag.size();i++)
    {  if(setMNAparag.get(i).isEmpty())
        {   for(int s=0; s<setMNAparag.size(); s++)   
        {   while(setMNAparag.get(i).isEmpty())
            { if(setMNAparag.get(s).isEmpty()== false)
                setMNAparag.set(i, setMNAparag.get(s));
                }
        }
        }
        List<List<String>> temp1 = new ArrayList<>();
        for(int j =0; j<setMNAparag.get(i).size(); j++)
        {   
         List<String> temp = new ArrayList<>();
         List<String> wordId = awn.Get_List_Word_Id_From_Value(setMNAparag.get(i).get(j));
         String synsetID= awn.Get_Synset_ID_From_Word_Id(wordId.get(0));
        for(int k =0; k<SVM.size(); k++)
          {
           double a = (int)Math.round(awn.Get_word_similirty_wuP(synsetID, SVM.get(k))*1000)/(double)1000;
           temp.add(String.valueOf(a));
          }  
      temp1.add(temp);
      }
      ContxLocal.add(temp1);
    }
    }
     public  List<List<String>> getCntxGlobal()
     {
     return ContxGlobal;
     }
     
     public  List<String> getSVM()
     {
     return SVM;
     }
     
     public  List<String> getSetMA()
     {
     return SetMA;
     }
     public List<List<List<String>>> getVectorSens()
     {
     return VectorSens;
     }
     
     public List<List<List<String>>> getContxLocal()
     {
     return ContxLocal;
     }
     public List<List<String>> getSynsetIDVectorSens()
     {
     return SynsetIDVectorSens;
     }
     public List<List<String>> getSetMNAContxLocal()
     {
     return SetMNAContxLocalMA;
     }
     public  List<Integer> getindicePargSetMA()
     {
     return IndiceParagMa;
     }
    
     public  List<String> getSetMNA()
     {
     return SetMNA;
     }
}
