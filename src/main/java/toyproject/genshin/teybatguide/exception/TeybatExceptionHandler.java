package toyproject.genshin.teybatguide.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import toyproject.genshin.teybatguide.base.ErrorResponseData;
import toyproject.genshin.teybatguide.base.value.Code;

@Slf4j
@RestControllerAdvice
public class TeybatExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> validation(ConstraintViolationException e, WebRequest request) {
        logException(e, request);
        return handleExceptionInternal(e, Code.VALIDATION_ERROR, request);
    }

    @ExceptionHandler(TeybatException.class)
    public ResponseEntity<Object> general(TeybatException e, WebRequest request) {
        logException(e, request);
        return handleExceptionInternal(e, e.getErrorCode(), request);
    }

    @ExceptionHandler(TeybatNotFoundException.class)
    public ResponseEntity<Object> notFound(TeybatNotFoundException e, WebRequest request) {
        logException(e, request);
        return handleExceptionInternal(e, Code.NOT_FOUND, request);
    }

    @ExceptionHandler(TeybatBadRequestException.class)
    public ResponseEntity<Object> badRequest(TeybatBadRequestException e, WebRequest request) {
        logException(e, request);
        return handleExceptionInternal(e, Code.BAD_REQUEST, request);
    }

    @ExceptionHandler
    public ResponseEntity<Object> exception(Exception e, WebRequest request) {
        logException(e, request);
        return handleExceptionInternal(e, Code.INTERNAL_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request
    ) {
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private ResponseEntity<Object> handleExceptionInternal(
            Exception e, Code errorCode, WebRequest request
    ) {
        return handleExceptionInternal(e, errorCode, HttpHeaders.EMPTY, errorCode.getHttpStatus(), request);
    }

    private ResponseEntity<Object> handleExceptionInternal(
            Exception e, Code errorCode, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleExceptionInternal(
                e,
                ErrorResponseData.of(errorCode, e),
                headers,
                status,
                request
        );
    }

    private void logException(Exception exception, WebRequest request) {
        if (request instanceof ServletWebRequest) {
            HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();
            log.error("url: {}, message: {}", servletRequest.getRequestURI(), exception.getMessage());
        } else {
            log.error("message: {}", exception.getMessage());
        }
    }

}
