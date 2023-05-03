package mx.unam.ciencias.edd.sort;

import mx.unam.ciencias.edd.*;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Lectura {

	private Lectura() {
	}

	public static Lista<String> deEstandar(Lista<String> l) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String linea;
			while ((linea = in.readLine()) != null)
				l.agregaFinal(linea);
		} catch (IOException ioe) {
			System.out.printf("Error en lectura de entrada estandar.");
			System.exit(1);
		}

		return l;
	}

	public static Lista<String> deArchivos(Lista<String> l, String[] args, String hayO) {

		if (hayO == null) {

			for (int i = 0; i < args.length; i++)
				if (!args[i].equals("-r") && !args[i].equals("-o"))
					try {
						BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(args[i])));
						String linea;
						while ((linea = in.readLine()) != null)
							l.agregaFinal(linea);

					} catch (IOException ioe) {
						System.out.printf("Error en lectura de archivos. ");
						System.exit(1);
					}

			return l;

		}

		for (int j = 0; j < args.length; j++)
			if (!args[j].equals("-r") && !args[j].equals("-o") && !args[j].equals(hayO))
				try {
					BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(args[j])));
					String linea;
					while ((linea = in.readLine()) != null)
						l.agregaFinal(linea);

				} catch (IOException ioe) {
					System.out.printf("Error en lectura de archivos. ");
					System.exit(1);
				}
		return l;

	}
}
