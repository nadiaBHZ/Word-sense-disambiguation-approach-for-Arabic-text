package desambiguation;

import java.util.List;

import desambiguation.AWN;

public class NmbrConcptAWN {
	public static int get(String term)
	{	AWN awn = new AWN("\\Users\\nadia\\Documents\\upc_db_dic.xml" ,false);
	     int i = 0;
		    List<String> ListWordId = awn.Get_List_Word_Id_From_Value(term);
		    i = ListWordId.size();
		  return i;
	}

}
