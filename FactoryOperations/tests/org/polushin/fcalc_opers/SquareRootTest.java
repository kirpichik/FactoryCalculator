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

class SquareRootTest {

	private final SquareRoot sqrt = new SquareRoot();
	private final CalcEnvironment env = new CalcEnvironment();

	@BeforeAll
	static void setUp() {
		Calculator.logger.setLevel(Level.WARNING);
	}

	@Test
	void executeValid() {
		process(env, sqrt, 0, 4, 2, 0);
		process(env, sqrt, 0, 256, 16, 00);
		process(env, sqrt, 0, 0, 0, 0);
		process(env, sqrt, 0, 1, 1, 0);
		process(env, sqrt, 0, 3, Math.sqrt(3), 0);
		process(env, sqrt, 0, 0.5, Math.sqrt(0.5), 0);
	}

	@Test
	void executeInvalid() {
		final String[] args = new String[0];
		assertThrows(IllegalStackException.class, () -> sqrt.execute(env, args));
		env.push(-1);
		sqrt.execute(env, args);
		assertTrue(Double.isNaN(env.pop()) && env.isStackEmpty());
		env.push(-5);
		sqrt.execute(env, args);
		assertTrue(Double.isNaN(env.pop()) && env.isStackEmpty());
	}
}