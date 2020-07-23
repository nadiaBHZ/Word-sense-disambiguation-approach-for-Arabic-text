package desambiguation;

import java.util.ArrayList;
import java.util.List;

import desambiguation.AWN;

// méthode qui prend un terme ambigue et le sysnsetId d'un terme non ambigue pour argument
//attribue le concept approprié au terme ambigue

public class desambLocal {
 List<String> BestSens = new ArrayList<>();
    public desambLocal(List<Integer> IndiceParagMa, List<List<List<String>>> VectorSens, List<List<List<String>>> ContxLocal, List<List<String>> SynsetIDVectorSens) 
  {  
  for(int i =0; i<VectorSens.size(); i++)
  {
    String MeilleurSens = SynsetIDVectorSens.get(i).get(0);
    List<Double> proximityLocal = new ArrayList<>();
    for(int j = 0; j<VectorSens.get(i).size();j++)
    {
     double supL = 0 ;
        
      for(int l = 0; l<ContxLocal.get(IndiceParagMa.get(i)).size(); l++)
        {
          if(similarityVector.get(VectorSens.get(i).get(j), ContxLocal.get(IndiceParagMa.get(i)).get(l))< 45)
                  {
                  supL++;
                  }
        }
        
        proximityLocal.add(supL/ContxLocal.get(IndiceParagMa.get(i)).size());  
    }
    double meilleurScore= proximityLocal.get(0);
    for(int j =1 ; j<proximityLocal.size();j++)
    {
     if(proximityLocal.get(j)>meilleurScore)
     {
     meilleurScore=proximityLocal.get(j);
     MeilleurSens=SynsetIDVectorSens.get(i).get(j);
     }
    }
    BestSens.add(i,MeilleurSens);
  }
  
  }
    public  List<String> get()
    {
    return BestSens;
    }
}
