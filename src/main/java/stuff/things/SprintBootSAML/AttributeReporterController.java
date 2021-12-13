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
        result += "Name: " + principal.getName() + "<br/>\n";
        result += "sp entityID: " + principal.getRelyingPartyRegistrationId() + "<br/>\n";

        result += "Attributes:<br/>\n";
        for (String attName : principal.getAttributes().keySet()) {
            result += attName+": " + principal.getAttributes().get(attName)+"<br/>\n";
        }
		return result;
	}
}
