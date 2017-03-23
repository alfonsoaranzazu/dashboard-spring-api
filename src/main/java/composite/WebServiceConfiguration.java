package composite;

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

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName("com.mysql.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/composite");
        source.setUsername("root");
        source.setPassword("root");
        
        return source;
	}
	
	@Bean
	public AccountDAO getAccountDAO() {
		return new AccountDAOImpl(getDataSource());
	}
}
