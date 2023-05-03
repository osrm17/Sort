package mx.unam.ciencias.edd.sort;

public class Banderas {

	private Banderas() {
	}

	public static boolean hayR(String[] args) {
		for (int i = 0; i < args.length; i++)
			if (args[i].equals("-r"))
				return true;
		return false;

	}

	public static String hayO(String[] args) {
		for (int i = 0; i < args.length; i++)
			try {
				if (args[i].equals("-o") && !args[i + 1].equals("-r"))
					return args[i + 1];
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Despues de -o va un identificador.");
				System.exit(1);
			}
		return null;
	}

}
