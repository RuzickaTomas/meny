package cz.kurz.meny;

import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

	@Bean
	public PoolingHttpClientConnectionManager customizedPoolingHttpClientConnectionManager() {
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
		connManager.setMaxTotal(100); // Set required maximum total connections
		connManager.setDefaultMaxPerRoute(20); // Set required maximum connections per route
		return connManager;
	}

	@Bean
	public RestTemplate restTemplate() {
		HttpClientBuilder clientBuilder = HttpClientBuilder.create()
				.setConnectionManager(customizedPoolingHttpClientConnectionManager()).setConnectionManagerShared(true);

		HttpComponentsClientHttpRequestFactory f = new HttpComponentsClientHttpRequestFactory();
		f.setHttpClient(clientBuilder.build());
		return new RestTemplate(f);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
	    return httpSecurity.csrf(AbstractHttpConfigurer::disable)
	            .authorizeHttpRequests(auth -> auth.requestMatchers("/", "/actuator/health").permitAll()
	            .requestMatchers("/exchange").authenticated())
	            .httpBasic(Customizer.withDefaults())
	            .build();
	}
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder().username("admin").password("Admin12345").roles("ADMIN").build();
		return new InMemoryUserDetailsManager(user);
	}

}
