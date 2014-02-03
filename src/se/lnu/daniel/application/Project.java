package se.lnu.daniel.application;

public class Project {
	final String[] languages;
	private int id;
	private String name;
	public Project(int id, String name, String[] languages) {
		this.languages = languages;
		this.id = id;
		this.name = name;
	}

	public boolean hasLanguage(String programmingLanguage) {
		for(String language : languages) {
			if (programmingLanguage.equalsIgnoreCase(language))
				return true;
		}
		return false;
	}

	public int getID() {
		return this.id;
	}

	public String getName() {
		return name;
	}
	
	public String[] getLanguages() {
		return languages;
	}

}
