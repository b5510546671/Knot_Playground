import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringRemover {
	public StringRemover(){
		
	}
	
	public static void main(String [] args){
		try{			
			//Text reader
			FileInputStream fstream = new FileInputStream("C:\\Users\\knotsupavit\\Desktop\\twitter_textOnlyFromCNN2016102Copy.txt");
			DataInputStream reader = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(reader));
			String text;
			
			//Text writer
			FileOutputStream os = new FileOutputStream("C:\\Users\\knotsupavit\\Desktop\\twitter_textOnlyFromCNN2016102_modified.txt");
			OutputStreamWriter osw = new OutputStreamWriter(os);
			Writer writer = new BufferedWriter(osw);
			
			while((text = br.readLine()) != null){
				System.out.println("New Text: " + text);
				if(text.contains("http://") || text.contains("https://") || text.contains("http:/") || text.contains("https:/") || text.contains("http:...") || text.contains("https:...") || text.contains("http...") || text.contains("https...")){
					System.out.println("removeLinkFromTweetText");
					text = removeLinkFromTweetText(text);
				}
				if(text.contains("RT @")){
					System.out.println("removeRetweetTagFromTweetText");
					text = removeRetweetTagFromTweetText(text);					
				}
				if(text.contains("@")){
					System.out.println("removeAtCharacterFromTweetText");
					text = removeAtCharacterFromTweetText(text);
				}
				writer.write(text + System.lineSeparator());
				//System.out.println(text);
			}
			System.out.println("SUCCESS!");
			reader.close();			
			writer.close();
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static String removeAtCharacterFromTweetText(String text) {
		while(true){
			if(!text.contains("@")){
				break;
			}
			
			char [] textarr = text.toCharArray();
			int i = 0;
			int startTrimIndex = -1;
			int endTrimIndex = -1;
			for(char c : textarr){
				if(c == '@' && startTrimIndex == -1){
					startTrimIndex = i;					
				}
				if(c == ' ' && endTrimIndex == -1 && startTrimIndex != -1){					
					endTrimIndex = i;
					break;
				}
				if(i == textarr.length - 1 && startTrimIndex != -1){
					endTrimIndex = i;
				}
				i++;
			}
			
			//System.out.println("startTrimIndex: " + startTrimIndex + " endTrimIndex: " + endTrimIndex);
			
			if(startTrimIndex != -1 && endTrimIndex != -1){
				//System.out.println("this if is called");
				String textToBeRemoved = text.substring(startTrimIndex, endTrimIndex+1);
				//System.out.println("textToBeRemoved: " + textToBeRemoved);
				text = text.replaceAll(textToBeRemoved, "");
				//System.out.println("text" + text);
			}
			
			
		}
		return text;
	}

	/**
	 * Remove retweet tag from tweet text.
	 * @param text a tweet in text form
	 * @return tweet without retweet tag
	 */
	public static String removeRetweetTagFromTweetText(String text){
		char [] textarr = text.toCharArray();
		
		int startTrimIndex = -1;
		int i = 0;
		
		for(char c : textarr){
			if(c == ':'){
				startTrimIndex = i + 1;
				break;
			}
			i++;
		}
		
		return text.substring(startTrimIndex+1);
	}
	
	/**
	 * Remove link from tweet text which includes http: or https: at anywhere in tweet.
	 * @param text a tweet in text form
	 * @return tweet without link
	 */
	public static String removeLinkFromTweetText(String text){
		String urlPattern = "http.[^\\s]+";
        //String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern p = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        int i = 0;
        while (m.find()) {
        	System.out.println("found: " + i + " : " + m.start() + " - " + m.end());
            //text = text.replaceAll(m.group(i),"").trim();
            text = text.replaceAll(m.group(0),"").trim();
            i++;
        }
        return text;
	}
	
	public static List<String> RemoveSpecialCharacters(String docString){
    	List<String> docList = Arrays.asList(docString.split(" "));
    	List<String> docList2 = new ArrayList<String>();
    	for(String str : docList){
        	while(true){
        		if(str.endsWith(".")){
            		str = str.replace(".", "");
            	}
        		else if(str.endsWith(",")){
            		str = str.replace(",", "");
            	}
        		else if(str.startsWith("'")){
            		str = str.replace("'", "");
            	}
        		else if(str.endsWith("'")){
            		str = str.replace("'", "");
            	}
        		else if(str.startsWith("\"")){
            		str = str.replace("\"", "");
            	}
        		else if(str.endsWith("\"")){
            		str = str.replace("\"", "");
            	}
        		else if(str.endsWith(":")){
            		str = str.replace(":", "");
            	}
        		else if(str.endsWith(";")){
            		str = str.replace(";", "");
            	}
        		else if(str.endsWith("'s")){
            		str = str.replace("'s", "");
            	}
        		else if(str.endsWith("?")){
            		str = str.replace("?", "");
            	}
        		else if(str.endsWith("!")){
            		str = str.replace("!", "");
            	}
            	else{
            		docList2.add(str);
            		break;
            	}
            	
        	}
        	
        }
    	
    	if(docList2.get(docList2.size()-1).contains("http")){
    		docList2.remove(docList2.size()-1);
        }
    	
    	return docList2;
    }
}
