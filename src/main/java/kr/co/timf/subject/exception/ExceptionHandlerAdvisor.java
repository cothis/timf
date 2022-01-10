package kr.co.timf.subject.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class ExceptionHandlerAdvisor {
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<Object> handleIllegalStateException(Exception ex) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.valueOf(400);

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status);

		List<String> errors = new ArrayList<>();
		errors.add(ex.getMessage());
		body.put("errors", errors);

		return new ResponseEntity<>(body, headers, status);
	}
}
