package com.hk.prj.expense360oauth.security;

import com.hk.prj.expense360oauth.service.OAuthUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.JdbcOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.SecurityFilterChain;

//@Profile({"dev"})
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    private final OAuthUsersService oAuthUsersService;

    public WebSecurityConfig(OAuthUsersService oAuthUsersService) {
        this.oAuthUsersService = oAuthUsersService;
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) ->
                        requests
                                .requestMatchers("/", "*/static/**", "/login", "/h2-console/**", "/oauth/**").permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2Login(oauth ->
                        oauth.userInfoEndpoint(infoEndpoint ->
                                        infoEndpoint.userService(oAuthUsersService))
                                .successHandler((request, response, authentication) -> {
                                            System.out.println("On default success Handler ------------ ***************** ");
                                            DefaultOidcUser defaultOidcUser = (DefaultOidcUser) authentication.getPrincipal();
                                            oAuthUsersService.saveUserAfterOAuth2Authentication(defaultOidcUser);
                                            response.sendRedirect("/");
                                        }
                                ))
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        //return new OAuthUsersService(this.googleClientRegistration());
        return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService(
            ClientRegistrationRepository clientRegistrationRepository) {
        //return new JdbcOAuth2AuthorizedClientService(jdbcTemplate, clientRegistrationRepository);
        return new JdbcOAuth2AuthorizedClientService(jdbcTemplate, clientRegistrationRepository);
    }

    @Bean
    public OAuth2AuthorizedClientRepository authorizedClientRepository(
            OAuth2AuthorizedClientService authorizedClientService) {
        return new AuthenticatedPrincipalOAuth2AuthorizedClientRepository(authorizedClientService);
    }

    private ClientRegistration googleClientRegistration() {
        return CommonOAuth2Provider.GOOGLE.getBuilder("google")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();
    }
}
