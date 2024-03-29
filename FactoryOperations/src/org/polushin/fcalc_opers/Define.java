package org.polushin.fcalc_opers;

import org.polushin.fcalc.CalcEnvironment;
import org.polushin.fcalc.CalcOperation;

import java.util.regex.Pattern;

/**
 * Объявление переменной.
 */
public class Define implements CalcOperation {

	private static final Pattern IDENTIFIER = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*");

	@Override
	public void execute(CalcEnvironment environment, String[] args) {
		if (args.length != 2 || args[0].isEmpty())
			throw new IllegalArgumentException("Need arguments: <name> <value>");
		double value;
		try {
			value = Double.parseDouble(args[1]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid value type.", e);
		}
		if (!IDENTIFIER.matcher(args[0]).matches())
			throw new IllegalArgumentException("Invalid define type.");
		environment.setDefine(args[0], value);
		environment.print("Defined " + args[0] + " as " + args[1]);
	}
}
