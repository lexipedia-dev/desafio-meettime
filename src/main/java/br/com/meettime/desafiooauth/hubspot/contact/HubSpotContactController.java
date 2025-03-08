package br.com.meettime.desafiooauth.hubspot.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/hubspot")
public class HubSpotContactController {
    @Autowired
    private HubSpotContactService hubSpotContactService;

    @PostMapping("/contacts")
    public ResponseEntity<?> createContact(@RequestHeader("Authorization") String accessToken,
                                           @RequestParam String email,
                                           @RequestParam String firstName,
                                           @RequestParam String lastName) {
        return hubSpotContactService.createContact(accessToken, email, firstName, lastName);
    }
}