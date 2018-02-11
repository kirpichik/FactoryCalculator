package org.polushin.fcalc;

import java.io.*;

public class Main {

	public static void main(String... args) throws IOException {
		if (args.length < 1) {
			Calculator.logger.severe("Need arguments: [-i] [input file] <operations>");
			return;
		}

		File input = null;
		File operations = null;

		for (int i = 0; i < args.length; i++) {

			// Установка файла для чтения.
			if (args[i].equals("-i")) {
				if (args.length == i + 1) {
					Calculator.logger.severe("Input file not presented.");
					return;
				} else if (input != null) {
					Calculator.logger.severe("Input file redefined.");
					return;
				}
				input = new File(args[++i]);
				if (!checkFile(input))
					return;
				continue;
			}

			File file = new File(args[i]);

			if (!checkFile(file))
				return;

			// Проверка типа файла
			if (!file.getName().endsWith(".conf")) {
				Calculator.logger.severe("Operations config file must ends with '.conf'");
				return;
			} else if (operations != null) {
				Calculator.logger.severe("Only one operations and multi jars files can be defined.");
				return;
			} else
				operations = file;
		}

		if (operations == null) {
			Calculator.logger.severe("No operations configuration defined.");
			return;
		}

		Reader reader;
		if (input == null) {
			reader = new InputStreamReader(System.in);
			Calculator.logger.info("Calculate:");
		} else
			reader = new FileReader(input);

		new Calculator(operations, reader).execute();
	}

	private static boolean checkFile(File file) {
		if (!file.exists()) {
			Calculator.logger.severe("File " + file.getAbsolutePath() + " not exists.");
			return false;
		} else if (file.isDirectory()) {
			Calculator.logger.severe(file.getAbsolutePath() + " is a directory.");
			return false;
		}
		return true;
	}
}
