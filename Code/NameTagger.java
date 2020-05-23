import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.Scanner;

import opennlp.tools.stemmer.PorterStemmer;
public class NameTagger {
	
	public static void main(String[] args) throws IOException
	{
		NameTagger nametag = new NameTagger();
		
		//PorterStemmer porterStemmer = new PorterStemmer();
		FeatureBuilder fb = new FeatureBuilder();
		
		
		File file = new File("C:\\Users\\Admin\\Desktop\\NYU Courant(2nd semester)\\NLP\\NLP Assignment 6\\CONLL_train.pos-chunk-name");
		List<String> lines = Files.readAllLines(file.toPath(),StandardCharsets.UTF_8);
		
	    BufferedWriter enhancedFeature_poschunkname = new BufferedWriter(new FileWriter("C:\\\\Users\\\\Admin\\\\Desktop\\\\NYU Courant(2nd semester)\\\\NLP\\\\NLP Assignment 6\\\\enhancedFeature_poschunkname.txt"));
		int index = 0;
	
		while(index<lines.size()) {
			StringBuilder test = new StringBuilder();
			String str = lines.get(index++);
			
			
			while(str!=null && !str.equals("")) {
				test.append(str+"###");
				str = lines.get(index++);	
			}
			
		  
		  	
		  ArrayList<String> predictedtags = fb.buildFeatureVector(test.toString());
		  for(String s:predictedtags) { 
			  enhancedFeature_poschunkname.write(s);
			  enhancedFeature_poschunkname.write("\n"); 
		      //System.out.println(s);
		  }
		  enhancedFeature_poschunkname.write("\n"); 
		  //System.out.println(); 
		  }
	
		  enhancedFeature_poschunkname.close();
		  
		  // Now making enhanced feature file for the test.pos chunk file
		  
		    File file2 = new File("C:\\Users\\Admin\\Desktop\\NYU Courant(2nd semester)\\NLP\\NLP Assignment 6\\CONLL_test.pos-chunk");
			List<String> lines2 = Files.readAllLines(file2.toPath(),StandardCharsets.UTF_8);
			
		    BufferedWriter enhancedFeature_poschunk = new BufferedWriter(new FileWriter("C:\\\\Users\\\\Admin\\\\Desktop\\\\NYU Courant(2nd semester)\\\\NLP\\\\NLP Assignment 6\\\\enhancedFeature_poschunk.txt"));
			int index2 = 0;
		
			while(index2<lines2.size()) {
				StringBuilder test = new StringBuilder();
				String str = lines2.get(index2++);
				
				
				while(str!=null && !str.equals("")) {
					test.append(str+"###");
					str = lines2.get(index2++);
				}
				
				
			  ArrayList<String> predictedtags_poschunk = fb.buildFeatureVector_test(test.toString());
			  for(String s:predictedtags_poschunk) { 
				  enhancedFeature_poschunk.write(s);
				  enhancedFeature_poschunk.write("\n"); 
			//      System.out.println(s);
			  }
			  enhancedFeature_poschunk.write("\n"); 
			  //System.out.println(); 
			  }
		
			  enhancedFeature_poschunk.close();
		  
		  
		  MEtrain me = new MEtrain();
		  String datafile = "C:\\\\Users\\\\Admin\\\\Desktop\\\\NYU Courant(2nd semester)\\\\NLP\\\\NLP Assignment 6\\\\enhancedFeature_poschunkname.txt";
		  String modelfile = "C:\\Users\\Admin\\Desktop\\NYU Courant(2nd semester)\\NLP\\NLP Assignment 6\\MAXENT_model";
		  me.MT(datafile, modelfile);
		  
		  MEtag metag = new MEtag();
		  String datafile_poschunk = "C:\\\\Users\\\\Admin\\\\Desktop\\\\NYU Courant(2nd semester)\\\\NLP\\\\NLP Assignment 6\\\\enhancedFeature_poschunk.txt";
		  //String responseFileName = "C:\\\\Users\\\\Admin\\\\Desktop\\\\NYU Courant(2nd semester)\\\\NLP\\\\NLP Assignment 6\\\\hsw268_hw6\\\\response.name";
		  //metag.ME_Tag(datafile_poschunk, modelfile, responseFileName);
		  
		  //Tagging the test file
		  String responseFileName1 = "C:\\\\Users\\\\Admin\\\\Desktop\\\\NYU Courant(2nd semester)\\\\NLP\\\\NLP Assignment 6\\\\hsw268_hw6\\\\CONLL_test.name";
		  metag.ME_Tag(datafile_poschunk, modelfile, responseFileName1);
		  
		 
	}
}

