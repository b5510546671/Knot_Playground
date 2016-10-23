import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class TextFileWriter {
	public TextFileWriter(){
		
	}
	public void writeToFile(ArrayList<String> stringList, String outputFilePath) throws IOException{
		FileOutputStream os = new FileOutputStream(outputFilePath);
		OutputStreamWriter osw = new OutputStreamWriter(os);
		Writer writer = new BufferedWriter(osw);
		
		for(String text : stringList){
			writer.write(text + System.lineSeparator());
		}
		
		writer.close();
	}
}
