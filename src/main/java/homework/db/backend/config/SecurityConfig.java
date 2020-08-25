package homework.db.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
        throws Exception {
        auth.jdbcAuthentication()
            .passwordEncoder(passwordEncoder)
            .dataSource(dataSource)
            .usersByUsernameQuery("select username,password,enabled "
                                      + "from user "
                                      + "where username = ?")
            .authoritiesByUsernameQuery("select user_username, authorities_authority  "
                                            + "from user_authorities "
                                            + "where user_username = ?");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity)
        throws Exception {
        httpSecurity.authorizeRequests()
            .antMatchers("/h2-console/**")
            .permitAll()
            .antMatchers(HttpMethod.POST)
            .authenticated()
            .and()
            .formLogin()
            .and()
            .rememberMe()
            .key("12345");

        httpSecurity.csrf()
            .disable();
        httpSecurity.headers()
            .frameOptions()
            .sameOrigin();

    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}
