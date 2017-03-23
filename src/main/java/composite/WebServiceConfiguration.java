package composite;

import java.sql.*;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages="composite")
@EnableWebMvc
public class WebServiceConfiguration extends WebMvcConfigurerAdapter {
	private DriverManagerDataSource source;

	@Bean
	public DataSource getDataSource() {
		try {
			this.source = new DriverManagerDataSource();
	        this.source.setDriverClassName("com.mysql.jdbc.Driver");
	        this.source.setUrl("jdbc:mysql://localhost:3306/composite");
	        this.source.setUsername("root");
	        this.source.setPassword("root");
	       
	        return source;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Bean
	public Connection getConnection() {
		try {
			return this.source.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Bean
	public AccountDAO getAccountDAO() {
		return new AccountDAOImpl(this.getDataSource(), this.getConnection());
	}
}
