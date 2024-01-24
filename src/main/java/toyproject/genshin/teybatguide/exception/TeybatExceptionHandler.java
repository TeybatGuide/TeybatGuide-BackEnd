package toyproject.genshin.teybatguide.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class TeybatExceptionHandler {

    @ExceptionHandler(TeybatException.class)
    public ResponseEntity<String> teybatExceptionHandler(
            TeybatException exception,
            HttpServletRequest request
    ) {
        log.error("url: {}, message: {}", request.getRequestURI(), exception.getMessage());
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
