package org.polushin.fcalc_opers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.polushin.fcalc.CalcEnvironment;
import org.polushin.fcalc.Calculator;
import org.polushin.fcalc.IllegalStackException;

import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.polushin.fcalc_opers.TestUtils.process;

class SubtractionTest {

	private final Subtraction sub = new Subtraction();
	private final CalcEnvironment env = new CalcEnvironment();

	@BeforeAll
	static void setUp() {
		Calculator.logger.setLevel(Level.WARNING);
	}

	@Test
	void executeValid() {
		process(env, sub, 2, 2, 0);
		process(env, sub, 100, 200, -100);
		process(env, sub, 100, -300, 400);
		process(env, sub, 0, 0, 0);
		process(env, sub, 2, 0, 2);
		process(env, sub, 0, 3, -3);
		process(env, sub, 0.5, 1.5, -1);
		process(env, sub, 0.7, 0.4, 0.7 - 0.4);
	}

	@Test
	void executeInvalid() {
		final String[] args = new String[0];
		assertThrows(IllegalStackException.class, () -> sub.execute(env, args));
		env.push(1);
		assertThrows(IllegalStackException.class, () -> sub.execute(env, args));
	}

}