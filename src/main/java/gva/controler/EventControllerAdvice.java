package gva.controler;

import gva.dto.ErrorDto;
import gva.service.DateTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor
public class EventControllerAdvice {
    private final DateTimeService dateTimeService;

    @ExceptionHandler({ HttpMessageNotReadableException.class, MethodArgumentNotValidException.class })
    public ResponseEntity<ErrorDto> badRequest(Exception exception) {
        return error(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorDto> unsupportedMediaType(Exception exception) {
        return error(exception, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorDto> methodNotAllowed(Exception exception) {
        return error(exception, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDto> notFound(Exception exception) {
        return error(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> internal(Exception exception) {
        return error(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorDto> error(Exception exception, HttpStatus status) {
        ErrorDto error = ErrorDto.builder()
                .timeStamp(dateTimeService.now())
                .message(exception.getMessage())
                .status(status.value())
                .build();
        return new ResponseEntity<>(error, status);
    }
}
