package mx.unam.ciencias.edd.sort;

import mx.unam.ciencias.edd.*;
import java.text.Collator;

public class Ordenador {

	private Ordenador() {
	};

	public static Lista<String> ordena(Lista<String> l) {

		Collator comparador = Collator.getInstance();

		comparador.setStrength(Collator.PRIMARY);

		return l.mergeSort(
				(a, b) -> comparador.compare(
						a.replaceAll("\\P{L}", ""),
						b.replaceAll("\\P{L}", ""))

		);
	}
}