package toyproject.genshin.teybatguide.jwt.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String secret;
    private int expirationTime;
    private String tokenPrefix;
    private String headerString;

}
