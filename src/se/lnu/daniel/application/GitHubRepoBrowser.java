package se.lnu.daniel.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GitHubRepoBrowser {
	ProjectDatabase projectDatabase;
	GitHubProjectClient ghClient;
	
	public GitHubRepoBrowser(ProjectDatabase db, GitHubProjectClient client) {
		projectDatabase = db;
		ghClient = client;
	}

	public Project[] getAllProjects() throws IOException {
		List<Project> ret = new ArrayList<Project>();
		
		List<Project> projectsFromDB =  projectDatabase.getProjects();
		List<Project> projectsFromClient =  ghClient.getProjects();
		
		projectDatabase.addProjects(projectsFromClient);
		
		ret.addAll(projectsFromDB);
		ret.addAll(projectsFromClient);
		
		return ret.toArray(new Project[ret.size()]);
	}

}
