package toyproject.genshin.teybatguide.oauth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import toyproject.genshin.teybatguide.jwt.properties.JwtProperties;
import toyproject.genshin.teybatguide.oauth.OauthService;
import toyproject.genshin.teybatguide.oauth.controller.dto.OauthToken;

@RestController("/oauth")
@RequiredArgsConstructor
public class OauthController {

    private final OauthService oauthService;
    private final JwtProperties jwtProperties;

    @GetMapping("/token")
    public ResponseEntity<String> getLogin(@RequestParam String code) {
        OauthToken accessToken = oauthService.getAccessToken(code);

        String jwtToken = oauthService.saveUserAndGetToken(accessToken.getAccess_token());

        HttpHeaders headers = new HttpHeaders();
        headers.add(jwtProperties.getHeaderString(), jwtProperties.getTokenPrefix() + jwtToken);

        return ResponseEntity.ok().headers(headers).body("success");
    }
}
