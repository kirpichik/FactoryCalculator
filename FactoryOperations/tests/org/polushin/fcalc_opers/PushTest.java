package org.polushin.fcalc_opers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.polushin.fcalc.CalcEnvironment;
import org.polushin.fcalc.Calculator;
import org.polushin.fcalc.DefineNotFoundException;

import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PushTest {

	private final Push push = new Push();
	private final CalcEnvironment env = new CalcEnvironment();

	@BeforeAll
	static void setUp() {
		Calculator.logger.setLevel(Level.WARNING);
	}

	@Test
	void executeValid() {
		String[] args = new String[] {"1"};
		push.execute(env, args);
		assertEquals(1, env.stackSize());

		args[0] = "0";
		push.execute(env, args);
		assertEquals(2, env.stackSize());

		args[0] = "0.5";
		push.execute(env, args);
		assertEquals(3, env.stackSize());

		env.setDefine("abc", 3);
		args[0] = "abc";
		push.execute(env, args);
		assertEquals(4, env.stackSize());
	}

	@Test
	void executeInvalid() {
		final String[] emptyArgs = new String[0];
		final String[] args = new String[] {""};
		assertThrows(IllegalArgumentException.class, () -> push.execute(env, emptyArgs));

		assertThrows(DefineNotFoundException.class, () -> push.execute(env, args));

		args[0] = "abc";
		assertThrows(DefineNotFoundException.class, () -> push.execute(env, args));
	}
}