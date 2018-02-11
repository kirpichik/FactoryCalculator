package org.polushin.fcalc_opers;

import org.polushin.fcalc.CalcEnvironment;
import org.polushin.fcalc.CalcOperation;
import org.polushin.fcalc.IllegalStackException;

/**
 * Вычитание.
 */
public class Subtraction implements CalcOperation {

	@Override
	public void execute(CalcEnvironment environment, String[] args) {
		if (environment.stackSize() < 2)
			throw new IllegalStackException(2, environment.stackSize());
		double right = environment.pop();
		double left = environment.pop();
		environment.push(left - right);
		environment.print("Subtracted.");
	}
}
