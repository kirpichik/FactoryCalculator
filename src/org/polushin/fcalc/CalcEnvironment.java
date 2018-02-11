package org.polushin.fcalc;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Среда-состояние калькулятора.
 */
public class CalcEnvironment {

	private final Map<String, Double> defines = new HashMap<>();
	private final List<Double> stack = new LinkedList<>();

	/**
	 * Кладет значение на стек.
	 *
	 * @param value Значение.
	 */
	public void push(double value) {
		stack.add(value);
	}

	/**
	 * Удаляет значение со стека.
	 *
	 * @return Удаленное значение.
	 *
	 * @throws IllegalStackException Если стек пуст.
	 */
	public double pop() throws IllegalStackException {
		if (stack.isEmpty())
			throw new IllegalStackException(1, 0);
		return stack.remove(stack.size() - 1);
	}

	/**
	 * Считывает значение со стека.
	 *
	 * @throws IllegalStackException Если стек пуст.
	 */
	public double peek() throws IllegalStackException {
		if (stack.isEmpty())
			throw new IllegalStackException(1, 0);
		return stack.get(stack.size() - 1);
	}

	/**
	 * @return {@code true}, если стек пуст.
	 */
	public boolean isStackEmpty() {
		return stack.isEmpty();
	}

	/**
	 * @return Текущий размер стека.
	 */
	public int stackSize() {
		return stack.size();
	}

	/**
	 * Очищает стек.
	 */
	public void clearStack() {
		stack.clear();
	}

	/**
	 * Ищет define по его имени и получает его значение.
	 *
	 * @param name Имя define.
	 *
	 * @return Значение define.
	 *
	 * @throws DefineNotFoundException Если define с данным именем не найдено.
	 */
	public double getDefinedValue(String name) throws DefineNotFoundException {
		if (name == null)
			throw new IllegalArgumentException("Name cannot be null!");
		Double value = defines.get(name);
		if (value == null)
			throw new DefineNotFoundException(name);
		return value;
	}

	/**
	 * Устанавливает значение define.
	 * Если данное имя еще не было занято, занимает его.
	 *
	 * @param name Имя define.
	 * @param value Значение define.
	 */
	public void setDefine(String name, double value) {
		if (name == null)
			throw new IllegalArgumentException("Name cannot be null!");
		defines.put(name, value);
	}

	/**
	 * Удаляет define.
	 *
	 * @param name Имя define.
	 */
	public void undefValue(String name) {
		if (name == null)
			throw new IllegalArgumentException("Name cannot be null!");
		defines.remove(name);
	}

	/**
	 * Печатает сообщение.
	 *
	 * @param msg Сообщение.
	 */
	public void print(String msg) {
		Calculator.logger.info(msg);
	}

	/**
	 * Печатает число.
	 *
	 * @param value Число.
	 */
	public void print(double value) {
		print(String.valueOf(value));
	}

}
