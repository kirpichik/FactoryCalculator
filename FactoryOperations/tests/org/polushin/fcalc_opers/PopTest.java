package org.polushin.fcalc_opers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.polushin.fcalc.CalcEnvironment;
import org.polushin.fcalc.Calculator;
import org.polushin.fcalc.IllegalStackException;

import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PopTest {

	private final Pop pop = new Pop();
	private final CalcEnvironment env = new CalcEnvironment();

	@BeforeAll
	static void setUp() {
		Calculator.logger.setLevel(Level.WARNING);
	}

	@Test
	void executeValid() {
		final String[] args = new String[0];
		env.push(1);
		env.push(2);
		pop.execute(env, args);
		assertEquals(1, env.stackSize());
		pop.execute(env, args);
		assertEquals(0, env.stackSize());
	}

	@Test
	void executeInvalid() {
		final String[] args = new String[0];
		assertThrows(IllegalStackException.class, () -> pop.execute(env, args));
	}
}