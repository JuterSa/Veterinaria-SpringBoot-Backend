package com.softcaribbean.veterinariaXYZ;


import org.springframework.boot.web.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.authorization.AuthorizationManagers;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;



/*@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
                // ...
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/**").permitAll()
                        //.requestMatchers("/api/**").hasRole("ADMIN")
                        //.requestMatchers("/db/**").access(new WebExpressionAuthorizationManager("hasRole('ADMIN') and hasRole('DBA')"))
                        // .requestMatchers("/db/**").access(AuthorizationManagers.allOf(AuthorityAuthorizationManager.hasRole("ADMIN"), AuthorityAuthorizationManager.hasRole("DBA")))
                        .anyRequest().denyAll()
                );

        return http.build();
    }

    }
    /*
    * @Bean
SecurityFilterChain web(HttpSecurity http) throws AuthenticationException {
    http
        .authorizeHttpRequests((authorize) -> authorize
            .anyRequest().authenticated();
        )
        // ...

    return http.build();
}
* @Bean
SecurityFilterChain web(HttpSecurity http) throws Exception {
	http
		// ...
		.authorizeHttpRequests(authorize -> authorize
			.requestMatchers("/resources/**", "/signup", "/about").permitAll()
			.requestMatchers("/admin/**").hasRole("ADMIN")
			.requestMatchers("/db/**").access(new WebExpressionAuthorizationManager("hasRole('ADMIN') and hasRole('DBA')"))
			// .requestMatchers("/db/**").access(AuthorizationManagers.allOf(AuthorityAuthorizationManager.hasRole("ADMIN"), AuthorityAuthorizationManager.hasRole("DBA")))
			.anyRequest().denyAll()
		);

	return http.build();
}
@Bean
SecurityFilterChain web(HttpSecurity http, AuthorizationManager<RequestAuthorizationContext> access)
        throws AuthenticationException {
    http
        .authorizeHttpRequests((authorize) -> authorize
            .anyRequest().access(access)
        )
        // ...

    return http.build();
}

@Bean
AuthorizationManager<RequestAuthorizationContext> requestMatcherAuthorizationManager(HandlerMappingIntrospector introspector) {
    MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
    RequestMatcher permitAll =
            new AndRequestMatcher(
                    mvcMatcherBuilder.pattern("/resources/**"),
                    mvcMatcherBuilder.pattern("/signup"),
                    mvcMatcherBuilder.pattern("/about"));
    RequestMatcher admin = mvcMatcherBuilder.pattern("/admin/**");
    RequestMatcher db = mvcMatcherBuilder.pattern("/db/**");
    RequestMatcher any = AnyRequestMatcher.INSTANCE;
    AuthorizationManager<HttpServletRequest> manager = RequestMatcherDelegatingAuthorizationManager.builder()
            .add(permitAll, (context) -> new AuthorizationDecision(true))
            .add(admin, AuthorityAuthorizationManager.hasRole("ADMIN"))
            .add(db, AuthorityAuthorizationManager.hasRole("DBA"))
            .add(any, new AuthenticatedAuthorizationManager())
            .build();
    return (context) -> manager.check(context.getRequest());
}

Copied!
You can also wire your own custom authorization managers for any request matcher.

Here is an example of mapping a custom authorization manager to the my/authorized/endpoint:

Custom Authorization Manager
Java

@Bean
SecurityFilterChain web(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((authorize) -> authorize
            .requestMatchers("/my/authorized/endpoint").access(new CustomAuthorizationManager());
        )
        // ...

    return http.build();
}

Copied!
Or you can provide it for all requests as seen below:

Custom Authorization Manager for All Requests
Java

@Bean
SecurityFilterChain web(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((authorize) -> authorize
            .anyRequest().access(new CustomAuthorizationManager());
        )
        // ...

    return http.build();
}

Copied!
By default, the AuthorizationFilter applies to all dispatcher types. We can configure Spring Security to not apply the authorization rules to all dispatcher types by using the shouldFilterAllDispatcherTypes method:

Set shouldFilterAllDispatcherTypes to false
Java

Kotlin

@Bean
SecurityFilterChain web(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((authorize) -> authorize
            .shouldFilterAllDispatcherTypes(false)
            .anyRequest().authenticated()
        )
        // ...

    return http.build();
}

Copied!
Instead of setting shouldFilterAllDispatcherTypes to false, the recommended approach is to customize authorization on the dispatcher types. For example, you may want to grant all access on requests with dispatcher type ASYNC or FORWARD.

Permit ASYNC and FORWARD dispatcher type
Java

Kotlin

@Bean
SecurityFilterChain web(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((authorize) -> authorize
            .dispatcherTypeMatchers(DispatcherType.ASYNC, DispatcherType.FORWARD).permitAll()
            .anyRequest().authenticated()
        )
        // ...

    return http.build();
}

Copied!
You can also customize it to require a specific role for a dispatcher type:

Require ADMIN for Dispatcher Type ERROR
Java

Kotlin

@Bean
SecurityFilterChain web(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((authorize) -> authorize
            .dispatcherTypeMatchers(DispatcherType.ERROR).hasRole("ADMIN")
            .anyRequest().authenticated()
        )
        // ...

    return http.build();
}

Copied!
Request Matchers
The RequestMatcher interface is used to determine if a request matches a given rule. We use securityMatchers to determine if a given HttpSecurity should be applied to a given request. The same way, we can use requestMatchers to determine the authorization rules that we should apply to a given request. Look at the following example:

Java

Kotlin

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.securityMatcher("/api/**")
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/user/**").hasRole("USER")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
			)
			.formLogin(withDefaults());
		return http.build();
	}
}

Copied!
Configure HttpSecurity to only be applied to URLs that start with /api/
Allow access to URLs that start with /user/ to users with the USER role
Allow access to URLs that start with /admin/ to users with the ADMIN role
Any other request that doesn’t match the rules above, will require authentication
The securityMatcher(s) and requestMatcher(s) methods will decide which RequestMatcher implementation fits best for your application: If Spring MVC is in the classpath, then MvcRequestMatcher will be used, otherwise, AntPathRequestMatcher will be used. You can read more about the Spring MVC integration here.

If you want to use a specific RequestMatcher, just pass an implementation to the securityMatcher and/or requestMatcher methods:

Java

Kotlin

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import static org.springframework.security.web.util.matcher.RegexRequestMatcher.regexMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.securityMatcher(antMatcher("/api/**"))
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(antMatcher("/user/**")).hasRole("USER")
				.requestMatchers(regexMatcher("/admin/.*")).hasRole("ADMIN")
				.requestMatchers(new MyCustomRequestMatcher()).hasRole("SUPERVISOR")
				.anyRequest().authenticated()
			)
			.formLogin(withDefaults());
		return http.build();
	}
}

public class MyCustomRequestMatcher implements RequestMatcher {

    @Override
    public boolean matches(HttpServletRequest request) {
        // ...
    }
}

Copied!
Import the static factory methods from AntPathRequestMatcher and RegexRequestMatcher to create RequestMatcher instances.
Configure HttpSecurity to only be applied to URLs that start with /api/, using AntPathRequestMatcher
Allow access to URLs that start with /user/ to users with the USER role, using AntPathRequestMatcher
Allow access to URLs that start with /admin/ to users with the ADMIN role, using RegexRequestMatcher
Allow access to URLs that match the MyCustomRequestMatcher to users with the SUPERVISOR role, using a custom RequestMatcher*/


@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http.headers().cacheControl().and()
                .contentSecurityPolicy("default-src 'self'; script-src 'self'; object-src 'self'; frame-ancestors 'self'; form-action 'self';")
                .and()
                .frameOptions()
                .deny();
        http.csrf().disable().authorizeHttpRequests().requestMatchers("**/health").permitAll().and().authorizeHttpRequests()
                .and().authorizeHttpRequests().requestMatchers("/api/**")
                //.authenticated()
                .permitAll().anyRequest().permitAll();
        //  http.addFilterBefore(awsCognitoJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.csrf().ignoringRequestMatchers("**/health").disable();
        return http.build();
    }
}