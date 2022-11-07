package com.se.sample.springboottetinggradle.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Base API Response class, containing parameterized constructor to build
 * the API Response Message .
 *
 * @author pylypchenko
 */

@Data
public class ApiGeneralResponse {

//	@Schema(description = "Http status code", example = "200")
	private int code;

//	@Schema(description = "Message", example = "Request received")
	private String message;

//	@Schema(description = "Http status", example = "OK")
	private HttpStatus status;

//	@Schema(description = "Response time", example = "MM-dd-yyyy hh:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
	private LocalDateTime timestamp;

//	@Schema(description = "Response data")
	private Object data;

	private ApiGeneralResponse() {
		timestamp = LocalDateTime.now();
	}

	public ApiGeneralResponse(HttpStatus status, String message) {
		this();
		this.status = status;
		this.code = status.value();
		this.message = message;
	}

	public ApiGeneralResponse(HttpStatus status, String message, Object data) {
		this();
		this.status = status;
		this.code = status.value();
		this.message = message;
		this.data = data;
	}

}
