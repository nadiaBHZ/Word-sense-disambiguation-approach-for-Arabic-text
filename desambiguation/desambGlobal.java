package desambiguation;

import java.util.ArrayList;
import java.util.List;

import desambiguation.AWN;

// méthode qui prend un terme ambigue et le sysnsetId d'un terme non ambigue pour argument
//attribue le concept approprié au terme ambigue

public class desambGlobal {
 List<String> BestSens = new ArrayList<>();
    public desambGlobal(List<List<String>> ContxGlobal, List<List<List<String>>> VectorSens, List<List<String>> SynsetIDVectorSens) 
  {  
  for(int i =0; i<VectorSens.size(); i++)
  {
    String MeilleurSens = SynsetIDVectorSens.get(i).get(0);
    List<Double> proximityGlobal = new ArrayList<>();
    for(int j = 0; j<VectorSens.get(i).size();j++)
    {
     double supG = 0 ;
     for( int k=0; k<ContxGlobal.size(); k++)
     {
        if (similarityVector.get(VectorSens.get(i).get(j), ContxGlobal.get(k)) < 45) 
        {
        supG++;
        }
     }
        proximityGlobal.add(supG/ContxGlobal.size());  
    }
    
    double meilleurScore= proximityGlobal.get(0);
    for(int j =1 ; j<proximityGlobal.size();j++)
    {
     if(proximityGlobal.get(j)>meilleurScore)
     {
     meilleurScore=proximityGlobal.get(j);
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

