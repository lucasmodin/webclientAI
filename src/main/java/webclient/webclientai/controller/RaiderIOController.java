package webclient.webclientai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import webclient.webclientai.raiderio_dto.RaiderIOCharacterDTO;
import webclient.webclientai.service.RaiderIOService;

@RestController
public class RaiderIOController {

    private final RaiderIOService raiderIOService;

    public RaiderIOController(RaiderIOService raiderIOService) {
        this.raiderIOService = raiderIOService;
    }

    @GetMapping("/{region}/{realm}/{name}")
    public ResponseEntity<RaiderIOCharacterDTO> getCharacterData(@PathVariable String region,
                                                                @PathVariable String realm,
                                                                @PathVariable String name) {
        RaiderIOCharacterDTO characterData = raiderIOService.fetchMythicPlusData(region, realm, name);
        return ResponseEntity.ok(characterData);
    }
}
