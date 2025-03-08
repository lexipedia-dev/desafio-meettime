package br.com.meettime.desafiooauth.oAuth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/oauth2")
public class OAuth2HubSpotController {

    @Autowired
    private OAuth2HubSpotService oAuth2HubSpotService;

    @GetMapping("/authorize")
    public RedirectView authorize() {
        return new RedirectView(oAuth2HubSpotService.getAuthorizationUrl());
    }

    @GetMapping("/callback")
    public ResponseEntity<?> handleOAuth2Callback(@RequestParam("code") String authorizationCode) {
        return oAuth2HubSpotService.exchangeAuthorizationCodeForToken(authorizationCode);
    }

}
