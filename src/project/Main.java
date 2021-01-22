package project;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {


        ArrayList<String> docNumbers; //this arraylist will hold all the docNumbers (DOCNO)
        ArrayList<String> Topics20; // this array will hold the 20 topics from topic.txt
        ArrayList<String> docString = new ArrayList<>();

        final String path4 = "topics.txt"; //specifies the path for topics document

        //specifies the path for the web documents
        final String path1= "src/data/WT01.xml";
        final String path2= "src/data/WT02.xml";
        final String path3= "src/data/WT03.xml";

        docnoExtractor d = new docnoExtractor(); //we need this class to extract the docno
        System.out.println("WT01 doc number extraction:");
        d.extractdocno(path1);
        System.out.println("WT02 doc number extraction:");
        d.extractdocno(path2);
        System.out.println("WT03 doc number extraction:");
        d.extractdocno(path3);

        topicExtractor a = new topicExtractor(); //this class is used to extract topics from topic.txt
        a.extractTopics(path4);

        docNumbers = d.getDocNo(); //gets the docno created in the docnoextractor class
        Topics20 =a.getTopics(); //gets the topics created in the topicExtractor class

        fileCreator s = new fileCreator();//this class creates the html files
        s.createFolder("src/filesToIndex");
        //writes all the html files to filestoindex folder
        System.out.println("WT01 html file creation:");
        s.writeToFile("src/data/WT01.xml", "src/filesToIndex/");
        System.out.println("WT02 html file creation:");
        s.writeToFile("src/data/WT02.xml", "src/filesToIndex/");
        System.out.println("WT03 html file creation:");
        s.writeToFile("src/data/WT03.xml", "src/filesToIndex/");
        //this for loop converts all html documents into a string
        // and puts that string into an arraylist called docstring that stores all the documents
        System.out.println("the files are now being converted to a string and being placed in the arraylist");
       for ( int i =1; i<docNumbers.size()+1; i++) {
            try {
                BufferedReader in = new BufferedReader(new FileReader("src/filesToIndex/" + i + ".html"));
                String str;
                String doc = " ";
                while ((str = in.readLine()) != null) {
                    doc = doc + " " +str;
                }
                docString.add(doc);

            } catch (IOException e) {
                e.printStackTrace();
            }

       }
       System.out.println("all files were succesfully converted to a string");
        System.out.println("TOTAL number of doc Numbers " + docNumbers.size()); //prints the number of docnumbers used for checking purposes

        System.out.println("TOTAL number of docs " + docString.size()); //prints the number of documents used for checking purposes

        System.out.println("Indexing now beings. Results will be posted soon");
        System.out.println(" ");
        System.out.println(" ");



        StandardAnalyzer analyzer = new StandardAnalyzer();

        // 1. create the index
        Directory index = new MMapDirectory(Files.createTempDirectory("tempFolder").toAbsolutePath());

        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        IndexWriter w = new IndexWriter(index, config);
        FileWriter theWriter = new FileWriter("result.txt");
        for (int i = 0; i < docNumbers.size(); i++) {
            addDoc(w,docString.get(i), docNumbers.get(i));
        }
        w.close();
        IndexReader reader = DirectoryReader.open(index);
        // 2. query
        int num = 401; //the first topicnumber
        for (int j = 0; j <Topics20.size(); j++) {

            String querystr = args.length > 0 ? args[0] : Topics20.get(j);

            // the "doc" arg specifies the default field to use
            // when no field is explicitly specified in the query.
            Query q = new QueryParser("doc", analyzer).parse(querystr);

            // 3. search
            int hitsPerPage = 1000;

            IndexSearcher searcher = new IndexSearcher(reader);
            TopDocs docs = searcher.search(q, hitsPerPage);
            ScoreDoc[] hits = docs.scoreDocs;

            // 4. display results
            System.out.println(Topics20.get(j));
            System.out.println("Found " + hits.length + " hits.");

            for (int i = 0; i < hits.length; ++i) {
                int docId = hits[i].doc;

                Document doc = searcher.doc(docId);


                theWriter.write((num) +  " Q0 " + doc.get("docNo") + " " + (i+1) + " " +hits[i].score + " " + "g6" + "\n");

            }
            num++; //adds the topicnumber each time we got
        }

        // reader and writer are closed because there
        // is no need to access the documents any more.
        reader.close();
        theWriter.close();
        System.out.println( " \n \n The result file has been created succesfully");
    }

    private static void addDoc(IndexWriter w, String somedoc, String docNo) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("doc", somedoc, Field.Store.YES));

        // use a string field for DOCNO because we don't want it tokenized
        doc.add(new StringField("docNo", docNo, Field.Store.YES));
        w.addDocument(doc);
    }



}


