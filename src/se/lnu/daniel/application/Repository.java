package se.lnu.daniel.application;

public class Repository {
	private int id;
	private String name;
	public Repository(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getRepo() {
		return name.split("/")[1];
	}
	public String getUser() {
		return name.split("/")[0];
	}
	
	

}
