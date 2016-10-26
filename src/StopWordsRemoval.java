import java.io.IOException;
import java.util.ArrayList;

public class StopWordsRemoval {
	public StopWordsRemoval(){

	}

	public static void main(String[] args) throws IOException{
		TextFileReader reader = new TextFileReader();
		TextFileWriter writer = new TextFileWriter();
		
		ArrayList<String> resultWordsList = new ArrayList<String>();
		
		ArrayList<String> stopWordsList = reader.readFile("C:\\Users\\knotsupavit\\Desktop\\stopwords_list.txt");
		ArrayList<String> tweets = reader.readFile("C:\\Users\\knotsupavit\\Desktop\\twitter_textOnlyFromCNN20161023_modified.txt");
		int counter = 0;
		for(String s : tweets){
			counter++;
			System.out.println(counter);
			System.out.println(s);
			String result = "";
			ArrayList<String> tempResultList = new ArrayList<String>();
			ArrayList<String> resultList = new ArrayList<String>();

			String[] words = s.split(" ");
			
			for(int j = 0; j < words.length; j++){
				boolean stopWord = false;
				for(int i = 0; i < stopWordsList.size(); i++){
					if(!words[j].equals(" ")){
						if(words[j].equalsIgnoreCase(stopWordsList.get(i))){
							stopWord = true;
							break;
						}
					}
				}
				if(!stopWord){
					resultList.add(words[j]);
				}
			}
			
			for(String str: resultList){
//				System.out.println("result: " + result);
				result += (" " + str);
			}
			System.out.println(result);
			resultWordsList.add(result);
		}
		
		writer.writeToFile(resultWordsList, "C:\\Users\\knotsupavit\\Desktop\\twitter_textOnlyFromCNN2016102_modified_wordsRemoved.txt");
		
	}
}
