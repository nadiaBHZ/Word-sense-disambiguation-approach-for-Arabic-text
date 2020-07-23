/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desambiguation;

import java.util.List;

/**
 *
 * @author Naadia
 */
public class similarityVector {
    
    public static double get(List<String> V1, List<String> V2)
    {
    double num=0;
    double normCareeV1 = 0;
    double normCareeV2 = 0;
    double normV1, normV2;
     double p = Math.PI;
    
    
     for(int i = 0; i<V1.size();i++)
    {
      
         double c =Double.parseDouble(V1.get(i));
         double f =Double.parseDouble(V2.get(i));
              
    num = num + (c*f);
    normCareeV1= normCareeV1+(c*c);
    normCareeV2 = normCareeV2+(c*f);
    }
    normV1= Math.sqrt(normCareeV1);
    normV2= Math.sqrt(normCareeV2);
    double cosV1V2 = (num/(normV1*normV2));
    double a = Math.acos(cosV1V2);
    double h = 180*a/p;

   double x = Math.round(h);
   return x;
   
    }          
}
