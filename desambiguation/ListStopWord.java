package desambiguation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import safar.util.tokenization.impl.SAFARTokenizer;

public class ListStopWord {
	public static List<String> get() {
		List<String> motvide= new ArrayList<>(); 
                String line;
                String line1;
                String line2;
		String text = "لقد ألا  حوله  جانب أنها أيضا بأنها  أكثر وذلك خلال و جميع  ان بعد ضد  يلي الى في من حتى وهو يكون به وليس أحد على وكان تلك كذلك التي وبين فيها عليها إن وعلى لكن عن مساء ليس منذ الذي أما حين ومن لا ليسب وكانت أي ما عنه حول دون مع لكنه ولكن له هذا والتي فقط ثم هذه أنه تكون قد بين جدا لن نحو كان لهم لأن اليوم لم هؤلاء فإن فيه ذلك لو عند اللذين كل بد لدى وثي أن ومع فقد بل هو عنها منه بها وفي فهو تحت لها أو إذ علي عليه كما كيف هنا وقد كانت لذلك أمام هناك قبل معه يوم منها إلى إذا هل حيث هي اذا او و ما لا الي إلي مازال لازال لايزال مايزال اصبح أصبح أمسى امسى أضحى اضحى ظل مابرح مافتئ ماانفك بات صار ليس إن كأن ليت لعل لاسيما ولايزال الحالي ضمن اول وله ذات اي بدلا اليها انه الذين فانه وان والذي وهذا لهذا الا فكان ستكون مما  أبو بإن الذي اليه يمكن بهذا لدي  وأن  وهي وأبو آل الذي هن الذى فهكذا بعض قد فيما أجل بعض فيما بينها وذلك اي ومن خلال وهو وكذلك فلقد مما أولئك حيث إذ ذات بهذه بذلك عندما بعض البعض هى انهاأن تم حول غير مثل لها دون أنّ تحت  عامة عام أنّ  فوق يتم وفق";
                SAFARTokenizer  tokenizer = new SAFARTokenizer();
                String[] tokens = tokenizer.tokenize(text);
                for(int i =0; i<tokens.length; i++)
                    motvide.add(tokens[i]);
                
                // extraire les mots vide à partir du document allforms
                String filePath = "\\Users\\nadia\\Documents\\stopwordsallforms.txt";
		   	try{
		       InputStreamReader in = new InputStreamReader(new FileInputStream(filePath),Charset.forName("UTF-8"));
		       BufferedReader buff = new BufferedReader(in);		 
		   try{
		       while ((line1 = buff.readLine())!= null)
                       { 
		        String[] tokens1 = tokenizer.tokenize(line1);
                        motvide.add(tokens1[0]);
		       }
		       } finally {
		  	buff.close();
		   	}
		 	} catch (IOException ioe) {
		   	System.out.println("Erreur --" + ioe.toString());
                        }
                return motvide;
	 }

}
