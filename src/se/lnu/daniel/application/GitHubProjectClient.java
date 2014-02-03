package se.lnu.daniel.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GitHubProjectClient {

	ProjectDatabase db;
	GitHubAPI api;
	public GitHubProjectClient(ProjectDatabase db, GitHubAPI api) {
		this.db = db;
		this.api = api;
	}

	public List<Project> getProjects() throws IOException {
		Random r = new Random();
		List<Project> repos = new ArrayList<Project>();
		int id = db.getLastID();
		//int highestid = id;
		List<Project> newRepos;
		int page = 1;
		do {
			
			newRepos = api.getRepositories(page);
			
			if (newRepos != null) {
				repos.addAll(newRepos);
				db.addProjects(newRepos);
				/*for (Project p : newRepos) {
					if (p.getID() > highestid) {
						highestid = p.getID(); 
					}
				}*/
			}
			page++;
		
		} while (newRepos != null && newRepos.size() > 0);
		
		return repos;
	}

}
