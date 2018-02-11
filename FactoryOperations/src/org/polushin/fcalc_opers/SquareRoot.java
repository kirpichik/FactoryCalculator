package org.polushin.fcalc_opers;

import org.polushin.fcalc.CalcEnvironment;
import org.polushin.fcalc.CalcOperation;

/**
 * Взятие квадратного корня.
 */
public class SquareRoot implements CalcOperation {

	@Override
	public void execute(CalcEnvironment environment, String[] args) {
		environment.push(Math.sqrt(environment.pop()));
		environment.print("Square root taken.");
	}
}
