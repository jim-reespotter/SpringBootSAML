package stuff.things.SprintBootSAML;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.saml2.provider.service.metadata.OpenSamlMetadataResolver;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrationRepository;
import org.springframework.security.saml2.provider.service.servlet.filter.Saml2WebSsoAuthenticationFilter;
import org.springframework.security.saml2.provider.service.web.DefaultRelyingPartyRegistrationResolver;
import org.springframework.security.saml2.provider.service.web.RelyingPartyRegistrationResolver;
import org.springframework.security.saml2.provider.service.web.Saml2MetadataFilter;

@EnableWebSecurity
public class SAMLSecurityConfig extends WebSecurityConfigurerAdapter {
  
    @Autowired
    RelyingPartyRegistrationRepository relyingPartyRegistrationRepository;

    @Autowired
    Saml2LoginSettings settings;
    
    protected void configure(HttpSecurity http) throws Exception {

        RelyingPartyRegistrationResolver relyingPartyRegistrationResolver =
        new DefaultRelyingPartyRegistrationResolver(this.relyingPartyRegistrationRepository);

        Saml2MetadataFilter filter = new Saml2MetadataFilter(relyingPartyRegistrationResolver, new OpenSamlMetadataResolver());

        http
            .saml2Login(settings) // Customizer...
                .addFilterBefore(filter, Saml2WebSsoAuthenticationFilter.class).antMatcher("/**")  // 
            .authorizeRequests()
            .antMatchers("/attributes").hasAuthority("ADMIN")
            .anyRequest().authenticated();
    }
}

