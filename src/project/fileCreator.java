package project;

import java.io.*;

/*
this class is responsible for creating all the html files that will be needed to index later
 */


public class fileCreator {

    //create a folder to save all html files
    public static void createFolder(String folderPath) {
        File file  = new File(folderPath);
        file.mkdirs();
    }
    //creates html files  from  <DOC> to its coreesponding end tag
     static int docNo = 1; //docnames start from 1 and increases thereafter
    public static void writeToFile(String readfile, String filePath) throws IOException{
        String docBegin = "<DOC>";
        String docEnd = "</DOC>";

        String content = "";
        String line = null;
        FileReader fr = new FileReader(readfile);
        BufferedReader reader = new BufferedReader(fr);


        while ((line = reader.readLine()) !=null){
            if(line.length()>4){
                if(line.substring(0,5).equalsIgnoreCase(docBegin)){
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath +docNo++ +".html", true));
                    writer.append("<DOC>");
                    writer.newLine();
                    content = reader.readLine();
                    while (content!=null){
                        if(content.contains(docEnd)){
                            writer.append("</DOC>");
                            writer.close();
                            break;
                        }else{
                            writer.append(content);
                            writer.newLine();
                            content = reader.readLine();
                        }
                    }
                }
            }
        }
        System.out.println("Individual HTML files have been created successfully");
    }

}
