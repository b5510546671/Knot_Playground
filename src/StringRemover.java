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
				String textToBeRemoved = text.substring(startTrimIndex, endTrimIndex);
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
		
		return text.substring(startTrimIndex);
	}
	
	/**
	 * Remove link from tweet text which includes http: or https: at anywhere in tweet.
	 * @param text a tweet in text form
	 * @return tweet without link
	 */
	public static String removeLinkFromTweetText(String text){
		while(true){
			System.out.println(text);
			if(text.contains("http://") || text.contains("https://") || text.contains("http:/") || text.contains("https:/") || text.contains("http:...") || text.contains("https:...") || text.contains("http...") || text.contains("https...")){
				char [] textarr = text.toCharArray();
				
				int startTrimIndex = -1;
				int endTrimIndex = -1;
				
				int i = 0;
				int counter = 0;
				
				for(char c : textarr){
					
					if(i <= textarr.length - 5){
						if(textarr[i] == 'h' && textarr[i+1] == 't' && textarr[i+2] == 't' && textarr[i+3] == 'p' && textarr[i+4] == ':'){
							startTrimIndex = i;
						}
						else if(textarr[i] == 'h' && textarr[i+1] == 't' && textarr[i+2] == 't' && textarr[i+3] == 'p' && textarr[i+4] == 's' && textarr[i+5] == ':'){
							startTrimIndex = i;
						}
					}
					else{
						if(c == 'h' && counter == 0){
							counter++;
						}
						else if((counter == 1 && c == 't') || (counter == 2 && c == 't')){
							counter++;
						}
						else if(counter == 3 && c == 'p'){
							counter++;
						}
						if(counter == 4){
							startTrimIndex = i - 4;
							counter = 0;
						}
					}

					if(c == ' ' && startTrimIndex > endTrimIndex){
						endTrimIndex = i;
						break;
					}			
					
					i++;
				}
				if(i == textarr.length && startTrimIndex > endTrimIndex){
					endTrimIndex = i;
				}
				//System.out.println("startTrimIndex: " + startTrimIndex + " endTrimIndex: " + endTrimIndex + " textarr.length is " + textarr.length);
				String textTobeRemoved = text.substring(startTrimIndex, endTrimIndex);
				//System.out.println("textTobeRemoved: " + textTobeRemoved);
				text = text.replaceAll(textTobeRemoved, "");
				//System.out.println("text: " + text);
			}
			else if(text.contains("http:") || text.contains("https:") || text.contains("http") || text.contains("https")){
				//TODO: implement logic to remove https or http available link that stays on the end of tweet
				
				
				//TODO: CONTINUE WORKING ON THIS!!!
				char[] textarr = text.toCharArray();
				
				int i = 0;
				int startTrimIndex = -1;
				int endTrimIndex = -1;
				
				for(char c : textarr){
					if(i <= textarr.length - 5){
						if(textarr[i] == 'h' && textarr[i+1] == 't' && textarr[i+2] == 't' && textarr[i+3] == 'p'){
							startTrimIndex = i;
						}
						else if(textarr[i] == 'h' && textarr[i+1] == 't' && textarr[i+2] == 't' && textarr[i+3] == 'p' && textarr[i+4] == 's'){
							startTrimIndex = i;
						}
						
					}
				}
			}
			else{
				break;
			}
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
