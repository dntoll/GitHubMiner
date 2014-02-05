package se.lnu.daniel.application;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class GitHubClient {
	CloseableHttpClient httpclient = HttpClients.createDefault();
	
	public RepositoryList getRandomRepoList(int randomNumber, String tokenKey) throws Exception {
		
		String uri = "https://api.github.com/repositories?since=" + randomNumber;
		HttpGet httpget = new HttpGet(uri);
		httpget.addHeader("Authorization", "token " + tokenKey);
		System.out.println("Downloading repos :" + httpget.toString());
		
		CloseableHttpResponse response;
		response = httpclient.execute(httpget);
		
		RepositoryList repoList = new RepositoryList(response);
		
		return repoList;
	}

	public JavaFileList getJavaFiles(Repository randomProject, String tokenKey) throws Exception {
		String repo = randomProject.getRepo();
		String user = randomProject.getUser();
		String uri = "https://api.github.com/search/code?q=*+language:java+repo:" + repo + "+user:" +user;
		HttpGet httpget = new HttpGet(uri);
		httpget.addHeader("Authorization", "token " + tokenKey);
		System.out.println("Search code :" + httpget.toString());
		
		CloseableHttpResponse response;
		response = httpclient.execute(httpget);
		
		JavaFileList fileList = new JavaFileList(response);
		
		
		return fileList;
	}

	public String getContent(String url, String tokenKey) throws Exception {
		CloseableHttpResponse response;
		try {
			HttpGet httpget = new HttpGet(url);
			httpget.addHeader("Authorization", "token " + tokenKey);
			System.out.println("Search code :" + httpget.toString());
			
			response = httpclient.execute(httpget);
			
			InputStreamReader isr = new InputStreamReader(response.getEntity().getContent());
			BufferedReader reader = new BufferedReader(isr);
			String read;
			StringBuilder content = new StringBuilder();
			
			do {
				read = reader.readLine();
				if (read != null) {
					content.append(read + "\n");
				}
				
				
			} while(read != null);
			response.close();
			return content.toString();
		} catch (Exception e) {
			throw e;
		}
		
		
	}
	
	

}
