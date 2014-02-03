package se.lnu.daniel.application;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

public class GitHubAPI {
	CloseableHttpClient httpclient;
	String tokenKey;
	public GitHubAPI(String tokenKey) {
		
		this.tokenKey = tokenKey;
	}
	

	public List<Project> getRepositories(int page) {
		
		List<Project> ret = new ArrayList<Project>();
		httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("https://api.github.com/search/repositories?q=language:Java&page=" + page +"&per_page=100");
		httpget.addHeader("Authorization", "token " + tokenKey);
		System.out.println("Downloading repos :" + httpget.toString());
		try {
			CloseableHttpResponse response;
			response = httpclient.execute(httpget);
			try {
							
				HttpEntity content = response.getEntity();
				InputStreamReader isr = new InputStreamReader(content.getContent());

				JsonReader reader = Json.createReader(isr);
				JsonStructure structure = reader.read();
				
				JsonObject tree = (JsonObject) structure;
				//navigateTree(structure, null);
				JsonArray array = (JsonArray) tree.get("items");
				
				
				try {
					JsonArray repositories = (JsonArray) array;
			        for (JsonValue val : repositories) {
			        	Project newProject = parseRepo(val);
			        	ret.add(newProject);
			        }
				} catch (java.lang.ClassCastException e) {
	    			e.printStackTrace();
	    			
	    			System.err.println(structure.toString());
	    		}
				
				
			    
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
		return ret;	
	}


	private Project parseRepo( JsonValue val) throws ClientProtocolException, IOException {
		JsonObject object = (JsonObject) val;
		boolean isPrivate = object.get("private").getValueType() == javax.json.JsonValue.ValueType.TRUE;
		boolean isFork = object.get("private").getValueType() == javax.json.JsonValue.ValueType.TRUE;

		JsonNumber id = (JsonNumber)object.get("id");
		JsonString name = (JsonString)object.get("full_name");
		//JsonString languages_url = (JsonString)object.get("languages_url");
		
		/*System.out.println("Checking language on :" + languages_url);
		
		String[] languages = null;
		if (!isFork && !isPrivate) {
			languages = getLanguages(languages_url);
		} else {
			languages = new String[1];
			languages[0] = "private or fork";
		}*/
		Project newProject = new Project(id.intValue(), name.getString());
		
		
		return newProject;
	}
	
	private String[] getLanguages( JsonString languages_url) throws ClientProtocolException, IOException {
		HttpGet httpgetapi = new HttpGet(languages_url.getString());
    	httpgetapi.addHeader("Authorization", "token " + tokenKey);
    	CloseableHttpResponse response;
		response = httpclient.execute(httpgetapi);
		
		HttpEntity content = response.getEntity();
		InputStreamReader isr = new InputStreamReader(content.getContent());

		JsonReader reader = Json.createReader(isr);
		JsonObject structure = (JsonObject)reader.read();
		
		String[] languages = new String[structure.keySet().size()];
		int i = 0;
		for (String name : structure.keySet()) {
			//JsonNumber num = (JsonNumber) structure.get(name);
			//System.out.println();
			languages[i] = name;
			i++;
		}
		response.close();
		
		
		
		return languages;
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
	        // for (JsonValue val : array)
	        //    navigateTree(val, null);
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
	}

}
