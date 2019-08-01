package br.com.devdojo.springboot.security.config;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prov")
public class DataConfigurationPostGresql<BasicDataSource> {
	@Bean
	public DataSource dataSource() throws URISyntaxException {
		URI dbUri = new URI(System.getenv("DATABASE_URI"));
		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath();
		
		//BasicDataSource basicDataSources = new BasicDataSource(); 
		//basicDataSources.
		
		/*DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/school?useSSL=false&serverTimezone=UTC&LegacyDatetimeCode=false");
		dataSource.setUsername("root");
		dataSource.setPassword("js@72446066");
		return dataSource();*/
		
		return null;
	}
	
}
