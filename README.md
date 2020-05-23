Using different features and its combinations such as:
1)PrevToken
2)CurrentToken
3)NextToken
4)PrevToken POS
5)PrevToken Chunk
6)CurrentToken POS
7)CurrentToken Chunk
8)Next Next Token
9)Current Prev Token
10)Current Next Token
11)Prev NameTag
12)Lemmas
13)Shape of the Tokens
14)IsUpperCase
15)IsLowerCase
16)prefix3
17)prefix4
18)prefix5

Combinations used and their scores:
1)token, POS, chunk, shape Token, prevnametag, nextToken_POS, prevToken, nextToken, nextnextToken, Current Prev Token, Current Next Token, IsUpperCase, IsLowerCase
  - Accuracy = 96.80
  - F1 Score = 81.44

2)token, POS, chunk, prevnametag, prevToken, nextToken, nextnextToken, CurPrev, CurNext
  - Accuracy = 96.01
  - F1 Score = 77.93

3)token, POS, chunk, prevToken, nextToken
  - Accuracy = 95.60
  - F1 Score = 75.34

4)token, POS, chunk, nextToken, prevToken, nextToken POS, prevToken POS
  - Accuracy = 96.01
  - F1 Score = 74.04

5)token, POS, chunk, prevToken, prevPOS, prev Chunk
  - Accuracy = 95.77
  - F1 Score = 72.92

6)token, POS, chunk, shape Token, prevnametag, nextToken_POS, prevToken, nextToken, nextnextToken, Current Prev Token, Current Next Token, IsUpperCase, IsLowerCase, prefix4
  - Accuracy = 96.61
  - F1 Score = 80.93

How to run the program
I have 4 classes -  1)NameTagger, 2)MEtrain, 3)MEtag, 4)FeatureBuilder
Step1 : Initially train the CONLL_train.pos-chunk-name file in the NameTagger class and build enhancedFeature_poschunkname. The path of CONLL_train.pos-chunk-name is to be given in the main method od NameTagger Class. (in variable file)

Step 2: Now build enhancedFeature_poschunk file using the CONLL_test.pos-chunk in the NameTagger class. The path of CONLL_test.pos-chunk is to be given in the main method of NameTagger Class. (in variable file2)

Step 3: Created an object of MEtrain class in NameTagger class and gave the MT method of the MEtrain class datafile(enhancedFeature_poschunkname.txt) and modelfile(MAXENT_model) which will get created by MT method
The path of enhancedFeature_poschunkname.txt is to be given to datafile(first parameter of MT method). The path of empty file is given to modelfile(2nd parameter of MT method), in which the MAXENT model would get written

Step 4: Now Created an object of MEtag class in NameTagger class and gave the ME_tag method of the MEtag class datafile(enhancedFeature_poschunk.txt) and modelfile(MAXENT_model). This will the returned the tagged file called CONLL_test.name. 
The path of enhancedFeature_poschunk.txt is to be given to datafile_poschunk(first parameter of ME_Tag method). The path of empty file is given to responseFileName1(3rd parameter of ME_Tag method), in which the output would get written

Step 5: To run the code please specify the file paths as mentioned as above and put all the four java classes files in the same folder
You will need required librares to import following: 
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
and also would require standford-corenlp-3.9.2-models-english and standford-corenlp-3.9.2-models jars
