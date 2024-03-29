package org.polushin.fcalc_opers;

import org.polushin.fcalc.CalcEnvironment;
import org.polushin.fcalc.CalcOperation;
import org.polushin.fcalc.IllegalStackException;

/**
 * Деление.
 */
public class Division implements CalcOperation {

	@Override
	public void execute(CalcEnvironment environment, String[] args) {
		if (environment.stackSize() < 2)
			throw new IllegalStackException(2, environment.stackSize());
		double right = environment.pop();
		if (right == 0) {
			environment.push(right);
			throw new ArithmeticException("Division by zero.");
		}
		double left = environment.pop();
		environment.push(left / right);
		environment.print("Divided.");
	}
}
