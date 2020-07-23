package desambiguation;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import safar.basic.morphology.analyzer.factory.MorphologyAnalyzerFactory;
import safar.basic.morphology.analyzer.interfaces.IMorphologyAnalyzer;
import safar.basic.morphology.analyzer.model.MorphologyAnalysis;
import safar.basic.morphology.analyzer.model.WordMorphologyAnalysis;
import safar.util.normalization.impl.SAFARNormalizer;
import safar.util.tokenization.impl.SAFARTokenizer;


public class ExtractTerm {
public static List<String> get(String Text)
{

	//Extraction de la liste des stop words
        List<String> stopword = ListStopWord.get();
    
        //ensemble des mots après elimination des mots vides.
        List<String> terms = new ArrayList<>();	
        
                         List<String> words= new ArrayList<String>();
                        SAFARNormalizer normalize = new SAFARNormalizer();
                        String textNormalized = normalize.normalize(Text);
                        SAFARTokenizer  tokenizer = new SAFARTokenizer();
                        String[] tokens = tokenizer.tokenize(textNormalized);
                        for (int i = 0; i<tokens.length; i++)
                        {
                        words.add(tokens[i]);
                        }
        
        //pour chaque mot du texte, on élimine les mots vides.
        for (int i=0; i<words.size(); i++)
          { int tru=0;   // la variable qu'on incrementera une fois que le mot est vide
    	      for (int j=0; j<stopword.size(); j++)
               { 
        	      if(words.get(i).equals(stopword.get(j)))
                        {
	                        tru++;
                        }
	             }
           if (tru==0)
              { 
              terms.add(words.get(i));
              }
          }
        // supprime le ال des termes
/*  for (int s=0; s<terms.size();s++)
     {
    	 if(terms.get(s).length()>2)
         {
         if (terms.get(s).charAt(0) == 'ا' && terms.get(s).charAt(1) == 'ل')
    	 {
    		  String c = removeCharAt(terms.get(s), 0);
                  //String c = remove.CharAt(terms.get(s), 0);
    		 //String t = remove.CharAt(c, 0);
    		 //terms.set(s, t);
    	 } 
     }
     } */
       // pour chaque mot abtenu, on applique un processus de steming une fois que le mot n'est pas dans l'AWN
       for (int i = 0; i<terms.size(); i++)
            {
               if(NmbrConcptAWN.get(terms.get(i))==0)
                 {
                   IMorphologyAnalyzer analyzer = MorphologyAnalyzerFactory.getAlkhalilImplementation();
                   List <WordMorphologyAnalysis> wordMorphologyAnalysis = analyzer.analyze(terms.get(i));
                   for (WordMorphologyAnalysis wordAnalysis: wordMorphologyAnalysis)
                       {
                        List<MorphologyAnalysis> listOfAnalysis = wordAnalysis.getStandardAnalysisList();
                        if (listOfAnalysis.isEmpty()== false)
                           {
                            terms.set(i,listOfAnalysis.get(0).getStem().getUnvoweledForm());
                           }
                        
                        }
                 }
            } 
       
       for (int i=0; i<terms.size(); i++)
          { int tru=0;   // la variable qu'on incrementera une fois que le mot est vide
    	      for (int j=0; j<stopword.size(); j++)
               { 
        	      if(terms.get(i).equals(stopword.get(j)))
                        {
	                        tru++;
                        }
	        }
           if (tru!=0)
              { 
              terms.remove(i);
              }
          }
     return terms;  
}
}
