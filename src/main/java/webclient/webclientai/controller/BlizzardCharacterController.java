package webclient.webclientai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import webclient.webclientai.service.BlizzardAuthService;
import webclient.webclientai.service.BlizzardCharacterService;

import java.util.Map;

@RestController
public class BlizzardCharacterController {

    @Autowired
    private BlizzardCharacterService blizzardCharacterService;

    @Autowired
    private BlizzardAuthService authService;


    @GetMapping("/characters")
    public ResponseEntity<?> getCharacters(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Map<String, Object> characters = blizzardCharacterService.getCharacters(token);
        return ResponseEntity.ok(characters);
    }

}

