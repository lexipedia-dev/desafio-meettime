package br.com.meettime.desafiooauth.oAuth2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

@Service
public class OAuth2HubSpotService {
    @Value("${spring.security.oauth2.client.registration.hubspot.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.hubspot.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.hubspot.redirect-uri}")
    private String redirectUri;

    @Value("${spring.security.oauth2.client.provider.hubspot.token-uri}")
    private String tokenUri;

    @Value("${spring.security.oauth2.client.provider.hubspot.authorization-uri}")
    private String authorizationUri;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAuthorizationUrl() {
        String encodedRedirectUri = URLEncoder.encode(redirectUri, StandardCharsets.UTF_8);
        return authorizationUri +
                "?client_id=" + clientId +
                "&redirect_uri=" + encodedRedirectUri +
                "&scope=oauth crm.objects.contacts.write crm.schemas.contacts.write crm.objects.contacts.read" +
                "&response_type=code";
    }

    public ResponseEntity<?> exchangeAuthorizationCodeForToken(String authorizationCode) {

        ResponseEntity<Map> response = fazRequisicaoDoToken(authorizationCode);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            String accessToken = response.getBody().get("access_token").toString();
            return ResponseEntity.ok(accessToken);
        } else {
            return ResponseEntity.status(response.getStatusCode()).body("Falha ao obter o token de acesso.");
        }
    }

    private ResponseEntity<Map> fazRequisicaoDoToken(String authorizationCode) {
        MultiValueMap<String, String> params = geraParametros(authorizationCode);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<Map> response = restTemplate.exchange(tokenUri, HttpMethod.POST, request, Map.class);
        return response;
    }

    private MultiValueMap<String, String> geraParametros(String authorizationCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("grant_type", Collections.singletonList("authorization_code"));
        params.put("client_id", Collections.singletonList(clientId));
        params.put("client_secret", Collections.singletonList(clientSecret));
        params.put("redirect_uri", Collections.singletonList(redirectUri));
        params.put("code", Collections.singletonList(authorizationCode));
        return params;
    }
}
