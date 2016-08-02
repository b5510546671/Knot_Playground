import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// JSON Simple Library Documentation: https://code.google.com/archive/p/json-simple/wikis/DecodingExamples.wiki

public class JsonReader {
	public static void main(String[] args){
		JSONParser parser = new JSONParser();
		
		File tweetText = new File("C:\\Users\\knotsupavit\\Desktop\\tweets_2016_07_25_textOnly.txt");
				
		try {
			FileOutputStream os = new FileOutputStream(tweetText);
			OutputStreamWriter osw = new OutputStreamWriter(os);
			Writer writer = new BufferedWriter(osw);
			
			JSONArray a = (JSONArray) parser.parse(new FileReader("C:\\Users\\knotsupavit\\Desktop\\tweets_2016_07_25.json"));
			int i = 1;
			for(Object o : a){
				JSONObject tweets = (JSONObject) o;
				
				String text = (String) tweets.get("text");
				writer.write(text + System.lineSeparator());
				System.out.println(i + ": " + text);
				i++;
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("file not found!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
