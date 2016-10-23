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
	//ref to regex at http://regexr.com/
	public ArrayList<String> removeString(ArrayList<String> textInput){
		ArrayList<String> cleanedText = new ArrayList<String>();
		try{			
			//Text reader
//			FileInputStream fstream = new FileInputStream("C:\\Users\\knotsupavit\\Desktop\\twitter_textOnlyFromCNN2016102.txt");
//			DataInputStream reader = new DataInputStream(fstream);
//			BufferedReader br = new BufferedReader(new InputStreamReader(reader));
//			String text;
//			
//			//Text writer
//			FileOutputStream os = new FileOutputStream("C:\\Users\\knotsupavit\\Desktop\\twitter_textOnlyFromCNN2016102_modified.txt");
//			OutputStreamWriter osw = new OutputStreamWriter(os);
//			Writer writer = new BufferedWriter(osw);
			
			
			for(String text : textInput){
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
				if(text.contains("#")){
					text = removeHashTagFromTweetText(text);
				}
				if(text.contains("…")){
					text = removeThreeDotsFromTweetText(text);
				}
				text = removeSpecialCharactersFromTweetText(text);
				cleanedText.add(text);
				//System.out.println(text);
			}
			System.out.println("SUCCESS!");
//			reader.close();			
//			writer.close();
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return cleanedText;
	}
	
	/**
	 * remove any non word characters from tweet text
	 * @param text
	 * @return
	 */
	private String removeSpecialCharactersFromTweetText(String text) {
		//remove all punctuation and symbols ref: http://stackoverflow.com/questions/10530942/regex-for-special-characters-in-java
		text = text.replaceAll("[^\\dA-Za-z ]", "");
        return text;
	}

	/**
	 * remove three dots from tweet text
	 * @param text
	 * @return
	 */
	private String removeThreeDotsFromTweetText(String text) {
		String pattern = "…";
        Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        int i = 0;
        while (m.find()) {
            text = text.replaceAll(m.group(0),"").trim();
            i++;
        }
        return text;
	}

	/**
	 * remove hashtag from tweet text
	 * @param text
	 * @return
	 */
	private String removeHashTagFromTweetText(String text) {
		String pattern = "#.[^\\s]+";
		text = text.replaceAll(pattern, "");
        return text;
	}

	/**
	 * remove mentioning (@) character from tweet text
	 * @param text
	 * @return
	 */
	private String removeAtCharacterFromTweetText(String text) {
		String pattern = "@.[\\w].[^\\s]+";
		text = text.replaceAll(pattern, "");
		return text;
	}

	/**
	 * Remove retweet tag from tweet text.
	 * @param text a tweet in text form
	 * @return tweet without retweet tag
	 */
	public String removeRetweetTagFromTweetText(String text){
		String pattern = "RT.@.[\\w].[^\\s]+";
        Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        int i = 0;
        while (m.find()) {
            text = text.replaceAll(m.group(0),"").trim();
            i++;
        }
        return text;
	}
	
	/**
	 * Remove link from tweet text which includes http: or https: at anywhere in tweet.
	 * @param text a tweet in text form
	 * @return tweet without link
	 */
	public String removeLinkFromTweetText(String text){
		String pattern = "http.[^\\s]+";
		text = text.replaceAll(pattern, "");
        return text;
	}
}
