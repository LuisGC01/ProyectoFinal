package mx.unam.dgtic.diplomado.proyecto;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
		PasswordEncoder encoder = passwordEncoder();
		builder.jdbcAuthentication().dataSource(dataSource).passwordEncoder(encoder)
				.usersByUsernameQuery(
						"select usu_nombre_usuario,usu_password,usu_activo from usuario where usu_nombre_usuario=?")
				.authoritiesByUsernameQuery(
						"select u.usu_nombre_usuario,r.r_rol from rol r inner join usuario u on r.id_usuario = u.id_usuario where u.usu_nombre_usuario=?");
		/*
		 * UserBuilder users = User.builder().passwordEncoder(encoder::encode);
		 * builder.inMemoryAuthentication().withUser(users.username("admin").password(
		 * "12345").roles("ADMIN", "USER"))
		 * .withUser(users.username("LUIS").password("12345").roles("USER"));
		 */

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated().and().formLogin()
				.defaultSuccessUrl("/").permitAll().and().logout().permitAll().and().exceptionHandling()
				.accessDeniedPage("/error_403");
	}
}
