package se.lnu.daniel.application;

import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import au.com.bytecode.opencsv.CSVWriter;

public class ProjectDatabaseTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private ProjectDatabase sut;

	@Before
	public void setUp() throws Exception {
		
		CSVWriter writer = new CSVWriter(new FileWriter("test.csv"), '\t');
		String[] entries = "1#name#lang1,lang2".split("#");
	    writer.writeNext(entries);
	    writer.writeNext(entries);
	    writer.writeNext(entries);
		writer.close();
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetProjects() throws IOException {
		sut = new ProjectDatabase("test.csv");
		List<Repository> projects = sut.getProjects();
		
		assertEquals(3, projects.size());
	}

	@Test
	public void testGetLastID() throws IOException {
		sut = new ProjectDatabase("test.csv");
		List<Repository> projects = sut.getProjects();
		assertEquals(sut.getLastID(), 1);
	}

	@Test
	public void testAddProjects() throws IOException {
		sut = new ProjectDatabase("test.csv");
		List<Repository> projects = sut.getProjects();
		
		sut = new ProjectDatabase("test.csv");
		sut.addProjects(projects);
		projects = sut.getProjects();
		assertEquals(6, projects.size());
	}

}
