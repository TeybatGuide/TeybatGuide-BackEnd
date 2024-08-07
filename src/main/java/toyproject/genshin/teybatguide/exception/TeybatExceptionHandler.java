package toyproject.genshin.teybatguide.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class TeybatExceptionHandler {

    @ExceptionHandler(TeybatBadRequestException.class)
    public ResponseEntity<String> teybatBadRequestExceptionHandler(
            TeybatException exception,
            HttpServletRequest request
    ) {
        logException(exception, request);
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(TeybatNotFoundException.class)
    public ResponseEntity<String> teybatNotFoundExceptionHandler(
            TeybatNotFoundException exception,
            HttpServletRequest request
    ) {
        logException(exception, request);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    private void logException(TeybatException exception, HttpServletRequest request) {
        log.error("url: {}, message: {}", request.getRequestURI(), exception.getMessage());
    }

}
