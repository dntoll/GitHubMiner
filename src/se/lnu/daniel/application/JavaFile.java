package se.lnu.daniel.application;

import java.io.FileNotFoundException;
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

}
