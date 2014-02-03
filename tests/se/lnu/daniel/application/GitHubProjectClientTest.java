package se.lnu.daniel.application;


import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GitHubProjectClientTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void downloadNoNewRepos() throws Exception {
		ProjectDatabase db = mock(ProjectDatabase.class);
		GitHubAPI api = mock(GitHubAPI.class);
		GitHubProjectClient sut = new GitHubProjectClient(db, api);
		
		
		
		int lastId = 15;
		when(db.getLastID()).thenReturn(lastId);
		when(api.getRepositories(anyInt())).thenReturn(null);
		
		List<Project> actual = sut.getProjects();
		
		assertEquals(0, actual.size());
		
		verify(db).getLastID();
		verify(api).getRepositories(lastId);
	}
	
	@Test
	public void downloadNewRepos() throws Exception {
		ProjectDatabase db = mock(ProjectDatabase.class);
		GitHubAPI api = mock(GitHubAPI.class);
		GitHubProjectClient sut = new GitHubProjectClient(db, api);
		
		
		
		int lastId = 13;
		when(db.getLastID()).thenReturn(lastId);
		
		List<Project> projects = new ArrayList<Project>();
		projects.add(new Project(114, ""));
		when(api.getRepositories(lastId)).thenReturn(projects);
		when(api.getRepositories(114)).thenReturn(null);
				
		List<Project> actual = sut.getProjects();
		
		assertEquals(projects, actual);
		
		verify(db).getLastID();
		verify(api).getRepositories(lastId);
		verify(api).getRepositories(114);
	}

}
