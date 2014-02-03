package se.lnu.daniel.application;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GitHubRepoBrowserTest {

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
	public void testGetAllProjects() throws IOException {
		ProjectDatabase db = mock(ProjectDatabase.class);
		GitHubProjectClient client = mock(GitHubProjectClient.class);
		GitHubRepoBrowser sut = new GitHubRepoBrowser(db, client);
		List<Project> fromDB = new ArrayList<Project>();
		List<Project> fromClient = new ArrayList<Project>();
		fromDB.add(new Project(0, "", new String[1]));
		fromDB.add(new Project(1, "", new String[1]));
		fromDB.add(new Project(2, "", new String[1]));
		
		
		
		when(db.getProjects()).thenReturn(fromDB);
		when(client.getProjects()).thenReturn(fromClient);
		
		
		Project[] actual = sut.getAllProjects();
		
		Assert.assertEquals(3, actual.length);
		
		verify(db).addProjects(fromClient);
		verify(db).getProjects();
		verify(client).getProjects();
		
	}

}
