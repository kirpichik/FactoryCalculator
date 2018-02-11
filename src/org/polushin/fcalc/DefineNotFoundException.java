package org.polushin.fcalc;

/**
 * Исключение при попытке получения define по имени.
 */
public class DefineNotFoundException extends RuntimeException {
	public DefineNotFoundException(String operation) {
		super(operation + " is not defined.");
	}
}
