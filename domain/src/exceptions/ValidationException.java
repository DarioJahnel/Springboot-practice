package exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	Map<String,ArrayList<String>> messages = new HashMap<>();

	public ValidationException(ArrayList<String> messages) {

		this.messages.put("messages", messages);

	}
	
	public Map<String, ArrayList<String>> getMessages() {
		return this.messages;
	}

}
