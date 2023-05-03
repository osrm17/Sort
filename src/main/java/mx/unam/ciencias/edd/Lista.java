package mx.unam.ciencias.edd;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase genérica para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas no aceptan a <code>null</code> como elemento.</p>
 *
 * @param <T> El tipo de los elementos de la lista.
 */
public class Lista<T> implements Coleccion<T> {

    /* Clase interna privada para nodos. */
    private class Nodo {
        /* El elemento del nodo. */
        public T elemento;
        /* El nodo anterior. */
        public Nodo anterior;
        /* El nodo siguiente. */
        public Nodo siguiente;

        /* Construye un nodo con un elemento. */
        public Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    /* Clase interna privada para iteradores. */
    private class Iterador implements IteradorLista<T> {
        /* El nodo anterior. */
        public Nodo anterior;
        /* El nodo siguiente. */
        public Nodo siguiente;

        /* Construye un nuevo iterador. */
        public Iterador() {
	    anterior = null;
	    siguiente = cabeza;
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            return siguiente != null;
        }

        /* Nos da el elemento siguiente. */
        @Override public T next() {
	    if (siguiente == null)
		throw new NoSuchElementException();
	    T elemento = siguiente.elemento;
	    anterior = siguiente;
	    siguiente = siguiente.siguiente;
	    return elemento;
        }

        /* Nos dice si hay un elemento anterior. */
        @Override public boolean hasPrevious() {
	    return anterior != null;
        }

        /* Nos da el elemento anterior. */
        @Override public T previous() {
            if (anterior == null)
		throw new NoSuchElementException();
	    T elemento = anterior.elemento;
	    siguiente = anterior;
	    anterior = anterior.anterior;
	    return elemento;
        }

        /* Mueve el iterador al inicio de la lista. */
        @Override public void start() {
            anterior = null;
	    siguiente = cabeza;
        }

        /* Mueve el iterador al final de la lista. */
        @Override public void end() {
            siguiente = null;
	    anterior = rabo;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista. El método es idéntico a {@link
     * #getElementos}.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
	return longitud;
    }

    /**
     * Regresa el número elementos en la lista. El método es idéntico a {@link
     * #getLongitud}.
     * @return el número elementos en la lista.
     */
    @Override public int getElementos() {
        return longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    @Override public boolean esVacia() {
        return cabeza == null;
    }

    /**
     * Agrega un elemento a la lista. Si la lista no tiene elementos, el
     * elemento a agregar será el primero y último. El método es idéntico a
     * {@link #agregaFinal}.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    @Override public void agrega(T elemento) {
	if (elemento == null)
	    throw new IllegalArgumentException();
	Nodo nuevo = new Nodo(elemento);
	if (cabeza == null)
	    cabeza = rabo = nuevo;
	else {
	    nuevo.anterior = rabo;
	    rabo.siguiente = nuevo;
	    rabo = nuevo;
	}
	longitud++;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
	if (elemento == null)
	    throw new IllegalArgumentException();
	Nodo nuevo = new Nodo(elemento);
	if (cabeza == null)
	    cabeza = rabo = nuevo;
	else {
	    nuevo.anterior = rabo;
	    rabo.siguiente = nuevo;
	    rabo = nuevo;
	}
	longitud++;
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        if (elemento == null)
	    throw new IllegalArgumentException();
	Nodo nuevo = new Nodo(elemento);
	if (cabeza == null)
	    cabeza = rabo = nuevo;
	else {
	    nuevo.siguiente = cabeza;
	    cabeza.anterior = nuevo;
	    cabeza = nuevo;
	}
	longitud++;
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void inserta(int i, T elemento) {
	if (i <= 0)
	    agregaInicio(elemento);
	else if (i >= longitud)
	    agregaFinal(elemento);
	else {
	    if (elemento == null)
		throw new IllegalArgumentException();
	    Nodo iEsimo = getIEsimoNodo(i);
	    Nodo nuevo = new Nodo(elemento);
	    nuevo.siguiente = iEsimo;
	    nuevo.anterior = iEsimo.anterior;
	    iEsimo.anterior.siguiente = nuevo;
	    iEsimo.anterior = nuevo;
	    longitud++;
	}
    }

    private Nodo getIEsimoNodo(int i){
	Nodo aux = cabeza;
	while (i > 0) {
	    aux = aux.siguiente;
	    i--;
	}
	return aux;
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
	Nodo aux = buscaElemento(elemento);
	if (aux != null) 
	    eliminaNodo(aux);
    }

    //Se supone que elemento != null 
    private Nodo buscaElemento(T elemento) {
	Nodo aux = cabeza;
	while (aux != null) {
	    if (aux.elemento.equals(elemento))
		return aux;
	    aux = aux.siguiente;
	}
	return aux;
    }

    private void eliminaNodo(Nodo n) {
	if (longitud == 1) {
	    cabeza = rabo = null;
	    longitud = 0;
	} else {
	    if (n == cabeza) {
		cabeza = cabeza.siguiente;
		cabeza.anterior = null;
	    } else if (n == rabo) {
		rabo = rabo.anterior;
		rabo.siguiente = null;
	    } else {
		n.siguiente.anterior = n.anterior;
		n.anterior.siguiente = n.siguiente;
	    }
	    longitud--;
	}
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
	if (cabeza == null)
	    throw new NoSuchElementException();
	T elemento = cabeza.elemento;
	eliminaNodo(cabeza);
	return elemento;
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {
	if (cabeza == null)
	    throw new  NoSuchElementException();
	T elemento = rabo.elemento;
	eliminaNodo(rabo);
	return elemento;
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
	return buscaElemento(elemento) != null;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
        Lista<T> lista = new Lista<T>();
	Nodo aux = cabeza;
	while (aux != null) {
	    lista.agregaInicio(aux.elemento);
	    aux = aux.siguiente;
	}
	return lista;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
	Lista<T> lista = new Lista<T>();
	Nodo aux = cabeza;
	while (aux != null) {
	    lista.agregaFinal(aux.elemento);
	    aux = aux.siguiente;
	}
	return lista;
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    @Override public void limpia() {
        cabeza = rabo = null;
	longitud = 0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {
	if (cabeza == null)
	    throw new NoSuchElementException();
	return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() {
        if (cabeza == null)
	    throw new NoSuchElementException();
	return rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public T get(int i) {
	if (i < 0 || i >= longitud)
	    throw new ExcepcionIndiceInvalido();
	return getIEsimoNodo(i).elemento;
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
	int i = 0;
	Nodo aux = cabeza;
	while (aux != null) {
	    if (aux.elemento.equals(elemento))
		return i;
	    i++;
	    aux = aux.siguiente;
	}
	return -1;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
	if (cabeza == null)
	    return "[]";
	String s = "[";
	Nodo n = cabeza;
	while (n.siguiente != null) {
	    s += String.format("%s, ", n.elemento.toString());
	    n = n.siguiente;
	}
	s += String.format("%s]", n.elemento.toString());
        return s;
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la lista es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)objeto;
	if (longitud != lista.longitud)
	    return false;
	if (lista.cabeza == null)
	    return cabeza == null;
	Nodo auxA = cabeza;
	Nodo auxB = lista.cabeza;
	while (auxA != null) {
	    if (!auxA.elemento.equals(auxB.elemento))
		return false;
	    auxA = auxA.siguiente;
	    auxB = auxB.siguiente;
	}
	return true;
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }

    /**
     * Regresa una copia de la lista, pero ordenada. Para poder hacer el
     * ordenamiento, el método necesita una instancia de {@link Comparator} para
     * poder comparar los elementos de la lista.
     * @param comparador el comparador que la lista usará para hacer el
     *                   ordenamiento.
     * @return una copia de la lista, pero ordenada.
     */
    public Lista<T> mergeSort(Comparator<T> comparador) {
	if(longitud <= 1)
	    return copia();
	Lista<T> listaA = new Lista<T>();
	Lista<T> listaB = new Lista<T>();
	Nodo aux = cabeza;
	for(int i = 0; i < longitud/2; i++) {
	    listaA.agregaFinal(aux.elemento);
	    aux = aux.siguiente;
	}
	while(aux != null) {
	    listaB.agregaFinal(aux.elemento);
	    aux = aux.siguiente;
	}

	listaA = listaA.mergeSort(comparador);
	listaB = listaB.mergeSort(comparador);
	return mezcla(listaA, listaB, comparador);
    }

    private Lista<T> mezcla(Lista<T> listaA, Lista<T> listaB, Comparator<T> comparador) {
	Lista<T> mezcla = new Lista<T>();
	Nodo auxA = listaA.cabeza;
	Nodo auxB = listaB.cabeza;
	while (auxA != null && auxB != null) {
	    if (comparador.compare(auxA.elemento, auxB.elemento) <= 0) {
		mezcla.agregaFinal(auxA.elemento);
		auxA = auxA.siguiente;
	    } else {
		mezcla.agregaFinal(auxB.elemento);
		auxB = auxB.siguiente;
	    }
	}
	if (auxA == null) 
	    mezcla.rellena(auxB);
	else
	    mezcla.rellena(auxA);
	
	return mezcla;
    }

    private void rellena(Nodo n) {
	while (n != null) {
	    agregaFinal(n.elemento);
	    n = n.siguiente;
	}
    }

    /**
     * Regresa una copia de la lista recibida, pero ordenada. La lista recibida
     * tiene que contener nada más elementos que implementan la interfaz {@link
     * Comparable}.
     * @param <T> tipo del que puede ser la lista.
     * @param lista la lista que se ordenará.
     * @return una copia de la lista recibida, pero ordenada.
     */
    public static <T extends Comparable<T>>
    Lista<T> mergeSort(Lista<T> lista) {
        return lista.mergeSort((a, b) -> a.compareTo(b));
    }

    /**
     * Busca un elemento en la lista ordenada, usando el comparador recibido. El
     * método supone que la lista está ordenada usando el mismo comparador.
     * @param elemento el elemento a buscar.
     * @param comparador el comparador con el que la lista está ordenada.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean busquedaLineal(T elemento, Comparator<T> comparador) {
	Nodo n = cabeza;
	while (n != null) {
	    int i = comparador.compare(elemento, n.elemento);
	    if (i < 0)
		return false;
	    if (n.elemento.equals(elemento))
		return true;
	    n = n.siguiente;
	}
	return false;
    }

    /**
     * Busca un elemento en una lista ordenada. La lista recibida tiene que
     * contener nada más elementos que implementan la interfaz {@link
     * Comparable}, y se da por hecho que está ordenada.
     * @param <T> tipo del que puede ser la lista.
     * @param lista la lista donde se buscará.
     * @param elemento el elemento a buscar.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public static <T extends Comparable<T>>
    boolean busquedaLineal(Lista<T> lista, T elemento) {
        return lista.busquedaLineal(elemento, (a, b) -> a.compareTo(b));
    }
}
