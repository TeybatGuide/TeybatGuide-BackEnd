package toyproject.genshin.teybatguide.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import toyproject.genshin.teybatguide.controller.dto.oauth.KakaoProfile;
import toyproject.genshin.teybatguide.controller.dto.oauth.KakaoTokenRequest;
import toyproject.genshin.teybatguide.controller.dto.oauth.OauthToken;
import toyproject.genshin.teybatguide.controller.dto.oauth.jwt.JwtProperties;
import toyproject.genshin.teybatguide.domain.User;
import toyproject.genshin.teybatguide.exception.TeybatException;
import toyproject.genshin.teybatguide.repository.UserRepository;

import java.util.Date;
import java.util.Map;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public OauthToken getAccessToken(String code) {
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<KakaoTokenRequest> kakaoTokenRequest =
                new HttpEntity<>(KakaoTokenRequest.of("1", code), headers);

        ResponseEntity<String> accessTokenResponse = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        OauthToken oauthToken = null;

        try {
            oauthToken = objectMapper.readValue(accessTokenResponse.getBody(), OauthToken.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return oauthToken;
    }

    public String saveUserAndGetToken(String token) {
        KakaoProfile profile = findProfile(token);

        if(!existsUserByEmail(profile)){
            userRepository.save(User.of(profile));
        }

        User user = userRepository.findByEmail(profile.getKakao_account().getEmail())
                .orElseThrow(() -> new TeybatException("저장이 안됐습니다."));
        return createToken(user);
    }

    private String createToken(User user) {
        return Jwts.builder()
                .addClaims(Map.of(
                        "id", user.getId(),
                        "nickname", user.getNickname()
                ))
                .signWith(SignatureAlgorithm.HS256, JwtProperties.SECRET)
                .setExpiration(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .compact();
    }

    private Boolean existsUserByEmail(KakaoProfile profile) {
        return userRepository.existsByEmail(profile.getKakao_account().getEmail());
    }

    private KakaoProfile findProfile(String token) {
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

        ResponseEntity<String> kakaoProfileResponse = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper.readValue(kakaoProfileResponse.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return kakaoProfile;
    }


}
