/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desambiguation;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
/**
 *
 * @author bouhriz
 */
public class Desambiguation extends JFrame {
  public Desambiguation() {
      super();
      AWN awn = new AWN("\\Users\\nadia\\Documents\\upc_db_dic.xml" ,false);
        
     //données pour le test des mots ambigus
     String file = "\\Users\\nadia\\Documents\\CorpusWSD\\doc27.txt";   
         
     SelectSense senses = new SelectSense(file);//à partir d'un fichier, on extrait les concepts associé 
                                               //en divisant les mots en deux catégorie, 
                                               //mots ambigue MA et mots non ambigues MNA
     List<List<List<String>>> MA = senses.getMA();//on récupère la liste des mots ambigues pour l'ensemble du texte
     List<List<List<String>>> MNA = senses.getMNA();//on récupère les mots non ambigues pour l'ensemble du texte
     ModelisationSVM model = new ModelisationSVM(MNA,MA);//on modélise sous forme vectroiel les mots du texte
     List<List<String>> contxGlobal = model.getCntxGlobal(); // l'ensemble de vecteurs du mots non ambigus de l'ensemble de texte
     List<List<List<String>>> vectorSens = model.getVectorSens();// le vecteur des concepts d'un mot ambigu
     List<List<List<String>>> contxlocal = model.getContxLocal();// le vecteur de mots non ambigus présent dans le contexte d'un mot ambigu
     List<List<String>> synsetIDvectorSens = model.getSynsetIDVectorSens();// le sysnset ID de chacun des concepts des mots ambigus
     List<Integer> indiceparaMa = model.getindicePargSetMA();
     desamb desamb = new desamb(indiceparaMa, contxGlobal, vectorSens, contxlocal, synsetIDvectorSens);
     setTitle("JTable basique dans un JScrollPane");
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // code renvoyant pour chaque mot ambigu le concept approprié  
     List<String> setMA = model.getSetMA();
     Object[][] donnees = new Object[setMA.size()][6]; // 9
     for(int i = 0; i<setMA.size(); i++)
     {
        List<List<String>> senspossibledei = new ArrayList<>();
        List<String> wordId1 = awn.Get_List_Word_Id_From_Value(setMA.get(i));
       for(int k = 0; k<wordId1.size(); k++)
       {
       String str = awn.Get_Synset_ID_From_Word_Id(wordId1.get(k));
       List<String> wordIdsynID = awn.Get_List_Word_Id_From_Synset_ID(str);
       List<String> temp = new ArrayList<>();
       for(int l= 0; l<wordIdsynID.size(); l++)
       {
       temp.add(awn.Get_Word_Value_From_Word_Id(wordIdsynID.get(l)));
       }
       senspossibledei.add(temp);
       }
       List<String>  wordId = awn.Get_List_Word_Id_From_Synset_ID(desamb.get().get(i));
       List<String> tempsens = new ArrayList<>();
       for(int j = 0 ; j< wordId.size(); j++)
       {
       tempsens.add(awn.Get_Word_Value_From_Word_Id(wordId.get(j)));
       }
       
       donnees[i][0] = i+1;
       donnees[i][1] = setMA.get(i);
       donnees[i][2]= senspossibledei;
       donnees[i][3] = tempsens;
       donnees[i][4]= PathtoRoot.get(desamb.get().get(i));
       donnees[i][5]= contxGlobal.size();
     }
        String[] entetes = {"id", "mot ambigu", "sens possible", "desamb", "Path", "nombre total des MNA dans le texte"};
  
        JTable tableau = new JTable(donnees, entetes);
 
        getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
 
        pack();  
         
     }
    public static void main(String[] args) {
     
       new Desambiguation().setVisible(true);
            
    }
}
