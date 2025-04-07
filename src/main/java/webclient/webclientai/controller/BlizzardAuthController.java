package webclient.webclientai.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import webclient.webclientai.service.BlizzardAuthService;
import webclient.webclientai.service.BlizzardTokenInspector;

import java.io.IOException;
import java.util.UUID;


@RestController
public class BlizzardAuthController {

    private final String state = UUID.randomUUID().toString();

    @Value("${blizzard.client.id}")
    private String clientId;

    @Value("${blizzard.redirect.uri}")
    private String redirectUri;

    @Value("${blizzard.scope}")
    private String scope;

    @Autowired
    private BlizzardAuthService blizzardAuthService;

    @Autowired
    private BlizzardTokenInspector blizzardTokenInspector;

    @GetMapping("/login")
    public void login(HttpServletResponse response) throws IOException {
        String authUrl = UriComponentsBuilder
                .newInstance()
                .scheme("https")
                .host("oauth.battle.net")
                .path("/authorize")
                .queryParam("client_id", clientId)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("response_type", "code")
                .queryParam("scope", scope)
                .queryParam("state", state)
                .build()
                .toUriString();
        response.sendRedirect(authUrl);
        System.out.println("Login redirected to " + authUrl);
    }

    @GetMapping("/callback")
        public ResponseEntity<Void> callback(@RequestParam("code") String code,
                                               HttpServletResponse response) throws IOException {
            String accessToken = blizzardAuthService.exchangeCodeForToken(code);

            blizzardTokenInspector.inspectAccessToken(accessToken);

            Cookie cookie = new Cookie("access_token", accessToken);
            cookie.setHttpOnly(true);
            cookie.setSecure(false); //only because im doing this in local devolopment, and not intending on deploying!
            cookie.setPath("/");
            cookie.setMaxAge(86400);
            //cookie.setDomain("localhost"); //test to resolve frontend error

            response.addCookie(cookie);
            response.sendRedirect("/account");
            return ResponseEntity.status(302).build();
        }


}
