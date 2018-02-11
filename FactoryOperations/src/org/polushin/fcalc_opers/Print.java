package org.polushin.fcalc_opers;

import org.polushin.fcalc.CalcEnvironment;
import org.polushin.fcalc.CalcOperation;

/**
 * Печать верхнего элемента стека.
 */
public class Print implements CalcOperation {

	@Override
	public void execute(CalcEnvironment environment, String[] args) {
		environment.print("Value: " + environment.peek());
	}
}
