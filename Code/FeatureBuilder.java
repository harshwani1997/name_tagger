import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import opennlp.tools.stemmer.PorterStemmer;


public class FeatureBuilder {

	public ArrayList<String> buildFeatureVector(String line)
	{
		ArrayList<String> list = new ArrayList<String>();
		
		ArrayList<String> nextTokens = getNextTokens(line);
		ArrayList<String> nextTokens_POS = getNextTokens(line);
		ArrayList<String> nextTokens_chunk = getNextTokens(line);
		
		ArrayList<String> nextnextTokens = getNextNextTokens(line);
		//ArrayList<String> lemmatization = lemmatization(line);
		
		ArrayList<String> shapes = getShape(line);
		
		ArrayList<String> prefix3 = getPreFix3(line);
		ArrayList<String> prefix4 = getPreFix4(line);
		ArrayList<String> prefix5 = getPreFix5(line);
		
		String prevToken = "@";
		String prevToken_POS = "@";
		String prevToken_chunk = "@";
		String prevToken_name = "@@";
		
		String[] tokens = line.split("###");
		int nextToken_counter=0;
		int nextTokenPOS_counter=0;
		int nextTokenChunk_counter=0;
		int nextnextToken_counter=0;
		
		int lemmas=0;
		int shape=0;
		
		int pf3=0;
		int pf4=0;
		int pf5=0;
		
		for(String tokenline:tokens)
		{
			String[] linearray = tokenline.split("\t");	
			String token = linearray[0];
			String POS = linearray[1];
			String chunk = linearray[2];
			String name = linearray[3];
			String nextToken = nextTokens.get(nextToken_counter++);
			String nextToken_POS = nextTokens_POS.get(nextTokenPOS_counter++);
			String nextToken_Chunk = nextTokens_chunk.get(nextTokenChunk_counter++);
			
			String nextnextToken = nextnextTokens.get(nextnextToken_counter++);
			//String lemma = lemmatization.get(lemmas++);
			String shapeToken = shapes.get(shape++);
			boolean uppercase = getUpperCase(token);
			boolean lowercase = getLowerCase(token);
			
			String PF3 = prefix3.get(pf3++);
			String PF4 = prefix4.get(pf4++);
			String PF5 = prefix5.get(pf5++);
			
			//(77.85)//String add = token + "\t" + "POS=" + POS + "\t" + "chunk=" + chunk + "\t"+ "prevnametag=" + prevToken_name + "\t" + "prevToken=" + prevToken + "\t" + "nextToken=" + nextToken + "\t" + "nextnextToken=" + nextnextToken + "\t" + "CurPrev=" + token + prevToken + "\t" + "CurNext=" + token + nextToken + "\t" + name;
			
			//(75.34)//String add = token + "\t" + "POS=" + POS + "\t" + "chunk=" + chunk  + "\t" + "prevToken=" + prevToken + "\t" + "nextToken=" + nextToken + "\t" + name;
			
			//(74.04)//String add = token + "\t" + "POS:" + POS + "\t" + "chunk:" + chunk + "\t" + "nextToken=" + nextToken + "\t" + "prevToken:" + prevToken + "\t" + "nextTokenPOS=" + nextToken_POS + "\t" + "prevTokenPOS=" + prevToken_POS + "\t" + name;
			
			//String add = token + "\t" + "POS=" + POS + "\t" + "chunk=" + chunk + "\t" + "lemma:=" + lemma + "\t" + "prevNameTag=" + prevToken_name + "\t" + "nextTokenPOSChunk=" + nextToken_POS + " " + nextToken_Chunk + "\t" + "prevToken=" + prevToken + "\t" + "nextToken=" + nextToken + "\t" + "nextnextToken=" + nextnextToken + "\t" + "CurPrev=" + token + " " + prevToken + "\t" + name;
			
			String add = token + "\t" + "POS=" + POS + "\t" + "chunk=" + chunk + "\t" + "shapeToken=" + shapeToken + "\t"+ "prevnametag=" + prevToken_name + "\t" + "nextToken_POS=" + nextToken_POS + "\t" + "prevToken=" + prevToken + "\t" + "nextToken=" + nextToken + "\t" + "nextnextToken=" + nextnextToken + "\t" + "CurPrev=" + token + " " + prevToken + "\t" + "CurNext=" + token + " " + nextToken + "\t" + "isUpperCase=" + uppercase + "\t" + "isLowerCase=" + lowercase + "\t" + name;
			
			//String add = token + "\t" + "POS=" + POS + "\t" + "chunk=" + chunk + "\t" + "shapeToken=" + shapeToken + "\t"+ "prevnametag=" + prevToken_name + "\t" + "nextToken_POS=" + nextToken_POS + "\t" + "prevToken=" + prevToken + "\t" + "nextToken=" + nextToken + "\t" + "nextnextToken=" + nextnextToken + "\t" + "isUpperCase=" + uppercase + "\t" + "isLowerCase=" + lowercase + "\t" + "prefix4=" + prefix4 + "\t" + name;
			
			list.add(add);

			prevToken = token;
			prevToken_POS = POS;
			prevToken_chunk = chunk;
			prevToken_name = name;
		}
	
		return list;
	}
	

	public ArrayList<String> buildFeatureVector_test(String line)
	{
		ArrayList<String> list = new ArrayList<String>();
		String prevToken = "@";
		String prevToken_POS = "@";
		String prevToken_chunk = "@";
		String prevToken_name = "@@";
		
		ArrayList<String> nextTokens = getNextTokens(line);
		ArrayList<String> nextTokens_POS = getNextTokens(line);
		ArrayList<String> nextTokens_chunk = getNextTokens(line);
		ArrayList<String> nextnextTokens = getNextNextTokens(line);
		
		//ArrayList<String> lemmatization = lemmatization(line);
		ArrayList<String> shapes = getShape(line);
		
		ArrayList<String> prefix3 = getPreFix3(line);
		ArrayList<String> prefix4 = getPreFix4(line);
		ArrayList<String> prefix5 = getPreFix5(line);
		
		int nextToken_counter=0;
		int nextTokenPOS_counter=0;
		int nextTokenChunk_counter=0;
		int nextnextToken_counter=0;
		
		int shape=0;
		int lemmas=0;
		
		int pf3=0;
		int pf4=0;
		int pf5=0;
		
		String[] tokens = line.split("###");
		for(String tokenline:tokens)
		{
			String[] linearray = tokenline.split("\t");	
			String token = linearray[0];
			boolean uppercase = getUpperCase(token);
			boolean lowercase = getLowerCase(token);
			
			String POS = linearray[1];
			String chunk = linearray[2];
			String nextToken = nextTokens.get(nextToken_counter++);
			String nextToken_POS = nextTokens_POS.get(nextTokenPOS_counter++);
			String nextToken_Chunk = nextTokens_chunk.get(nextTokenChunk_counter++);
			String nextnextToken = nextnextTokens.get(nextnextToken_counter++);
			
			//String lemma = lemmatization.get(lemmas++);
			String shapeToken = shapes.get(shape++);
			
			String PF3 = prefix3.get(pf3++);
			String PF4 = prefix4.get(pf4++);
			String PF5 = prefix5.get(pf5++);
			
			//(77.85)//String add = token + "\t" + "POS=" + POS + "\t" + "chunk=" + chunk + "\t" + "prevnametag=" + prevToken_name + "\t" + "prevToken=" + prevToken + "\t" + "nextToken=" + nextToken + "\t" + "nextnextToken=" + nextnextToken + "\t" + "CurPrev=" + token + prevToken + "\t" + "CurNext=" + token + nextToken;
			
			//(81.26)//String add = token + "\t" + "POS=" + POS + "\t" + "chunk=" + chunk + "\t" + "shapeToken=" + shapeToken + "\t"+ "prevnametag=" + prevToken_name + "\t" + "nextToken_POS=" + nextToken_POS + "\t" + "prevToken=" + prevToken + "\t" + "nextToken=" + nextToken + "\t" + "nextnextToken=" + nextnextToken + "\t" + "CurPrev=" + token + " " +prevToken + "\t" + "CurNext=" + token + " " + nextToken;
		   
			//(75.34)//String add = token + "\t" + "POS=" + POS + "\t" + "chunk=" + chunk  + "\t" + "prevToken=" + prevToken + "\t" + "nextToken=";
		    
			//(74.04)//String add = token + "\t" + "POS:" + POS + "\t" + "chunk:" + chunk + "\t" + "nextToken=" + nextToken + "\t" + "prevToken:" + prevToken + "\t" + "nextTokenPOS=" + nextToken_POS + "\t" + "prevTokenPOS=" + prevToken_POS;

			String add = token + "\t" + "POS=" + POS + "\t" + "chunk=" + chunk + "\t" + "shapeToken=" + shapeToken + "\t"+ "prevnametag=" + prevToken_name + "\t" + "nextToken_POS=" + nextToken_POS + "\t" + "prevToken=" + prevToken + "\t" + "nextToken=" + nextToken + "\t" + "nextnextToken=" + nextnextToken + "\t" + "CurPrev=" + token + " " + prevToken + "\t" + "CurNext=" + token + " " + nextToken + "\t" + "isUpperCase=" + uppercase + "\t" + "isLowerCase=" + lowercase;
			list.add(add);

			prevToken = token;
			prevToken_POS = POS;
			prevToken_chunk= chunk;
		}
		return list;
	}
	
	public ArrayList<String> getNextTokens(String line)
	{
		ArrayList<String> next = new ArrayList<String>();
		String[] tokenline = line.split("###");
		String nextToken = "";
		for(int i=0;i<tokenline.length-1;i++)
		{
			String[] linearray1 = tokenline[i].split("\t");
			String[] linearray2 = tokenline[i+1].split("\t");
			nextToken = linearray2[0];
			next.add(nextToken);
		}
		
		next.add("#");
		String nextOfLast = "#";
		return next;
	}
	
	public ArrayList<String> getNextNextTokens(String line)
	{
		ArrayList<String> nextnext = new ArrayList<String>();
		String[] tokenline = line.split("###");
		String nextnextToken = "";
		for(int i=0;i<tokenline.length-2;i++)
		{
			String[] linearray1 = tokenline[i].split("\t");
			String[] linearray2 = tokenline[i+2].split("\t");
			nextnextToken = linearray2[0];
			nextnext.add(nextnextToken);
		}
		
		nextnext.add("#");
		nextnext.add("#");
		String nextOfLast = "#";
		String secondlastToken = "harsh";
		String lastToken = "harsh";
		return nextnext;
	}
	
	
	public ArrayList<String> getNextTokens_POS(String line)
	{
		ArrayList<String> next = new ArrayList<String>();
		String[] tokenline = line.split("###");
		String nextToken_POS = "";
		for(int i=0;i<tokenline.length-1;i++)
		{
			String[] linearray1 = tokenline[i].split("\t");
			String[] linearray2 = tokenline[i+1].split("\t");
			nextToken_POS = linearray2[1];
			next.add(nextToken_POS);
		}
		
		next.add("#");
		String nextOfLast = "#";
		return next;
	}
	
	
	public ArrayList<String> getNextTokens_chunks(String line)
	{
		ArrayList<String> next = new ArrayList<String>();
		String[] tokenline = line.split("###");
		String nextToken_chunk = "";
		for(int i=0;i<tokenline.length-1;i++)
		{
			String[] linearray1 = tokenline[i].split("\t");
			String[] linearray2 = tokenline[i+1].split("\t");
			nextToken_chunk = linearray2[2];
			next.add(nextToken_chunk);
		}
		
		next.add("#");
		String nextOfLast = "#";
		return next;
	}
	
	
	public ArrayList<String> lemmatization(String line)
	{
      ArrayList<String> lemma = new ArrayList<String>();
	  Document doc = new Document(line);
      for(Sentence sent:doc.sentences())
      {
    	  for(String lem:sent.lemmas())
    	  {
    		  lemma.add(lem);
    	  }
      }
      return lemma;
	}
	
	public ArrayList<String> getShape(String line)
	{
		ArrayList<String> shape = new ArrayList<String>();
		String[] tokenline = line.split("###");
		
		for(int i=0;i<tokenline.length;i++)
		{
			String[] linearray = tokenline[i].split("\t");
			shape.add(getTokenShape(linearray[0]));
		}
		return shape;
	}
	
	public String getTokenShape(String token)
	{
		StringBuilder shape = new StringBuilder();
		for(char ch:token.toCharArray())
		{
			if(Character.isDigit(ch))
			{
				shape.append("d");
			}
			else if(Character.isUpperCase(ch))
			{
				shape.append("X");
			}
			else if(Character.isLowerCase(ch))
			{
				shape.append("x");
			}
			else 
			{
				shape.append(ch);
			}
		}
		return shape.toString();
	}
	
	private boolean getUpperCase(String token) {
		char ch = token.charAt(0);
		if(Character.isUpperCase(ch))
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	private boolean getLowerCase(String token) {
		char ch = token.charAt(0);
		if(Character.isLowerCase(ch))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public ArrayList<String> getPreFix3(String line)
	{
		ArrayList<String> prefix3 = new ArrayList<String>();
		String[] tokenline = line.split("###");
		
		for(int i=0;i<tokenline.length;i++)
		{
			String[] linearray = tokenline[i].split("\t");
			String token = linearray[0];
			if(token.length()<=3)
			{
				prefix3.add(token);
			}
			else
			{
				prefix3.add(token.substring(0, 3));
			}
		}
		return prefix3;
	}
	
	public ArrayList<String> getPreFix4(String line)
	{
		ArrayList<String> prefix4 = new ArrayList<String>();
		String[] tokenline = line.split("###");
		
		for(int i=0;i<tokenline.length;i++)
		{
			String[] linearray = tokenline[i].split("\t");
			String token = linearray[0];
			if(token.length()<=4)
			{
				prefix4.add(token);
			}
			else
			{
				prefix4.add(token.substring(0, 4));
			}
		}
		return prefix4;
	}
	
	public ArrayList<String> getPreFix5(String line)
	{
		ArrayList<String> prefix5 = new ArrayList<String>();
		String[] tokenline = line.split("###");
		
		for(int i=0;i<tokenline.length;i++)
		{
			String[] linearray = tokenline[i].split("\t");
			String token = linearray[0];
			if(token.length()<=5)
			{
				prefix5.add(token);
			}
			else
			{
				prefix5.add(token.substring(0, 5));
			}
		}
		return prefix5;
	}

	
}
