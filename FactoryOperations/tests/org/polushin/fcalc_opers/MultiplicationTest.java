package org.polushin.fcalc_opers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.polushin.fcalc.CalcEnvironment;
import org.polushin.fcalc.Calculator;
import org.polushin.fcalc.IllegalStackException;

import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.polushin.fcalc_opers.TestUtils.process;

class MultiplicationTest {

	private final Multiplication mult = new Multiplication();
	private final CalcEnvironment env = new CalcEnvironment();

	@BeforeAll
	static void setUp() {
		Calculator.logger.setLevel(Level.WARNING);
	}

	@Test
	void executeValid() {
		process(env, mult, 2, 2, 4);
		process(env, mult, 100, 200, 20000);
		process(env, mult, 100, -300, -30000);
		process(env, mult, 0, 0, 0);
		process(env, mult, 2, 0, 0);
		process(env, mult, 0, 3, 0);
	}

	@Test
	void executeInvalid() {
		final String[] args = new String[0];
		assertThrows(IllegalStackException.class, () -> mult.execute(env, args));
		env.push(1);
		assertThrows(IllegalStackException.class, () -> mult.execute(env, args));
	}

}