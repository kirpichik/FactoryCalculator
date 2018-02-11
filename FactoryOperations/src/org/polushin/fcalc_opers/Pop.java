package org.polushin.fcalc_opers;

import org.polushin.fcalc.CalcEnvironment;
import org.polushin.fcalc.CalcOperation;
import org.polushin.fcalc.IllegalStackException;

/**
 * Снятие элемента со стека.
 */
public class Pop implements CalcOperation {

	@Override
	public void execute(CalcEnvironment environment, String[] args) {
		environment.pop();
		environment.print("Popped.");
	}
}
