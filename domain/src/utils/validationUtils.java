package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import exceptions.ValidationException;

/**
 * Class used to ease validation tasks
 * @author Dario
 */
public class validationUtils {

	/**
	 * This method requires the source object to perform the validation(i.e. some entity class)
	 * and the validator object from Hibernate validator library.
	 * Throws ValidationException which contains field, message constraint violations
	 * @param source
	 * @param validator
	 * @throws ValidationException
	 */
	public static void validateClass(Object source, Validator validator) throws ValidationException {
		Set<ConstraintViolation<Object>> violations = validator.validate(source);
		if (violations.size() > 0) {

			Map<String,String> violationMessages = new HashMap<String,String>();
			String[] violationStr;
			
			for (ConstraintViolation<Object> violation : violations) {
				violationStr = violation.getMessage().split(":");
				violationMessages.put(violationStr[0], violationStr[1]);
			}
			throw new ValidationException(violationMessages);
		}
	}

}
