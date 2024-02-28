package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import toyproject.genshin.teybatguide.controller.dto.oauth.OauthToken;
import toyproject.genshin.teybatguide.service.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/oauth/token")
    public ResponseEntity<OauthToken> getLogin(@RequestParam String code) {
        OauthToken accessToken = userService.getAccessToken(code);

        userService.saveUser(accessToken.getAccess_token());

        return ResponseEntity.ok(accessToken);
    }

}
