package org.polushin.fcalc;

/**
 * Операция калькулятора.
 */
public interface CalcOperation {

	/**
	 * Вызывается для обработки операции с заданными параметрами.
	 *
	 * @param environment Среда-состояние калькулятора
	 * @param args Аргументы операции.
	 *
	 * @throws IllegalArgumentException Ошибка при обработке полученных аргуметов.
	 * @throws IllegalStackException Недостаточное кол-во элеметов на стеке.
	 * @throws DefineNotFoundException Запрашиваемое значение замены не определено.
	 */
	void execute(CalcEnvironment environment, String[] args);

}
