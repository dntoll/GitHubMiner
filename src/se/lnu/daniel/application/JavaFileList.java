package se.lnu.daniel.application;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonValue;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;

public class JavaFileList {
	List<JavaFile> allFiles;
	
	public JavaFileList(CloseableHttpResponse response) throws Exception {
		try {
			HttpEntity content = response.getEntity();
			InputStreamReader isr;
			
			isr = new InputStreamReader(content.getContent());
			JsonReader reader = Json.createReader(isr);
			JsonStructure structure = reader.read();
			allFiles = new ArrayList<JavaFile>();
			JsonObject files = (JsonObject) structure;
			JsonArray array = (JsonArray)files.get("items");
		
	        for (JsonValue val : array) {
	        	JavaFile newProject = parseJavaFiles((JsonObject)val);
	        	allFiles.add(newProject);
	        }
		} catch (Exception e) {
			throw e;
		} finally {
			response.close();
		}
	//	https://raw.github.com/kdridi/Lineage-II-Server/blob/42b638a5109eb83fde411194d7c560fbb4c3679e/lineage-server/src/main/java/net/sf/l2j/gameserver/handler/IAdminCommandHandler.java
	//	https://raw.github.com/kdridi/Lineage-II-Server/42b638a5109eb83fde411194d7c560fbb4c3679e/lineage-server/src/main/java/net/sf/l2j/gameserver/handler/IAdminCommandHandler.java
		
		
	}

	private JavaFile parseJavaFiles(JsonObject val) {
		JsonObject object = (JsonObject) val;
		
		String path = object.get("html_url").toString();
		//JsonString contentBase64 = (JsonString)object.get("content");
		
		path = path.replaceAll("https://github", "https://raw.github");
		path = path.replaceFirst("blob/", "");
		
		//String content =  StringUtils.newStringUtf8(Base64.decodeBase64(contentBase64.toString()));
		
		return new JavaFile(path);
	}

	public JavaFile getRandomFile(Random random) throws Exception {
		if (allFiles.size() == 0) {
			throw new Exception("No java files in repo");
		}
		return allFiles.get(random.nextInt(allFiles.size()));
	}

	
	
}
