package org.polushin.fcalc_opers;

import org.polushin.fcalc.CalcEnvironment;
import org.polushin.fcalc.CalcOperation;

/**
 * Взятие квадратного корня.
 */
public class SquareRoot implements CalcOperation {

	@Override
	public void execute(CalcEnvironment environment, String[] args) {
		double value = environment.pop();
		if (value < 0) {
			environment.push(value);
			throw new ArithmeticException("Square root from < 0 number.");
		}
		environment.push(Math.sqrt(value));
		environment.print("Square root taken.");
	}
}
