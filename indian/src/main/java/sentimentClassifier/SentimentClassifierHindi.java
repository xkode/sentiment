package sentimentClassifier;

import de.bwaldvogel.liblinear.*;
import org.apache.commons.cli.BasicParser;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class ClassifierHelperHindi {
    List<LinkedHashMap<Integer, Double>> featureList = new ArrayList<LinkedHashMap<Integer, Double>>();

    ClassifierHelperHindi(String dataset) throws IOException {
        BufferedReader readerTrain = new BufferedReader(new FileReader(new File(dataset)));

        int count = 0;
        while (readerTrain.readLine() != null) {
            count++;
        }

        for (int i = 0; i < count; i++) {
            featureList.add(i, new LinkedHashMap<Integer, Double>());
        }

    }

    public void setHashMap(int start, List<LinkedHashMap<Integer, Double>> hMap) {
        for (int i = 0; i < hMap.size(); i++) {
            for (Map.Entry<Integer, Double> entry : hMap.get(i).entrySet()) {
                featureList.get(i).put(start + entry.getKey(), entry.getValue());
            }
        }
    }

    public List<LinkedHashMap<Integer, Double>> getList() {
        return featureList;
    }
}

public class SentimentClassifierHindi {

    static String rootDirectory;
    static List<LinkedHashMap<Integer, Double>> trainingFeature;
    static List<LinkedHashMap<Integer, Double>> testFeature;


    SentimentClassifierHindi(int option, String trainFile, String testFile) throws IOException {

        rootDirectory = System.getProperty("user.dir");
        mainClassifierFunction(option, trainFile, testFile);

        //rootDirectory = "D:\\Course\\Semester VII\\Internship\\sentiment\\indian";
        //return generateFeature();
    }

    private int generateFeature(int option, String trainFile, String testFile) throws IOException {
        //TRAINING SET
        ClassifierHelperHindi trainingObject = new ClassifierHelperHindi(rootDirectory+"\\dataset\\"+trainFile);


        //TESTING SET
        ClassifierHelperHindi testObject = null;
        if(option == 2|| option == 3)
        {
            testObject = new ClassifierHelperHindi(rootDirectory+"\\dataset\\"+testFile);
        }

        //POSHindi FEATURE
        int start = 0;
        POSHindi posObject = new POSHindi(rootDirectory);
        //trainingObject.setHashMap(start, posObject.getTrainingList());

        //N GRAM FEATURE
        //start += posObject.getFeatureCount();
        Ngram ngramObject = new Ngram(rootDirectory);
        trainingObject.setHashMap(start, ngramObject.getTrainingList());
        if(option == 2|| option == 3) {
            testObject.setHashMap(start, ngramObject.getTestList());
        }

        //SENTI WORDNET 
        /*start += ngramObject.getFeatureCount();
        SentiWordNetHindi sentiObject = new SentiWordNetHindi(rootDirectory);
        trainingObject.setHashMap(start, sentiObject.getTrainingList());
        if(option == 2|| option == 3) {
        testObject.setHashMap(start, sentiObject.getTestList());
        }*/

        //PREFIX 2 
        /*start += sentiObject.getFeatureCount();
        CharacterNgramPrefixSize2 pre2ob = new CharacterNgramPrefixSize2(rootDirectory);
        trainingObject.setHashMap(start, pre2ob.getTrainingList());

        //SUFFIX 2 
        start += pre2ob.getFeatureCount();
        CharacterNgramSuffixSize2 suf2ob = new CharacterNgramSuffixSize2(rootDirectory);
        trainingObject.setHashMap(start, suf2ob.getTrainingList());*/

        //PREFIX 3 
        /*start += sentiObject.getFeatureCount();
        CharacterNgramPrefixSize3 pre3ob = new CharacterNgramPrefixSize3(rootDirectory);
        trainingObject.setHashMap(start, pre3ob.getTrainingList());
        if(option == 2|| option == 3) {
        testObject.setHashMap(start, pre3ob.getTestList());
        }

        //SUFFIX 3 
        start += pre3ob.getFeatureCount();
        CharacterNgramSuffixSize3 suf3ob = new CharacterNgramSuffixSize3(rootDirectory);
        trainingObject.setHashMap(start, suf3ob.getTrainingList());
        if(option == 2|| option == 3) {
        testObject.setHashMap(start, suf3ob.getTestList());
        }


        //PREFIX 4 
        start +=  suf3ob.getFeatureCount();
        CharacterNgramPrefixSize4 pre4ob = new CharacterNgramPrefixSize4(rootDirectory);
        trainingObject.setHashMap(start, pre4ob.getTrainingList());
        if(option == 2|| option == 3) {
        testObject.setHashMap(start, pre4ob.getTestList());
        }


        //SUFFIX 4 
        start += pre4ob.getFeatureCount();
        CharacterNgramSuffixSize4 suf4ob = new CharacterNgramSuffixSize4(rootDirectory);
        trainingObject.setHashMap(start, suf4ob.getTrainingList());
        if(option == 2|| option == 3) {
        testObject.setHashMap(start, suf4ob.getTestList());
        }*/

        //PREFIX 5
        /*start +=  suf4ob.getFeatureCount();
        CharacterNgramPrefixSize5 pre5ob = new CharacterNgramPrefixSize5(rootDirectory);
        trainingObject.setHashMap(start, pre5ob.getTrainingList());

        //SUFFIX 5
        start += pre5ob.getFeatureCount();
        CharacterNgramSuffixSize5 suf5ob = new CharacterNgramSuffixSize5(rootDirectory);
        trainingObject.setHashMap(start, suf5ob.getTrainingList());*/

        //DT COOC FEATURE
        start += ngramObject.getFeatureCount();
        DTCOOCLexiconHindi dtCOOCObject = new DTCOOCLexiconHindi(rootDirectory);
        trainingObject.setHashMap(start, dtCOOCObject.getTrainingList());
        if(option == 2|| option == 3) {
            testObject.setHashMap(start, dtCOOCObject.getTestList());
        }

        //DT TWITTER FEATURE
        /*start += ngramObject.getFeatureCount();
        DTLexiconHindiTwitter dtTwitterObject = new DTLexiconHindiTwitter(rootDirectory);
        trainingObject.setHashMap(start, dtTwitterObject.getTrainingList());
        if(option == 2|| option == 3) {
        testObject.setHashMap(start, dtTwitterObject.getTestList());
        }*/

        /*///DT FEATURE
        /*start += ngramObject.getFeatureCount();
        DTHindi dtObject = new DTHindi(rootDirectory);
        trainingObject.setHashMap(start, dtObject.getTrainingList());

        //COOC FEATURE
        start += dtObject.getFeatureCount();
        COOCHindi coocObject = new COOCHindi(rootDirectory);
        trainingObject.setHashMap(start, coocObject.getTrainingList());*/

        //USERNAME URL HASHTAG FEATURE
        /*start += suf4ob.getFeatureCount();
        UsernameURLHashtag userUrlObject = new UsernameURLHashtag(rootDirectory);
        trainingObject.setHashMap(start, userUrlObject.getTrainingList());
        if(option == 2|| option == 3) {
        testObject.setHashMap(start, userUrlObject.getTestList());
        }*/

        trainingFeature = trainingObject.getList();
        if(option == 2|| option == 3) {
            testFeature = testObject.getList();
        }


        int finalSize = start + dtCOOCObject.getFeatureCount();

        return finalSize;


        /*System.out.println("TRAINING *************************************");
        for (int i = 0; i < trainingFeature.size(); i++)    //Print the feature values
        {
            //System.out.println(trainingFeature.get(i).size());
            System.out.println(trainingFeature.get(i));
        }

        System.out.println("TEST *************************************");
        for (int i = 0; i < testFeature.size(); i++)    //Print the feature values
        {
            //System.out.println(trainingFeature.get(i).size());
            System.out.println(testFeature.get(i));
        }*/
    }

    //public static void main(String[] args) throws IOException {
        private void mainClassifierFunction(int option, String trainFile, String testFile)throws IOException {
            //SentimentClassifierHindi this = new SentimentClassifierHindi();
            //int finalSize = this.SentimentClassifierHindi();
            int finalSize = this.generateFeature(option, trainFile, testFile);

            System.out.println("Hello sentiment!");

            // Create features
            Problem problem = new Problem();

            // Save X to problem
            double a[] = new double[this.trainingFeature.size()];
            File file = new File(rootDirectory + "\\dataset\\trainingLabels.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String read;
            int count = 0;
            while ((read = reader.readLine()) != null) {
                a[count++] = Double.parseDouble(read.toString());
            }

            Feature[][] trainFeatureVector = new Feature[trainingFeature.size()][finalSize];

            for (int i = 0; i < trainingFeature.size(); i++) {
                System.out.println(i + " trained.");
                for (int j = 0; j < finalSize; j++) {
                    if (trainingFeature.get(i).containsKey(j + 1)) {
                        trainFeatureVector[i][j] = new FeatureNode(j + 1, trainingFeature.get(i).get(j + 1));
                    } else {
                        trainFeatureVector[i][j] = new FeatureNode(j + 1, 0.0);
                    }
                }
            }

            problem.l = trainingFeature.size(); // number of training examples
            problem.n = finalSize; // number of features
            problem.x = trainFeatureVector; // feature nodes
            problem.y = a; // target values ----

            BasicParser bp = new BasicParser();

            SolverType solver = SolverType.L2R_LR_DUAL; // -s 7
            double C = 1.0;    // cost of constraints violation
            double eps = 0.001; // stopping criteria

            Parameter parameter = new Parameter(solver, C, eps);
            Model model = Linear.train(problem, parameter);
            File modelFile = new File("model");
            model.save(modelFile);

            //double[] val = new double[trainingFeature.size()];
            PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter(rootDirectory + "\\dataset\\predictedLabels.txt")));

            if (option == 1) {
                double[] val = new double[trainingFeature.size()];
                Linear.crossValidation(problem, parameter, 5, val);
                for (int i = 0; i < trainingFeature.size(); i++) {
                    write.println(val[i]);
                }
                write.close();
                return;
            }

            //LinkedHashMap<String, Double>weight = new LinkedHashMap<String, Double>();

            // load model or use it directly
            if (option == 2 || option == 3) {
                model = Model.load(modelFile);
                //BufferedReader testFile = new BufferedReader(new InputStreamReader(new FileInputStream(rootDirectory + "\\dataset\\hindiTest.txt"), "UTF-8"));
                for (int i = 0; i < testFeature.size(); i++) {
                    Feature[] instance = new Feature[testFeature.get(i).size()];
                    int j = 0;
                    for (Map.Entry<Integer, Double> entry : testFeature.get(i).entrySet()) {
                        instance[j++] = new FeatureNode(entry.getKey(), entry.getValue());
                    }

                    double prediction = Linear.predict(model, instance);
                    //String id = testFile.readLine().split("\t")[0];
                    //write.println(id+"\t"+prediction);
                    write.println(prediction);
                }

                write.close();
                return;
            }
        }
}