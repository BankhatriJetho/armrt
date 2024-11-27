// SecurityConfig.java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
           .antMatchers("/api/**").authenticated()
           .anyRequest().permitAll()
           .and()
           .oauth2ResourceServer()
           .jwt();
   }

   @Bean
   JwtDecoder jwtDecoder() {
       NimbusJwtDecoder decoder = (NimbusJwtDecoder) JwtDecoders.fromIssuerLocation("https://saviynt.com/oauth/token");
       decoder.setJwsKeySelector(new IssuerJwsKeySelector<>(Collections.singleton("saviynt.com")));
       return decoder;
   }
}