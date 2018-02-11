package org.polushin.fcalc_opers;

import org.polushin.fcalc.CalcEnvironment;
import org.polushin.fcalc.CalcOperation;

/**
 * Помещение элемента на стек.
 */
public class Push implements CalcOperation {

	@Override
	public void execute(CalcEnvironment environment, String[] args) {
		if (args.length != 1)
			throw new IllegalArgumentException("Need arguments: <value>");
		double value;
		try {
			value = Double.parseDouble(args[0]);
		} catch (NumberFormatException e) {
			value = environment.getDefinedValue(args[0]);
		}
		environment.push(value);
		environment.print("Pushed " + value);
	}
}
