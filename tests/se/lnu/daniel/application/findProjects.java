package se.lnu.daniel.application;


import java.io.IOException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class findProjects {

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
	public void downloadProjects() throws IOException {
		GitHubRepoBrowser ghrb = mock(GitHubRepoBrowser.class);
		Project[] projectMockers = new Project[3];
		
		String[] langs = new String[]{"Java", "Foo"};
		String[] langs2 = new String[]{"PHP", "Java"};
		String[] langs3 = new String[]{"PHP", "Foo"};
		projectMockers[0] = new Project(1, "One", langs);
		projectMockers[1] = new Project(2, "Two", langs2);
		projectMockers[2] = new Project(2, "Three", langs3);
		
		when(ghrb.getAllProjects()).thenReturn(projectMockers);
		
		ProjectFinder finder = new ProjectFinder(ghrb);
		
		Project[] actual = finder.findProjects("java");
		
		Assert.assertTrue(actual.length == 2);
	}
	


}
