package br.com.devdojo.springboot.javaclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.tomcat.util.http.fileupload.IOUtils;

public class JavaClient {

	public static void main(String[] args) {
		
		HttpURLConnection connect = null;
		BufferedReader reader = null;
		//String user = "josemberg";
		//String password = "js72446066";
		try {
			URL url = new URL("http://localhost:8080/v1/protected/students/5");
			connect = (HttpURLConnection) url.openConnection();
			connect.setRequestMethod("GET");
			//connect.addRequestProperty("Authorization", "Basic " + encodingUsernamePassword(user, password));
			connect.addRequestProperty("Authorization", "Basic am9zZW1iZXJnOmpzNzI0NDYwNjY");
			//System.out.println(encodingUsernamePassword(user, password));
			reader = new BufferedReader(new InputStreamReader(connect.getInputStream()));//retorna o valor
			StringBuilder jsonSB = new StringBuilder();//para adicinar a resposta
			String line;
			while((line = reader.readLine()) != null) {
				jsonSB.append(line);
			}
			System.out.println(jsonSB.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			IOUtils.closeQuietly(reader);
			if (connect != null) {
				connect.disconnect();
			}
		}
	}
	
	/*private static String encodingUsernamePassword(String user, String password) {
		String userPassword = user + " : " + password;
		return new String(Base64.encodeBase64(userPassword.getBytes()));
	}*/

}
