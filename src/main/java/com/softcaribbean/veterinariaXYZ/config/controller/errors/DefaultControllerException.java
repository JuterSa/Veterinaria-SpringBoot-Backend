package com.softcaribbean.veterinariaXYZ.config.controller.errors;

import com.softcaribbean.veterinariaXYZ.util.MessagesConstants;
import com.softcaribbean.veterinariaXYZ.util.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class DefaultControllerException  extends ResponseEntityExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(DefaultControllerException.class);

	@ExceptionHandler(value
			= { ApplicationCustomException.class})
	protected ResponseEntity<Object> handleAppException(
			ApplicationCustomException ex, WebRequest request) {
		log.error("{}", ex);
		return handleExceptionInternal(ex, ex.getResponse(),
				new HttpHeaders(), HttpStatus.OK, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		log.error("{}", ex);
		StringBuilder errors = new StringBuilder();
		ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
			log.error("Invalid {} value submitted for {}",
					fieldError.getRejectedValue(), fieldError.getField());
			errors.append(String.format(fieldError.getDefaultMessage(), fieldError.getField() ) ).append(" | ");

		});
		return handleExceptionInternal(ex, new ResponseMessage<Object>(MessagesConstants.ERROR_FIELD_VALIDATION_CODE, errors.toString(), null ),
				new HttpHeaders(), HttpStatus.OK, request);
	}


	@ExceptionHandler(value
			= { Exception.class})
	protected ResponseEntity<Object> handleOthers(
			ApplicationCustomException ex, WebRequest request) {
		log.error("{}", ex);
		return handleExceptionInternal(ex, new ResponseMessage<Object>(MessagesConstants.DEFAULT_ERROR_CODE, MessagesConstants.DEFAULT_ERROR_MESSAGE, null),
				new HttpHeaders(), HttpStatus.OK, request);
	}
}
