# Sort

## Descripcion

Ordenador lexicografico por lineas (parecido al programa `sort` en Unix) que funciona con uno
o mas archivos de texto o bien de la entrada estandar. 

El programa debe poder recibir varios archivos, ya sea en la línea de comandos o
por la entrada estándar, en cuyo caso los trata a todos como un **único** archivo
grande.

Si el usuario pasa la bandera -r, el programa debe imprimir las líneas en
orden inverso.

Si el usuario pasa la bandera -o seguida de un identificador, el programa
debe guardar su salida en un archivo llamado como el identificador. La
operación es destructiva, por lo que el archivo sera sobreescrito en caso de existir.

## Requerimientos

- Java 11
- Maven 

## Instalacion

```
mvn install
```

## Uso

Con un archivo(s) existente(s) 
```
$ java -jar target/sort.jar archivo1.txt archivo2.txt 
```

Desde la entrada estandar
```
$ cat archivo1.txt | java -jar target/sort.jar
```

Usando la bandera -r (reversa)
```
$ java -jar target/sort.jar archivo1.txt archivo2.txt -r 
```

Usando la bandera -o <archivo-salida> 
```
$ java -jar target/sort.jar archivo1.txt archivo2.txt -o salida.txt
```

Combinacion de las anteriores, sin importar el orden
```
$ java -jar target/sort.jar archivo1.txt archivo2.txt -o salida.txt -r
```
