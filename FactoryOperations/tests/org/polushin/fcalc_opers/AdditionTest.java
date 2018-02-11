package org.polushin.fcalc_opers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.polushin.fcalc.CalcEnvironment;
import org.polushin.fcalc.Calculator;
import org.polushin.fcalc.IllegalStackException;

import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.polushin.fcalc_opers.TestUtils.process;

class AdditionTest {

	private final Addition add = new Addition();
	private final CalcEnvironment env = new CalcEnvironment();

	@BeforeAll
	static void setUp() {
		Calculator.logger.setLevel(Level.WARNING);
	}

	@Test
	void executeValid() {
		process(env, add, 2, 2, 4);
		process(env, add, 100, 200, 300);
		process(env, add, 100, -300, -200);
		process(env, add, 0, 0, 0);
		process(env, add, 2, 0, 2);
		process(env, add, 0, 3, 3);
		process(env, add, 0.5, 1.5, 2);
		process(env, add, 0.7, 0.3, 1);
	}

	@Test
	void executeInvalid() {
		final String[] args = new String[0];
		assertThrows(IllegalStackException.class, () -> add.execute(env, args));
		env.push(1);
		assertThrows(IllegalStackException.class, () -> add.execute(env, args));
	}
}