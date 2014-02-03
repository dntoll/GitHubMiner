package se.lnu.daniel.application;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonStructure;
import javax.json.JsonValue;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GitHubAPITest {

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
	public void testGetRepositories() {
		GitHubAPI sut = new GitHubAPI("key");
		List<Project> p = sut.getRepositories(1);
	}

	/*@Test
	public void testApi() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("https://api.github.com/repositories");
		
		httpget.addHeader("Authorization", "token ");
		
		
		
		
		
		
		try {
			CloseableHttpResponse response;
			response = httpclient.execute(httpget);
			try {
							
				HttpEntity content = response.getEntity();
				
				InputStreamReader isr = new InputStreamReader(content.getContent());
				
				
				
				JsonReader reader = Json.createReader(isr);
				
				
				JsonStructure structure = reader.read();
				
				navigateTree(structure, null);
				
				
			    
			} finally {
			    response.close();
			}
			httpclient.close();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void navigateTree(JsonValue tree, String key) {
		   if (key != null)
		      System.out.print("Key " + key + ": ");
		   switch(tree.getValueType()) {
		      case OBJECT:
		         System.out.println("OBJECT");
		         JsonObject object = (JsonObject) tree;
		         for (String name : object.keySet())
		            navigateTree(object.get(name), name);
		         break;
		      case ARRAY:
		         System.out.println("ARRAY");
		         JsonArray array = (JsonArray) tree;
		         for (JsonValue val : array)
		            navigateTree(val, null);
		         break;
		      case STRING:
		         JsonString st = (JsonString) tree;
		         System.out.println("STRING " + st.getString());
		         break;
		      case NUMBER:
		         JsonNumber num = (JsonNumber) tree;
		         System.out.println("NUMBER " + num.toString());
		         break;
		      case TRUE:
		      case FALSE:
		      case NULL:
		         System.out.println(tree.getValueType().toString());
		         break;
		   }
		}*/
}
