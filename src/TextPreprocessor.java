import java.io.IOException;
import java.util.ArrayList;

public class TextPreprocessor {
	public TextPreprocessor(){
		
	}
	
	public static void main(String[] args) throws IOException{
		StringRemover stringRemover = new StringRemover();
//		JsonReader jsonReader = new JsonReader();
		TextFileWriter fileWriter = new TextFileWriter();
		TextFileReader fileReader = new TextFileReader();
		
			
//		String jsonFileInputPath = "C:\\Users\\knotsupavit\\Desktop\\python.json";
		String outputFilePath = "C:\\Users\\knotsupavit\\Desktop\\twitter_textOnlyFromCNN20161023_modified.txt";
		String inputFilePath = "C:\\Users\\knotsupavit\\Desktop\\twitter_textOnlyFromCNN20161023.txt";
		
		ArrayList<String> inputTextFile = fileReader.readFile(inputFilePath);
		
//		ArrayList<String> parsedJsonList = jsonReader.parseJson(jsonFileInputPath);
		ArrayList<String> removedStringList = stringRemover.removeString(inputTextFile);
		fileWriter.writeToFile(removedStringList, outputFilePath);
	}
}
