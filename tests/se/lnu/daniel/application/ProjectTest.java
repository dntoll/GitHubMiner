package se.lnu.daniel.application;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProjectTest {

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
	public void testHasLanguage() {
		String[] langs = new String[]{"Java", "Foo"};
		Project sut = new Project(0, "", langs);
		
		assertTrue(sut.hasLanguage("Java"));
		assertTrue(sut.hasLanguage("Foo"));
		assertFalse(sut.hasLanguage("PHP"));
	}

}