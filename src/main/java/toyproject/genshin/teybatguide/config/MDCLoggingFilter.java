package toyproject.genshin.teybatguide.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.jboss.logging.MDC;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MDCLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, @NotNull FilterChain filterChain) throws IOException, ServletException {
        String requestId = ((HttpServletRequest) servletRequest).getHeader("X-RequestID");
        MDC.put("request_id", Objects.toString(requestId, getUUID()));
        filterChain.doFilter(servletRequest, servletResponse);
        MDC.clear();
    }

    private String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
