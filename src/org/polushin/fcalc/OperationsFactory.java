package org.polushin.fcalc;

import java.io.*;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * Фабрика операций калькулятора.
 */
public class OperationsFactory {

	private final Map<String, Class<? extends CalcOperation>> classes;

	public OperationsFactory() {
		classes = new HashMap<>();
	}

	/**
	 * @param operations Файл свободной конфигурации.
	 * @throws IOException Ошибка чтения файла конфигурации.
	 */
	public OperationsFactory(File operations) throws IOException {
		this();
		if (operations != null)
			loadFromFile(operations);
		Calculator.logger.fine("Factory initialised.");
	}

	/**
	 * @param classes Реализации операций.
	 */
	private OperationsFactory(Map<String, Class<? extends CalcOperation>> classes) {
		this.classes = classes;
	}

	/**
	 * Регистрирует операцию и ее класс.
	 *
	 * @param name Название операции.
	 * @param clazz Класс-реализация операции.
	 */
	public void registerOperation(String name, Class<? extends CalcOperation> clazz) {
		if (name == null)
			throw new IllegalArgumentException("Operation name cannot be null!");
		if (clazz == null)
			throw new IllegalArgumentException("Operation implementation cannot be null!");
		classes.put(name.toUpperCase(), clazz);
		Calculator.logger.fine("Operation " + name + " registered.");
	}

	/**
	 * Создает и возвращает объект операции по ее имени.
	 *
	 * @param name Имя операции.
	 *
	 * @return Объект операции или {@code null}.
	 */
	public CalcOperation getOperation(String name) throws OperationCreateException {
		if (name == null)
			throw new IllegalArgumentException("Name cannot be null!");
		Class<? extends CalcOperation> clazz = classes.get(name.toUpperCase());
		if (clazz == null)
			throw new OperationCreateException(name);
		try {
			CalcOperation operation = clazz.newInstance();
			Calculator.logger.fine("Operation " + name + " created.");
			return operation;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new OperationCreateException("Cannot create operation object.", e);
		}
	}

	/**
	 * Загружает операции из свободного файла.
	 */
	@SuppressWarnings("unchecked")
	private void loadFromFile(File operations) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader(operations));

		// Получаем дирректорию с классами
		String line = input.readLine();
		if (line == null)
			throw new IOException("Factory config format error. Class path not defined.");
		Calculator.logger.fine("Loading factory classes...");
		ClassLoader classLoader = new URLClassLoader(new URL[] {new File(operations.getParent(), line).toURI().toURL()});

		while ((line = input.readLine()) != null) {
			int space = line.indexOf(' ');
			if (space == -1)
				throw new IOException("Factory config format error. Operation description syntax error.");
			String operation = line.substring(0, space).toUpperCase();
			String className = line.substring(space + 1);
			if (className.indexOf(' ') != -1)
				throw new IOException("Factory config format error. Class description syntax error.");

			try {
				Class<?> clazz = classLoader.loadClass(className);

				// Проверка наследования
				if (!CalcOperation.class.isAssignableFrom(clazz))
					throw new ClassCastException(className + " isn't inherited from " + CalcOperation.class.getName());

				// Проверка публичного конструктора без аргументов
				clazz.getConstructor();

				classes.put(operation, (Class<? extends CalcOperation>) clazz);
			} catch (ClassNotFoundException | ClassCastException | NoSuchMethodException | SecurityException e) {
				throw new IOException("Factory config error. No realization found for: " + operation, e);
			}
			Calculator.logger.fine("Loaded operation: " + operation);
		}
	}

	/**
	 * Исключение при попытке получения операции по имени.
	 */
	public static class OperationCreateException extends Exception {

		private OperationCreateException(String operation) {
			super("Operation implementation " + operation + " not found.");
		}

		private OperationCreateException(String message, Throwable e) {
			super(message, e);
		}
	}

}
