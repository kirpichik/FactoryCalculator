package org.polushin.fcalc_opers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.polushin.fcalc.CalcEnvironment;
import org.polushin.fcalc.Calculator;

import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DefineTest {

	private final Define def = new Define();
	private final CalcEnvironment env = new CalcEnvironment();

	@BeforeAll
	static void setUp() {
		Calculator.logger.setLevel(Level.WARNING);
	}

	@Test
	void executeValid() {
		final String[] args = new String[] {"a", "4"};
		def.execute(env, args);
		assertEquals((double) 4, env.getDefinedValue("a"));

		args[0] = "b";
		args[1] = "4.2";
		def.execute(env, args);
		assertEquals(4.2, env.getDefinedValue("b"));

		args[0] = "a";
		args[1] = "5";
		def.execute(env, args);
		assertEquals((double) 5, env.getDefinedValue("a"));
	}

	@Test
	void executeInvalid() {
		final String[] args0 = new String[0];
		final String[] args1 = new String[] {""};
		final String[] args2 = new String[] {"", ""};

		assertThrows(IllegalArgumentException.class, () -> def.execute(env, args0));
		assertThrows(IllegalArgumentException.class, () -> def.execute(env, args1));
		assertThrows(IllegalArgumentException.class, () -> def.execute(env, args2));

		args1[0] = "a";
		assertThrows(IllegalArgumentException.class, () -> def.execute(env, args1));

		args1[0] = "1";
		assertThrows(IllegalArgumentException.class, () -> def.execute(env, args1));

		args2[0] = "a";
		assertThrows(IllegalArgumentException.class, () -> def.execute(env, args2));

		args2[0] = "a";
		args2[1] = "b";
		assertThrows(IllegalArgumentException.class, () -> def.execute(env, args2));

		args2[0] = "123";
		args2[1] = "456";
		assertThrows(IllegalArgumentException.class, () -> def.execute(env, args2));

		args2[0] = "123.456";
		assertThrows(IllegalArgumentException.class, () -> def.execute(env, args2));

		args2[0] = "1asder";
		assertThrows(IllegalArgumentException.class, () -> def.execute(env, args2));
	}
}