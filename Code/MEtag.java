// Wrapper for maximum-entropy tagging

// NYU - Natural Language Processing - Prof. Grishman

// invoke by:  java  MEtag dataFile  model  responseFile

import java.io.*;
import java.util.regex.Matcher;
import opennlp.maxent.*;
import opennlp.maxent.io.*;

// reads line with tab separated features
//  writes feature[0] (token) and predicted tag

public class MEtag {

    public void ME_Tag(String dataFileName, String modelFileName, String responseFileName) {
	try {
	    GISModel m = (GISModel) new SuffixSensitiveGISModelReader(new File(modelFileName)).getModel();
	    BufferedReader dataReader = new BufferedReader (new FileReader (dataFileName));
	    PrintWriter responseWriter = new PrintWriter (new FileWriter (responseFileName));
	    String priorTag = "#";
	    String line;
	    while ((line = dataReader.readLine()) != null) {
		if (line.equals("")) {
		    responseWriter.println();
		    priorTag = "#";
		} else {
		    line = line.replaceAll("@@", Matcher.quoteReplacement(priorTag));
		    String[] features = line.split("\t");
		    String tag = m.getBestOutcome(m.eval(features));
		    responseWriter.println(features[0] + "\t" + tag);
		    priorTag = tag;
		}
	    }
	    responseWriter.close();
	} catch (Exception e) {
	    System.out.print("Error in data tagging: ");
	    e.printStackTrace();
	}
    }

}