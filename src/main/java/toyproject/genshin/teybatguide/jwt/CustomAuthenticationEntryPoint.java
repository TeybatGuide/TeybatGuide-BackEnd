package toyproject.genshin.teybatguide.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import toyproject.genshin.teybatguide.jwt.properties.JwtProperties;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final JwtProperties jwtProperties;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String exception = request.getHeader(jwtProperties.getHeaderString());
        String errorCode;

        if (exception.equals("토큰이 만료되었습니다.")) {
            errorCode = "토큰이 만료되었습니다.";
            setResponse(response, errorCode);
        }

        if (exception.equals("유효하지 않은 토큰입니다.")) {
            errorCode = "유효하지 않은 토큰입니다.";
            setResponse(response, errorCode);
        }
    }

    private void setResponse(HttpServletResponse response, String errorCode) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(jwtProperties.getHeaderString() + " : " + errorCode);
    }
}
