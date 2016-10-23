import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TextFileReader {
	public TextFileReader(){
		
	}
	public ArrayList<String> readFile(String filePath) throws IOException{
		ArrayList<String> fileContents = new ArrayList<String>();
		
		FileInputStream fstream = new FileInputStream(filePath);
		DataInputStream reader = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(reader));
		String text;
		
		while((text = br.readLine()) != null){
			fileContents.add(text);
		}
		reader.close();
		return fileContents;
	}
}
