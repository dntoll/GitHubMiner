package se.lnu.daniel.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectFinder {

	private GitHubRepoBrowser gitHubRepoBrowser;


	public static void main(String tokenkey[]) throws IOException {
		ProjectDatabase db = new ProjectDatabase("repositories");
		GitHubAPI api = new GitHubAPI(tokenkey[0]);
		GitHubProjectClient client = new GitHubProjectClient(db, api);
		GitHubRepoBrowser browser = new GitHubRepoBrowser(db, client);
		browser.getAllProjects();
	}
	
	public ProjectFinder(GitHubRepoBrowser ghrb) {
		this.gitHubRepoBrowser = ghrb;
	}
}
