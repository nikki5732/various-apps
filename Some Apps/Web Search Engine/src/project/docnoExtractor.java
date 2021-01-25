package project;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*
this class extracts docno from all the documents
 */

public class docnoExtractor {
    public ArrayList<String> docNumbers = new ArrayList<String>();

    public void extractdocno(String file) {

        // Markers for parsing elements.

        String docNoBegin = "<DOCNO>";
        String docNoEnd = "</DOCNO>";

        // reads through the files to extract docno
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;
            String docNo = "";

            while ((line = br.readLine()) != null) {


                //  Look for <DOCNO>
                if (line.length() > 6 && line.substring(0, 7).equalsIgnoreCase(docNoBegin)) {
                    docNo = line.replace(docNoBegin, "").replace(docNoEnd, "");
                    docNumbers.add(docNo);

                }


            }
            System.out.println("doc Numbers have been extracted succesfully");

        }catch(IOException e){
            e.printStackTrace();

        }

    }

    //get method for used for getting the docnumbers in the main class
    public ArrayList<String> getDocNo() {
        return this.docNumbers;
    }


}