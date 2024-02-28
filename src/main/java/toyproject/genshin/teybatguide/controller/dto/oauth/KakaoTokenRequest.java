package toyproject.genshin.teybatguide.controller.dto.oauth;

import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class KakaoTokenRequest {

    private String grant_type;

    @Value("${oauth.kakao.client_id}")
    private String client_id;

    private String redirect_uri;

    private String code;

    @Value("${oauth.kakao.client_secret}")
    private String client_secret;

    @Builder
    public KakaoTokenRequest(String grant_type, String redirect_uri, String code) {
        this.grant_type = grant_type;
        this.redirect_uri = redirect_uri;
        this.code = code;
    }

    public static KakaoTokenRequest of(String redirect_uri, String code) {
        return KakaoTokenRequest.builder()
                .grant_type("authorization_code")
                .redirect_uri(redirect_uri)
                .code(code)
                .build();
    }

}
