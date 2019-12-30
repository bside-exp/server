package bundang.exp.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExpExceptionHandler {

    @ExceptionHandler(ExpException.class)
    public ExceptionResponse handleExpException(ExpException exp){
        return new ExceptionResponse(exp);
    }
}
