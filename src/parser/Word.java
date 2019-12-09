package parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.HeaderStories;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class Word {
	 public static void readMyDocument(String fileName){
	        POIFSFileSystem fs = null;
	        try {
	            fs = new POIFSFileSystem(new FileInputStream(fileName));
	            HWPFDocument doc = new HWPFDocument(fs);
	 
	            /** Read the content **/
	            readParagraphs(doc);
	 
	           // int pageNumber=1;
	 
	            /** We will try reading the header for page 1**/
	            //readHeader(doc, pageNumber);
	 
	            /** Let's try reading the footer for page 1**/
	           // readFooter(doc, pageNumber);
	 
	            /** Read the document summary**/
	           // readDocumentSummary(doc);
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }  
	 
	    public static void readParagraphs(HWPFDocument doc) throws Exception{
	        WordExtractor we = new WordExtractor(doc);
	 
	        /**Get the total number of paragraphs**/
	       /* String[] paragraphs = we.getParagraphText();
	        System.out.println("Total Paragraphs: "+paragraphs.length);
	        File fil = new File("D:\\abstract4.txt"); 
         Writer output = new BufferedWriter(new FileWriter(fil));
	        for (int i = 0; i < paragraphs.length; i++) {
	 
	            System.out.println("Length of paragraph "+(i +1)+": "+ paragraphs[i].length());
	            System.out.println(paragraphs[i].toString());
	            
	            output.write(paragraphs[i].toString()); 
	           
	 
	        }
	        output.close();*/
	        String paragraph=we.getText();
	       // System.out.println(paragraph);
	        File fil = new File("D:\\abstract4.txt"); 
	        Writer output = new BufferedWriter(new FileWriter(fil));
	        output.write(paragraph.toString());
	        output.close();
	    }
	 
	   /* public static void readHeader(HWPFDocument doc, int pageNumber){
	        HeaderStories headerStore = new HeaderStories( doc);
	        String header = headerStore.getHeader(pageNumber);
	        System.out.println("Header Is: "+header);
	 
	    }
	 
	    public static void readFooter(HWPFDocument doc, int pageNumber){
	        HeaderStories headerStore = new HeaderStories( doc);
	        String footer = headerStore.getFooter(pageNumber);
	        System.out.println("Footer Is: "+footer);
	 
	    }
	 
	    public static void readDocumentSummary(HWPFDocument doc) {
	        DocumentSummaryInformation summaryInfo=doc.getDocumentSummaryInformation();
	        String category = summaryInfo.getCategory();
	        String company = summaryInfo.getCompany();
	        int lineCount=summaryInfo.getLineCount();
	        int sectionCount=summaryInfo.getSectionCount();
	        int slideCount=summaryInfo.getSlideCount();
	 
	        System.out.println("---------------------------");
	        System.out.println("Category: "+category);
	        System.out.println("Company: "+company);
	        System.out.println("Line Count: "+lineCount);
	        System.out.println("Section Count: "+sectionCount);
	        System.out.println("Slide Count: "+slideCount);
	 
	    }*/
	 
	
}
