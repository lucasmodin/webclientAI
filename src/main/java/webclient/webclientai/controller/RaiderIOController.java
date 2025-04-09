package webclient.webclientai.controller;

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
    public RaiderIOCharacterDTO getCharacterData(@PathVariable String region,
                                                 @PathVariable String realm,
                                                 @PathVariable String name) {
        return raiderIOService.fetchMythicPlusData(region, realm, name);
    }
}
