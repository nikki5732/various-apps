package project;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
this class extracts all the topics form topic.txt
 */

public class topicExtractor {
    ArrayList<String> words = new ArrayList<String>();
    public void extractTopics(String file){
        String line;
        String title = "<title>";
        String topicline;


        //extracts topics from topic.txt and puts them into an arrayList
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {

                if (line.length() > 8) {
                    if (line.substring(0, 7).equalsIgnoreCase(title)) {
                        topicline = line.substring(8);

                        words.add(topicline);

                    }
                }


            }


            System.out.println("Topics from topic.txt have been extracted succesfully");
        }catch(Exception e){
            e.printStackTrace();
        }


    }

    public ArrayList<String> getTopics() {
        return this.words;
    } //get method used for retrieving the topics
}
