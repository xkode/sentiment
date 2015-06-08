package de.tudarmstadt.lt.sentiment;

import java.io.*;

/**
 * Created by krayush on 02-Jun-15.
 */
public class PolarityLabels {

    private void generateTestLabels() {
        try {
            BufferedReader read = new BufferedReader(new FileReader(new File("E:\\COURSE\\Semester VII\\Internship\\sentiment\\dataset\\Test_Restaurants_Cleansed.txt")));
            String line = null;
            PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter("E:\\COURSE\\Semester VII\\Internship\\sentiment\\dataset\\testLabels.txt")));

            while ((line = read.readLine()) != null) {
                line = line.replace("\n", "").replace("\r", "");
                String words[] = line.split("\\|");
                if (words.length == 4) {
                    continue;
                }
                if (words[6].compareTo("negative") == 0) {          //words[5] for laptop set
                    write.println("0");
                } else if (words[6].compareTo("positive") == 0) {
                    write.println("1");
                } else if (words[6].compareTo("neutral") == 0) {
                    write.println("2");
                }
            }
            read.close();
            write.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void generateTrainingLabels() {
        try {
            BufferedReader read = new BufferedReader(new FileReader(new File("E:\\COURSE\\Semester VII\\Internship\\sentiment\\dataset\\Train_Restaurants_Cleansed.txt")));
            String line = null;
            PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter("E:\\COURSE\\Semester VII\\Internship\\sentiment\\dataset\\trainingLabels.txt")));

            while ((line = read.readLine()) != null) {
                line = line.replace("\n", "").replace("\r", "");
                String words[] = line.split("\\|");
                if (words.length == 4) {
                    continue;
                }
                if (words[6].compareTo("negative") == 0) {
                    write.println("0");
                } else if (words[6].compareTo("positive") == 0) {
                    write.println("1");
                } else if (words[6].compareTo("neutral") == 0) {
                    write.println("2");
                }
            }
            read.close();
            write.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        PolarityLabels obj = new PolarityLabels();
        obj.generateTrainingLabels();
        //obj.generateTestLabels();
    }

}
