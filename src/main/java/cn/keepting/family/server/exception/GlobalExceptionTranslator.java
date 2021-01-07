package cn.keepting.family.server.exception;

import cn.keepting.family.server.constant.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionTranslator {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BaseResponse handleError(MissingServletRequestParameterException e) {
        String message = String.format("Missing Request Parameter: %s", e.getParameterName());
        log.warn("MissingServletRequestParameterException {}", message);
        return BaseResponse.error(ResultCode.BAD_REQUEST.getStatus(), message);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public BaseResponse handleError(MethodArgumentTypeMismatchException e) {
        String message = String.format("Method Argument Type Mismatch: %s", e.getName());
        log.warn("MethodArgumentTypeMismatchException {}", message);
        return BaseResponse.error(ResultCode.BAD_REQUEST.getStatus(), message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse handleError(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
        log.warn("MethodArgumentNotValidException {}", message);
        return BaseResponse.error(ResultCode.BAD_REQUEST.getStatus(), message);
    }

    @ExceptionHandler(BindException.class)
    public BaseResponse handleError(BindException e) {
        FieldError error = e.getFieldError();
        String message = error.getDefaultMessage();
        log.warn("Bind Exception {},{}", error.getField(), message);
        return BaseResponse.error(ResultCode.BAD_REQUEST.getStatus(), message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResponse handleError(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
        String message = String.format("%s:%s", path, violation.getMessage());
        log.warn("ConstraintViolationException {}", message);
        return BaseResponse.error(ResultCode.BAD_REQUEST.getStatus(), message);
    }

    @ExceptionHandler(ServiceException.class)
    public BaseResponse handleError(ServiceException e) {
        log.warn(e.getMessage(), e);
        return BaseResponse.error(e.getResultCode().getStatus(), e.getMessage());

    }

    @ExceptionHandler(Exception.class)
    public BaseResponse handleError(Exception e) {
        log.error(e.getMessage(), e);
        return BaseResponse.error(ResultCode.INTERNAL_SERVER_ERROR.getStatus(), ResultCode.INTERNAL_SERVER_ERROR.getErrmsg());


    }

}
