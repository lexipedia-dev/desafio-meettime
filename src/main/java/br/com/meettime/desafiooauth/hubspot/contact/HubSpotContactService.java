package br.com.meettime.desafiooauth.hubspot.contact;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
public class HubSpotContactService {

    @Value("${hubspot.api.base-url}")
    private String hubSpotBaseUrl;

    private final RestTemplate restTemplate = new RestTemplate();
    private final Bucket bucket;

    public HubSpotContactService() {
        Bandwidth limit = Bandwidth.classic(100, Refill.greedy(100, Duration.ofSeconds(10)));
        this.bucket = Bucket.builder().addLimit(limit).build();
    }

    public ResponseEntity<String> createContact(String accessToken, String email, String firstName, String lastName) {
        if (!bucket.tryConsume(1)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Rate limit exceeded");
        }

        String url = hubSpotBaseUrl + "/crm/v3/objects/contacts";

        HttpEntity<Map<String, Object>> request = geraRequest(accessToken, email, firstName, lastName);
        ResponseEntity<Map> response;
        try{
            response = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);
            return ResponseEntity.status(response.getStatusCode()).body("Contact created successfully");

        } catch (HttpClientErrorException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Um erro desconhecido ocorreu: "+e.getMessage());
        }
    }

    private HttpEntity<Map<String, Object>> geraRequest(String accessToken, String email, String firstName, String lastName) {
        Map<String, Object> contact = new HashMap<>();
        Map<String, String> properties = geraProperties(email, firstName, lastName);
        contact.put("properties", properties);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(contact, headers);
        return request;
    }

    private Map<String, String> geraProperties(String email, String firstName, String lastName) {
        Map<String, String> properties = new HashMap<>();
        properties.put("email", email);
        properties.put("firstname", firstName);
        properties.put("lastname", lastName);
        return properties;
    }
}
