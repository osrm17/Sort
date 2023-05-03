package mx.unam.ciencias.edd.sort;

import mx.unam.ciencias.edd.*;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Escritura {
	private Escritura() {
	}

	public static void guarda(String nombreArchivo, Lista<String> l) {
		try {
			FileOutputStream fileOut = new FileOutputStream(nombreArchivo);
			OutputStreamWriter osOut = new OutputStreamWriter(fileOut);
			BufferedWriter out = new BufferedWriter(osOut);
			for (String s : l)
				out.write(s + "\n");
			out.close();
		} catch (IOException ioe) {
			System.out.printf("No se pudo guardar.");
			System.exit(1);
		}

	}
}
