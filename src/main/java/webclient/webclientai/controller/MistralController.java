package webclient.webclientai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webclient.webclientai.ai_dto.Choice;
import webclient.webclientai.ai_dto.UserPromptDTO;
import webclient.webclientai.blizzard_dto.Item_dto.EquippedItemDTO;
import webclient.webclientai.raiderio_dto.RaiderIOCharacterDTO;
import webclient.webclientai.service.BlizzardEquipmentService;
import webclient.webclientai.service.MistralService;
import webclient.webclientai.service.RaiderIOService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class MistralController {
    @Value("${openai.api.key}")
    private String openAPIKey;

    @Autowired
    private MistralService mistralService;

    @Autowired
    private RaiderIOService raiderIOService;

    @Autowired
    private BlizzardEquipmentService blizzardEquipmentService;



    @GetMapping("/key")
    public String key() {
        return openAPIKey;
    }

    @GetMapping("/test")
    public Map<String, Object> test(){
        Map<String, Object> testMap = mistralService.promptMistral();
        return testMap;
    }

    @PostMapping("/ai/ask")
    public ResponseEntity<?> askAI (@RequestBody UserPromptDTO userPromptDTO,
                                    @CookieValue("access_token") String accessToken) {

        RaiderIOCharacterDTO character =
                raiderIOService.fetchMythicPlusData("eu", userPromptDTO.getRealm(), userPromptDTO.getCharacterName());
        List<EquippedItemDTO> gear =
                blizzardEquipmentService.fetchEquippedItems(userPromptDTO.getRealm(), userPromptDTO.getCharacterName(), accessToken);

        if (character == null || gear.isEmpty()) {
            return ResponseEntity.badRequest().body("Missing character data or gear.");
        }

        Map<String, Object> response =
                mistralService.promptMistralWithCharacterContext(character, gear, userPromptDTO.getUserMessage());

        // Extract message from response to return to frontend
        List<Choice> choices = (List<Choice>) response.get("Choices");
        String reply = choices != null && !choices.isEmpty() ? choices.get(0).getMessage().getContent() : "No AI response";

        return ResponseEntity.ok(Map.of("reply", reply));
    }
}
