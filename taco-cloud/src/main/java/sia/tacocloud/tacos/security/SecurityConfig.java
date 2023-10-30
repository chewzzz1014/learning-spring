package sia.tacocloud.tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import sia.tacocloud.tacos.data.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {
        List<UserDetails> usersList = new ArrayList<>();

        usersList.add(new User(
                "buzz",
                encoder.encode("password"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
        ));
        usersList.add(new User(
                "woody",
                encoder.encode("password"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
        ));
        return new InMemoryUserDetailsManager(usersList);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/design").hasRole("USER")
                    .requestMatchers("/orders").hasRole("USER")
                    .requestMatchers("/", "/**").permitAll()
                );
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http,
//                                                      HandlerMappingIntrospector introspector) throws Exception {
//        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
//
//        http.authorizeHttpRequests(auth ->
//                auth
//                        .requestMatchers(mvcMatcherBuilder.pattern(API_URL_PATTERN)).permitAll()
//                        //This line is optional in .authenticated() case as .anyRequest().authenticated()
//                        //would be applied for H2 path anyway
//                        .requestMatchers(PathRequest.toH2Console()).authenticated()
//                        .anyRequest().authenticated()
//        );
//
//        return http.build();
//    }

}
