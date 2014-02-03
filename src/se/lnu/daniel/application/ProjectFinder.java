package se.lnu.daniel.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectFinder {

	private GitHubRepoBrowser gitHubRepoBrowser;


	public ProjectFinder(GitHubRepoBrowser ghrb) {
		this.gitHubRepoBrowser = ghrb;
	}

	
	public Project[] findProjects(String programmingLanguage) throws IOException {
		Project[] allProjects = gitHubRepoBrowser.getAllProjects();
		List<Project> ret = new ArrayList<Project>();
		
		for (Project p : allProjects) {
			if (p.hasLanguage(programmingLanguage)) {
				ret.add(p);
			}
		}
		
		
		return ret.toArray(new Project[ret.size()]);
	}

}
