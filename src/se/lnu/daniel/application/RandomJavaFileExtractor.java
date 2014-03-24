package se.lnu.daniel.application;

import java.io.IOException;
import java.util.Random;

import org.apache.http.client.ClientProtocolException;

public class RandomJavaFileExtractor {
	public static void main(String args[]) {
		String token = args[0];
		int numTries = Integer.parseInt(args[1]);
		GitHubClient client = new GitHubClient();
		
		Random random = new Random();
		
		
		
		for (int i  =0; i< numTries; i++) {
			try {
				
				JavaFile file = getRandomJavaFile(token, client, random);
				
				
				
				System.out.println(file.print());
				
			} catch (Exception e) {
				e.printStackTrace();
				
				try {
					Thread.sleep(20);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			} 
		}
	}

	private static JavaFile getRandomJavaFile(String token,
			GitHubClient client, Random random) throws ClientProtocolException,
			IOException, Exception {
		int randomNumber = random.nextInt(17972500);
		RepositoryList repoList = client.getRandomRepoList(randomNumber, token);
		Repository randomProject = repoList.getRandomRepository(random);
		
		JavaFileList fileList = client.getJavaFiles(randomProject, token);
		
		JavaFile file = fileList.getRandomFile(random);
		
		file.setContent(client.getContent(file.getURL(), token));
		
		file.save(randomNumber);
		
		return file;
	}
}
