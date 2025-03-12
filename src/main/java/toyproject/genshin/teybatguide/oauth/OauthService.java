package toyproject.genshin.teybatguide.oauth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import toyproject.genshin.teybatguide.exception.TeybatDataAccessException;
import toyproject.genshin.teybatguide.jwt.properties.JwtProperties;
import toyproject.genshin.teybatguide.oauth.controller.dto.KakaoProfile;
import toyproject.genshin.teybatguide.oauth.controller.dto.KakaoTokenRequest;
import toyproject.genshin.teybatguide.oauth.controller.dto.OauthToken;
import toyproject.genshin.teybatguide.user.entity.User;
import toyproject.genshin.teybatguide.user.service.UserService;

import java.util.Date;
import java.util.Map;

import static toyproject.genshin.teybatguide.config.SecurityConfig.FRONT_URL;

@Slf4j
@Service
@RequiredArgsConstructor
public class OauthService {

    private final JwtProperties jwtProperties;
    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final WebClient webClient;

    public String saveUserAndGetToken(String token) {
        KakaoProfile profile = findProfile(token);

        if (!userService.existsUserByEmail(profile)) {
            userService.saveUser(User.of(profile));
        }

        User user = userService.findByEmail(profile.getKakao_account().getEmail());
        return createToken(user);
    }

    public OauthToken getAccessToken(String code) {
        return webClient.post()
            .uri("https://kauth.kakao.com/oauth/token")
            .bodyValue(KakaoTokenRequest.of(FRONT_URL + "/auth", code))
            .retrieve()
            .bodyToMono(OauthToken.class)
            .block();
    }

    private String createToken(User user) {
        return Jwts.builder()
            .addClaims(Map.of(
                "id", user.getId(),
                "nickname", user.getNickname()
            ))
            .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
            .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationTime()))
            .compact();
    }

    private KakaoProfile findProfile(String token) {
        try {
            String responseBody = webClient.post()
                .uri("/v2/user/me")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(String.class)
                .block();

            return objectMapper.readValue(responseBody, KakaoProfile.class);
        } catch (JsonProcessingException e) {
            log.error("JSON 파싱 오류", e);
            throw new TeybatDataAccessException("카카오 프로필 정보를 가져오는 중 오류 발생");
        }
    }

}
