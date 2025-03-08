package br.com.meettime.desafiooauth.hubspot.contact;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/webhooks/contacts")
public class HubSpotContactWebhookController {
    @PostMapping
    public void recebeEventoDeCriacaodeContato(@RequestBody List<HubSpotContactCreationEventDto> contactsDto){
        contactsDto.forEach(contact -> System.out.println(contact.toString()));
        System.out.println("Contato recebido com sucesso");
    }
}
