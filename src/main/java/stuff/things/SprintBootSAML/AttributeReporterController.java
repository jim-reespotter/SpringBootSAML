package stuff.things.SprintBootSAML;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttributeReporterController {
    
    @GetMapping("/attributes")
	public String getAttributes(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal) {
		String result = "<h3>Details<h3>\n";
        result += "Name: " + principal.getName() + "\n";
        result += "sp entityID: " + principal.getRelyingPartyRegistrationId() + "\n";
        result += "Attributes: " + principal.getAttributes().toString();
		return result;
	}
}
