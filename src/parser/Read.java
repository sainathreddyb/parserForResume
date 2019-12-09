package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Read {
	static  BufferedReader br;
	static String line;
	static String[] modules={"education qualification","strengths","extra curricular activities","cocurricular activities","weakness","skills","personal profile","experience"};
    /**
     * @param args
     * @throws IOException
     */
	
	public  int search(String str,String s) throws IOException
	{
		br = new BufferedReader(new FileReader("D://abstract4.txt"));

		System.out.println(s+str);
		line = br.readLine();
		if((!str.equals("intermediate"))&&(str!="btech")&&(!str.equals("ssc"))){
			System.out.println("not btech inter ssc");
		while(line!=null){
		if(line.toLowerCase().contains(s.toLowerCase())){
			System.out.println("found");
			return 1;
		}
		 line = br.readLine();
		}
		}
		else
		{
			System.out.println("entering correct loop");
			
			while(line!=null){
				if(line.toLowerCase().contains(str.toLowerCase())){
					String[] words=line.split(" ");
					for(int i=0;i<words.length;i++)
					{
						if(words[i].matches("(\\d+(\\.\\d+)?)%"))
						{
							System.out.println("number matches");
							words[i]=words[i].substring(0,words[i].length()-1);
							int k=(int)Float.parseFloat(words[i]);
							int j=Integer.parseInt(s);
							if(k>=j&&k<=100)
							{
								System.out.println("matched requirement");
								return 1;
							}
							else{
								System.out.println("matched not requirement");
								return 0;
							}
						}
					}
				}
				line = br.readLine();
			}
		}
		return 0;
	}
}
