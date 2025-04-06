package webclient.webclientai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import webclient.webclientai.blizzard_dto.AccountDTO;
import webclient.webclientai.service.BlizzardService;


@RestController
public class BlizzardAccountController {

    @Autowired
    BlizzardService blizzardService;

    @GetMapping("/account")
    public ResponseEntity<AccountDTO> getFullAccount(@CookieValue("access_token")String token) {
        AccountDTO account = blizzardService.buildFullAccount(token);
        return ResponseEntity.ok(account);
    }
}
