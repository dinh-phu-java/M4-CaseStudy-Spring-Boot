package com.dinhpu.m4casestudy.config;

import com.dinhpu.m4casestudy.services.user.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled = true,
		jsr250Enabled = true)
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	// add a reference to our security data source
    @Autowired
    private IUserServices userService;


    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    
   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	   auth.authenticationProvider(authenticationProvider());

    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/real-estate/detail/**").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/real-estate/**").authenticated()
			.and()
			.formLogin()
				.loginPage("/loginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.successHandler(customAuthenticationSuccessHandler)
				.permitAll()
			.and()
			.logout()
			.permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
		
	}
	
	//beans
	//bcrypt bean definition
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//authenticationProvider bean definition
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService); //set the custom user details service
		auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
		return auth;
	}
	  
}

//auth.jdbcAuthentication().dataSource(myDataSource)
//		.usersByUsernameQuery(
//		"select username, password, enabled from users " +
//		"where username=?")
//		.authoritiesByUsernameQuery(
//		"select username, authority from authorities " +
//		"where username=?");

//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()                                                                1
//                .antMatchers("/resources/**", "/signup", "/about").permitAll()                  2
//                .antMatchers("/admin/**").hasRole("ADMIN")                                      3
//                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")            4
//                .anyRequest().authenticated()                                                   5
//                .and()
//                // ...
//                .formLogin();
//    }

//<div sec:authorize="isAuthenticated()">
//        This content is only shown to authenticated users.
//</div>
//<div sec:authorize="hasRole('ROLE_ADMIN')">
//        This content is only shown to administrators.
//</div>
//<div sec:authorize="hasRole('ROLE_USER')">
//        This content is only shown to users.
//</div>
//Logged user: <span sec:authentication="name">Bob</span>
//        Roles: <span sec:authentication="principal.authorities">[ROLE_USER, ROLE_ADMIN]</span>

//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/showMyLoginPage")
//                .loginProcessingUrl("/authenticateTheUser")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/my/logout")
//                .logoutSuccessUrl("/my/index")
//                ;



