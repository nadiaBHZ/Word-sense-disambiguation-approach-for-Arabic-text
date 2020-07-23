package desambiguation;

import java.util.ArrayList;
import java.util.List;

import desambiguation.AWN;

// méthode qui prend un terme ambigue et le sysnsetId d'un terme non ambigue pour argument
//attribue le concept approprié au terme ambigue

public class desamb {
 List<String> BestSens = new ArrayList<>();
 List<List<Double>> proximityLocal = new ArrayList<>();
 List<List<Double>> proximityGlobal = new ArrayList<>();
 List<List<List<Double>>> DistanceLocal = new ArrayList<>();
 List<List<List<Double>>> DistanceGlobal = new ArrayList<>();
 List<List<Double>> moyen = new ArrayList<>();
    public desamb(List<Integer> IndiceParagMa, List<List<String>> ContxGlobal, List<List<List<String>>> VectorSens, List<List<List<String>>> ContxLocal, List<List<String>> SynsetIDVectorSens) 
  {  
  for(int i =0; i<VectorSens.size(); i++)
  {  
    List<Double> tempmoyen = new ArrayList<>();
    String MeilleurSens = SynsetIDVectorSens.get(i).get(0);
    List<Double> tempproximityGlobal = new ArrayList<>();
    List<Double> tempproximityLocal = new ArrayList<>();
    List<List<Double>> tempDistance1 = new ArrayList<>();
  
    List<List<Double>> tempDistance2 = new ArrayList<>();
    
    for(int j = 0; j<VectorSens.get(i).size();j++)
    {
     double supG = 0 ;
     double supL = 0 ;
     List<Double> temp1 = new ArrayList<>();
     List<Double> temp2 = new ArrayList<>();
     for( int k=0; k<ContxGlobal.size(); k++)
     {
        temp1.add(similarityVector.get(VectorSens.get(i).get(j), ContxGlobal.get(k)));
         if (similarityVector.get(VectorSens.get(i).get(j), ContxGlobal.get(k)) < 90 && similarityVector.get(VectorSens.get(i).get(j), ContxGlobal.get(k))!= 0.0 ) 
        {
        supG++;
        }
     }
        tempDistance1.add(temp1);
        tempproximityGlobal.add(supG/ContxGlobal.size());
        
        for(int l = 0; l<ContxLocal.get(IndiceParagMa.get(i)).size(); l++)
        {
         temp2.add(similarityVector.get(VectorSens.get(i).get(j), ContxLocal.get(IndiceParagMa.get(i)).get(l)));
          if(similarityVector.get(VectorSens.get(i).get(j), ContxLocal.get(IndiceParagMa.get(i)).get(l))< 90 && similarityVector.get(VectorSens.get(i).get(j), ContxLocal.get(IndiceParagMa.get(i)).get(l))!= 0.0)
            {
             supL++;
            }  
        }
        tempDistance2.add(temp2);
        tempproximityLocal.add(supL/ContxLocal.get(IndiceParagMa.get(i)).size());  
    }
    
    for(int j = 0; j<VectorSens.get(i).size();j++)
    {
    tempmoyen.add((tempproximityLocal.get(j)+ tempproximityGlobal.get(j))/2);
    }
    double meilleurScore= tempmoyen.get(0);
    for(int j =1 ; j<tempmoyen.size();j++)
    {
     if(tempmoyen.get(j)>meilleurScore)
     {
     meilleurScore=tempmoyen.get(j);
     MeilleurSens=SynsetIDVectorSens.get(i).get(j);
     }
    }
    BestSens.add(i,MeilleurSens);
    proximityLocal.add(i,tempproximityLocal);
    proximityGlobal.add(i,tempproximityGlobal);
    moyen.add(i,tempmoyen);
    DistanceLocal.add(i,tempDistance2);
    DistanceGlobal.add(i,tempDistance1);
  }
  
  }
    public  List<String> get()
    {
    return BestSens;
    }
    public  List<List<Double>> getproximityL()
    {
    return proximityLocal;
    }
    public  List<List<Double>> getproximityG()
    {
    return proximityGlobal;
    }
    public  List<List<Double>> getmoyen()
    {
    return moyen;
    }
    public  List<List<List<Double>>> getdistancelocal()
    {
    return DistanceLocal;
    }
    public  List<List<List<Double>>> getdistanceGlobal()
    {
    return DistanceGlobal;
    }
}
