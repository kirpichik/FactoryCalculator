package org.polushin.fcalc_opers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.polushin.fcalc.CalcEnvironment;
import org.polushin.fcalc.Calculator;
import org.polushin.fcalc.IllegalStackException;

import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.polushin.fcalc_opers.TestUtils.process;

class DivisionTest {

	private final Division div = new Division();
	private final CalcEnvironment env = new CalcEnvironment();

	@BeforeAll
	static void setUp() {
		Calculator.logger.setLevel(Level.WARNING);
	}

	@Test
	void executeValid() {
		process(env, div, 2, 2, 1);
		process(env, div, 200, 100, 2);
		process(env, div, 3000, 300, 10);
		process(env, div, 0, 3, 0);
		process(env, div, 1, 0.5, 1 / 0.5);
		process(env, div, 1, 0.1, 1 / 0.1);
	}

	@Test
	void executeInvalid() {
		final String[] args = new String[0];
		assertThrows(IllegalStackException.class, () -> div.execute(env, args));
		env.push(1);
		assertThrows(IllegalStackException.class, () -> div.execute(env, args));
		env.clearStack();
		env.push(0);
		env.push(0);
		assertThrows(ArithmeticException.class, () -> div.execute(env, args));
		env.push(2);
		env.push(0);
		assertThrows(ArithmeticException.class, () -> div.execute(env, args));
	}
}