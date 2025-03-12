package toyproject.genshin.teybatguide.jwt;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import toyproject.genshin.teybatguide.jwt.properties.JwtProperties;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtProperties jwtProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtHeader = request.getHeader(jwtProperties.getHeaderString());

        if (jwtHeader == null || !jwtHeader.startsWith(jwtProperties.getTokenPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = jwtHeader.replace(jwtProperties.getTokenPrefix(), "");

        String id = Jwts.parser()
            .setSigningKey("id")
            .parseClaimsJws(token)
            .getSignature();

        request.setAttribute("id", id);
        filterChain.doFilter(request, response);

    }
}
