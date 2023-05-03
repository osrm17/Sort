package mx.unam.ciencias.edd.sort;

import mx.unam.ciencias.edd.*;

public class Proyecto1 {

    public static void main(String[] args) {

	boolean reversa = Banderas.hayR(args);
	String output = Banderas.hayO(args);

	boolean porArchivos = TipoEntrada.esPorArchivos(args, output);

	Lista<String> l = new Lista<String>();
						  

	if (porArchivos) 
	    l = Lectura.deArchivos(l, args, output);
	else
	    l = Lectura.deEstandar(l);

	l = Ordenador.ordena(l);
	
	if (reversa)
	    l = l.reversa();
	if (output != null) {
	    Escritura.guarda(output,l);
	} else 
	    for (String s : l)
		System.out.println(s);
	
	
    }
}
