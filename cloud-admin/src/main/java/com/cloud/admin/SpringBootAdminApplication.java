package com.cloud.admin;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;



@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
public class SpringBootAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdminApplication.class, args);
    }

        @Profile("dev")
        @Configuration
        public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
            @Override
            protected void configure(HttpSecurity http) throws Exception {
                http.authorizeRequests().anyRequest().permitAll()
                        .and().csrf().disable();
            }
        }

        @Profile("docker")
        @Configuration
        public static class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
            private final String adminContextPath;

            public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
                this.adminContextPath = adminServerProperties.getContextPath();
            }

            @Override
            protected void configure(HttpSecurity http) throws Exception {
                SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
                successHandler.setTargetUrlParameter("redirectTo");

                http.authorizeRequests()
                        .antMatchers(adminContextPath + "/assets/**").permitAll()
                        .antMatchers(adminContextPath + "/login").permitAll()
                        .anyRequest().authenticated()
                        .and()
                        .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
                        .logout().logoutUrl(adminContextPath + "/logout").and()
                        .httpBasic().and()
                        .csrf().disable();
            }
        }

}
