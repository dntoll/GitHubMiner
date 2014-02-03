package se.lnu.daniel.application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class ProjectDatabase {
	
	String fileName;
	int lastID = 0;
	
	List<Project> allProjects;
	
	
	public ProjectDatabase(String fileName) throws IOException {
		this.fileName = fileName;
		allProjects = myGetMethods();
	}

	public List<Project> getProjects() throws IOException {
		return allProjects;
	}

	private List<Project> myGetMethods() throws FileNotFoundException,
			IOException {
		CSVReader reader = new CSVReader(new FileReader(fileName), '\t');
		List<Project> ret = new ArrayList<Project>();
		
		List<String[]> lines= reader.readAll();
		
		for(String[] line : lines) {
			
			String[] languages = line[2].split(",");
			Project p = new Project(Integer.parseInt(line[0]), line[1], languages);
			ret.add(p);
			if (p.getID() > lastID)
				lastID = p.getID();
		}
		return ret;
	}

	public int getLastID() {
		return lastID;
	}

	public void addProjects(List<Project> fromClient) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(fileName, true), '\t');
		
		String[] entries = new String[3];
		for (Project p : fromClient) {
			entries[0] = "" + p.getID();
			entries[1] = p.getName();
			
			entries[2] = "";
			String[] languages = p.getLanguages();
			for(int i = 0; i < languages.length; i++) {
				entries[2] += languages[i];
				if (i < languages.length-1)
					entries[2] += ","; 
			}
		    writer.writeNext(entries);
		    allProjects.add(p);
		    if (p.getID() > lastID)
				lastID = p.getID();
		}
		writer.close();
		
	}

}
