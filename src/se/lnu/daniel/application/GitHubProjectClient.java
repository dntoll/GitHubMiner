package se.lnu.daniel.application;

import java.util.ArrayList;
import java.util.List;

public class GitHubProjectClient {

	ProjectDatabase db;
	GitHubAPI api;
	public GitHubProjectClient(ProjectDatabase db, GitHubAPI api) {
		this.db = db;
		this.api = api;
	}

	public List<Project> getProjects() {
		List<Project> repos = new ArrayList<Project>();
		int id = db.getLastID();
		int highestid = id;
		boolean foundNew = false;
		
		do {
			foundNew = false;
			List<Project> newRepos = api.getRepositories(highestid);
			if (newRepos != null) {
			
				for (Project p : newRepos) {
					if (p.getID() > highestid) {
						highestid = p.getID(); 
						foundNew = true;
					}
					repos.add(p);
				}
			}
		
		} while (foundNew);
		
		return repos;
	}

}
