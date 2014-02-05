package se.lnu.daniel.application;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

public class RepositoryList {
	List<Repository> allRepos = new ArrayList<Repository>();
	CloseableHttpResponse response;
	public RepositoryList(CloseableHttpResponse response) throws Exception {
		try {
			this.response = response;
			HttpEntity content = response.getEntity();
			InputStreamReader isr;
			
			isr = new InputStreamReader(content.getContent());
			JsonReader reader = Json.createReader(isr);
			JsonStructure structure = reader.read();
			
			JsonArray repositories = (JsonArray) structure;
		
	        for (JsonValue val : repositories) {
	        	Repository newProject = parseRepo(val);
	        	allRepos.add(newProject);
	        }
		} catch (Exception e) {
			throw e;
		} finally {
			response.close();
		}
	    
	}

	public Repository getRandomRepository(Random rand) throws Exception {
		if (allRepos.size() == 0)
			throw new Exception("No repos in result");
		
		int repoNumber = rand.nextInt(allRepos.size());
		return allRepos.get(repoNumber);
	}
	
	private Repository parseRepo( JsonValue val) throws ClientProtocolException, IOException {
		JsonObject object = (JsonObject) val;
		
		JsonNumber id = (JsonNumber)object.get("id");
		JsonString name = (JsonString)object.get("full_name");
		
		return new Repository(id.intValue(), name.getString());
	}
	
	

}
