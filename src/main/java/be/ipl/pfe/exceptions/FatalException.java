package be.ipl.pfe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FatalException extends RuntimeException {
	public FatalException() {
		super("Something went wrong.");
	}

}
