package ac.kr.smu.config;

import ac.kr.smu.jwt.JWTAuthenticationFilter;
import ac.kr.smu.vo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.xml.ws.Action;
import java.util.List;

@Configuration
@EnableWebSecurity //security 자동설정 허용
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)//Security 관련 Annotation 허용
@ComponentScan(basePackages={"ac.kr.smu.service"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().formLogin().loginPage("/").loginProcessingUrl("/login").defaultSuccessUrl("/board",true)
                .and().logout().deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .and().authorizeRequests()
                .antMatchers("/vendor/**","/css/**","/js/**","/scss/**","/img/**","/","/user").permitAll()
                .antMatchers("/board").hasRole("ADMIN")
                .anyRequest().authenticated().and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}