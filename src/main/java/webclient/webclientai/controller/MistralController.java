package webclient.webclientai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import webclient.webclientai.service.MistralService;

import java.util.Map;

@RestController
public class MistralController {
    @Value("${openai.api.key}")
    private String openAPIKey;

    @Autowired
    MistralService mistralService;

    @GetMapping("/key")
    public String key() {
        return openAPIKey;
    }

    @GetMapping("/test")
    public Map<String, Object> test(){
        Map<String, Object> testMap = mistralService.promptMistral();
        return testMap;
    }
}
