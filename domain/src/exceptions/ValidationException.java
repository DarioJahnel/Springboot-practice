package exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	Map<String,String> messages = new HashMap<>();

	public ValidationException(Map<String,String> messages) {

		this.messages = messages;

	}
	
	public Map<String, String> getMessages() {
		return this.messages;
	}

}
