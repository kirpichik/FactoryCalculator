package org.polushin.fcalc_opers;

import org.polushin.fcalc.CalcEnvironment;
import org.polushin.fcalc.CalcOperation;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Полезные для тестов методы.
 */
public class TestUtils {

	/**
	 * Создает массив из аргуметов.
	 *
	 * @param args Аргументы
	 *
	 * @return Массив из аргуметов.
	 */
	public static String[] array(String... args) {
		return args;
	}

	/**
	 * Проходится по стеку и сравнивает его значения с переданными аргуметами.
	 * Если аргументы закончились до окончания стека, стек будет очищен.
	 *
	 * @param environment Окружение калькулятора.
	 * @param values Значения на стеке в обратном порядке.
	 *
	 * @return {@code true}, если стек совпал и закончился.
	 */
	public static boolean checkStack(CalcEnvironment environment, double... values) {
		int i = 0;
		boolean diff = false;

		while (!environment.isStackEmpty() && i < values.length)
			if (Double.compare(values[i++], environment.pop()) != 0)
				diff = true;

		if (!environment.isStackEmpty() || i < values.length) {
			environment.clearStack();
			return false;
		}

		return !diff;
	}

	/**
	 * Выполняет операцию и проверяет значение стека.
	 *
	 * @param environment Окружение калькулятора.
	 * @param operation Операция.
	 * @param args Аргументы.
	 * @param left Левый операнд.
	 * @param right Правый операнд.
	 * @param stack Конечное значение стека.
	 */
	public static void process(CalcEnvironment environment, CalcOperation operation, String[] args, double left,
	                           double right, double... stack) {
		environment.push(left);
		environment.push(right);
		operation.execute(environment, args);
		assertTrue(checkStack(environment, stack));
	}

	/**
	 * Выполняет операцию и проверяет значение стека.
	 *
	 * @param environment Окружение калькулятора.
	 * @param operation Операция.
	 * @param left Левый операнд.
	 * @param right Правый операнд.
	 * @param stack Конечное значение стека.
	 */
	public static void process(CalcEnvironment environment, CalcOperation operation, double left, double right,
	                           double... stack) {
		final String[] args = new String[] {};
		process(environment, operation, args, left, right, stack);
	}
}
