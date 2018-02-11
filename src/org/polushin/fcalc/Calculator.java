package org.polushin.fcalc;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Logger;

/**
 * Калькулятор на динамических операциях.
 */
public class Calculator {

	public static final Logger logger = Logger.getLogger("Calculator");

	private final OperationsFactory factory;
	private final BufferedReader input;

	/**
	 * Загружает операции в фабрику из набора Jar-файлов и свободного файла операций.
	 *
	 * @param operations Набор свободного файла операций.
	 * @param input Входной поток инструкций.
	 * @throws IOException При ошибке загрузки операций.
	 */
	public Calculator(File operations, Reader input) throws IOException {
		this.input = new BufferedReader(input);
		factory = new OperationsFactory(operations);
	}

	/**
	 * Исполняет команды из входного потока.
	 *
	 * @throws IOException Ошибка чтения из потока.
	 */
	public void execute() throws IOException {
		CalcEnvironment environment = new CalcEnvironment();
		String line;
		while ((line = input.readLine()) != null) {
			String[] split = line.split(" ");
			String[] args = new String[split.length - 1];
			System.arraycopy(split, 1, args, 0, args.length);

			if (split[0].isEmpty())
				continue;

			try {
				factory.getOperation(split[0]).execute(environment, args);
			} catch (OperationsFactory.OperationCreateException e) {
				logger.warning("Operation not found.");
				continue;
			} catch (IllegalArgumentException e) {
				logger.warning("Illegal operation arguments: " + e.getMessage());
				continue;
			} catch (IllegalStackException e) {
				logger.warning("Illegal stack state: " + e.getMessage());
				continue;
			} catch (DefineNotFoundException e) {
				logger.warning(e.getMessage());
			}
			logger.fine(split[0] + " executed successfully.");
		}
	}

}
