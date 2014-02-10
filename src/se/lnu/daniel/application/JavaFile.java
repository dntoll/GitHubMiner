package se.lnu.daniel.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class JavaFile {

	private String fileURL;
	private String content ="";

	public JavaFile(String string) {
		fileURL = string;
	}

	public String print() {
		return "FileURL: {"+fileURL+"}\nContent {" + content + "}";
	}

	public String getURL() {
		return fileURL;
	}

	public void setContent(String content2) {
		content = content2;
		
	}

	public void save(int randomNumber) throws FileNotFoundException {
		PrintWriter out = new PrintWriter("randomFiles/" + randomNumber + ".java");
		
		out.println(fileURL);
		out.println("\n");
		out.print(content);
		out.flush();
	}

	public static JavaFile getFromFile(File f) throws IOException {
		FileReader fr = new FileReader(f);
		
		BufferedReader reader = new BufferedReader(fr);
		String read;
		StringBuilder content = new StringBuilder();
		String URL = reader.readLine();
		reader.readLine();
		do {
			read = reader.readLine();
			if (read != null) {
				content.append(read + "\n");
			}
			
			
		} while(read != null);
		
		fr.close();
		String contentString = content.toString();
		
		JavaFile jf = new JavaFile(URL);
		jf.setContent(contentString);
		return jf;
	}

	public String getContent() {
		return content;
	}
	
	

}
