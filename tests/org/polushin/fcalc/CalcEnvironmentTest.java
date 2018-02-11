package org.polushin.fcalc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalcEnvironmentTest {

	@Test
	void push() {
		final CalcEnvironment env = new CalcEnvironment();
		env.push(0);
		assertEquals(1, env.stackSize());
		env.push(100);
		assertEquals(2, env.stackSize());
		env.push(-100);
		assertEquals(3, env.stackSize());
		env.push(0);
		assertEquals(4, env.stackSize());
		env.push(0.5);
		assertEquals(5, env.stackSize());
	}

	@Test
	void pop() {
		final CalcEnvironment env = new CalcEnvironment();
		assertThrows(IllegalStackException.class, env::pop);

		env.push(0);
		env.push(1);
		env.push(100);
		env.push(-100);
		env.push(0.5);
		env.push(0);

		assertEquals(6, env.stackSize());
		assertEquals(0, env.pop());
		assertEquals(0.5, env.pop());
		assertEquals(-100, env.pop());
		assertEquals(100, env.pop());
		assertEquals(1, env.pop());
		assertEquals(0, env.pop());
	}

	@Test
	void peek() {
		final CalcEnvironment env = new CalcEnvironment();
		assertThrows(IllegalStackException.class, env::peek);

		env.push(0);
		assertEquals(0, env.peek());

		env.push(1);
		assertEquals(1, env.peek());

		env.pop();
		assertEquals(0, env.peek());
	}

	@Test
	void define() {
		final CalcEnvironment env = new CalcEnvironment();

		assertThrows(DefineNotFoundException.class, () -> env.getDefinedValue(""));
		assertThrows(DefineNotFoundException.class, () -> env.getDefinedValue("name"));
		assertThrows(DefineNotFoundException.class, () -> env.getDefinedValue("123"));

		env.setDefine("name", 0);
		assertEquals(0, env.getDefinedValue("name"));

		env.setDefine("name1", 1);
		assertEquals(1, env.getDefinedValue("name1"));

		env.setDefine("name2", -1);
		assertEquals(-1, env.getDefinedValue("name2"));

		env.setDefine("name", 2);
		assertEquals(2, env.getDefinedValue("name"));

		env.undefValue("name1");
		assertThrows(DefineNotFoundException.class, () -> env.getDefinedValue("name1"));
	}
}