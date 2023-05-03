package mx.unam.ciencias.edd.sort;

import mx.unam.ciencias.edd.*;

public class TipoEntrada {

	private TipoEntrada() {
	}

	public static boolean esPorArchivos(String[] args, String hayO) {
		if (hayO == null) {

			for (int i = 0; i < args.length; i++)
				if (!args[i].equals("-r") && !args[i].equals("-o"))
					return true;
			return false;
		}

		for (int j = 0; j < args.length; j++)
			if (!args[j].equals("-r") && !args[j].equals("-o") && !args[j].equals(hayO))
				return true;
		return false;
	}

}
