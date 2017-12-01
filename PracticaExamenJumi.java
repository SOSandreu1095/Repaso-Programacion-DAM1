/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaexamenjumi;

import java.util.Scanner;

/**
 *
 * @author Andreu
 */
public class PracticaExamenJumi {

    /**
     * @param args the command line arguments
     */
    private static boolean fin = false;

    public static void main(String[] args) {

        while (!fin) {
            mostrarOpciones();
            int op = leerOpcion();
            realizarOpcion(op);
        }

    }

    private static void mostrarOpciones() {
        System.out.println("\n\n\n-------- PRACTICA ---------\n"
                + "\t 1) Dar los n primeros numeros primos\n"
                + "\t 2) Busqueda dicotómica\n"
                + "\t 3) Multiplicación de Matrices\n"
                + "\t 4) Matriz traspuesta\n"
                + "\t 5) Ordenacion por seleccion\n"
                + "\t 6) Ordenación burbuja\n"
                + "\t 0) Salir");
        System.out.print("\t OPCION --> ");
    }

    private static int leerOpcion() {
        Scanner sc = new Scanner(System.in);
        try {
            int x = sc.nextInt();
            return x;
        } catch (Exception e) {
            System.err.println("\tERROR: Introduce un numero correcto\n");
            return -1;
        }
    }

    private static void realizarOpcion(int op) {
        Scanner sc = new Scanner(System.in);
        switch (op) {
            case 1: //Primos
                System.out.print("Introduce cuantos primos quieres buscar: ");
                int max = sc.nextInt();
                visualizarPrimos(max);
                break;
            case 2: //Dicotomica
                int a[] = {1, 4, 6, 10, 15, 17, 20};
                System.out.print("Que numero deseas buscar: ");
                int x = sc.nextInt();
                dicotomica(a, x);
                break;
            case 3: //Mult matrices
                int[][] m1 = {{1, 2}, {3, 4}, {5, 6}};
                int[][] m2 = {{1, 2, 3, 4}, {5, 6, 7, 8}};
                multiplicarMatrices(m1, m2);

                break;
            case 4: //Matriz traspuesta
                int[][] m = {{11, 22, 33}, {44, 55, 66}, {77, 88, 99}, {10, 20, 30}};
                matrizTraspuesta(m);
                break;
            case 5: //Ordenacion seleccion
                int b[] = {8, 5, 6, 9, 3, 2, 54, 1, 2, 17, 8, 93, 3, 5, 65};
                imprimirArray(b);
                ordenacionSeleccion(b);
                imprimirArray(b);
                break;
            case 6: //Ordenacion burbuja
                int c[] = {8, 5, 6, 9, 3, 2, 54, 1, 2, 17, 8, 93, 3, 5, 65};
                imprimirArray(c);
                ordenacionBurbuja(c);
                imprimirArray(c);
            case 0: //Salir
                fin = true;
                break;
            default:
                System.out.println("\tOPCION NO VALIDA\n");
        }
    }

    /**
     * Visauliza un numero determinado de primos (max) Numero primo: Solo
     * divisible entre el 1 y el mismo
     *
     * @param max
     */
    private static void visualizarPrimos(int max) {
        int cnt = 0;

        for (int num = 1; cnt < max; num++) {
            int div = 0;
            for (int i = 1; i <= num; i++) {
                if (num % i == 0) {
                    div++;
                }
            }
            if (div <= 2) {
                System.out.println(num + " es primo");
                cnt++;
            }
            num++;
        }
    }

    /**
     * Realiza una busqueda dicotomica dentro de un array, sobre un elemento x
     * El array debe estar ordenao para que funcione la busqueda
     *
     * La estrategia es ir dividiendo el array por la mitad e ir cogiendo la
     * parte en la que se deberia encontrar nuestro elemento si es mayor o
     * menor, hasta encontrarlo si existe
     *
     * @param a
     * @param x
     */
    private static void dicotomica(int[] a, int x) {
        int pmin = 0;
        int pmax = a.length - 1;
        int pmed = (pmin + pmax) / 2;

        while ((a[pmed] != x) && (pmin <= pmax)) {
            if (a[pmed] < x) {
                pmin = pmed + 1;
            } else {
                pmax = pmed - 1;
            }
            pmed = (pmin + pmax) / 2;
        }
        if (a[pmed] == x) {
            System.out.println("Encontrado");
        } else {
            System.out.println("NO EXISTE");
        }
    }

    /**
     * Realiza la trasposición de una determinada matriz
     *
     * @param m
     */
    private static void matrizTraspuesta(int[][] m) {
        //Imprimo la matriz inicial
        imprimirMatriz(m);

        //Trasponer matriz
        int[][] n = new int[m[0].length][m.length];

        for (int f = 0; f < n.length; f++) {
            for (int c = 0; c < n[0].length; c++) {
                n[f][c] = m[c][f];
            }
        }

        imprimirMatriz(n);
    }

    /**
     * Realiza la multiplicacion (si se puede) de 2 matrices pasadas por
     * parametros La multiplicacion se puede llevar a cabo si: - El numerdo de
     * columnas de la matriz 1 = Numero filas matriz 2 La matriz resultante
     * tendra una dimension de: - Numero de filas m1 , Numero de columnas m2
     *
     * @param m1
     * @param m2
     */
    private static void multiplicarMatrices(int[][] m1, int[][] m2) {
        System.out.println("Matriz 1");
        imprimirMatriz(m1);
        System.out.println("\nMatriz 2");
        imprimirMatriz(m2);
        if (m1[0].length == m2.length) {
            //Entonces si se pueden multiplicar
            int[][] mR = new int[m1.length][m2[0].length];

            //Recorremos la matriz resultado
            for (int f = 0; f < mR.length; f++) {
                for (int c = 0; c < mR[f].length; c++) {
                    //Para cada posicion calculamos el resultado
                    for (int x = 0; x < m2.length; x++) {
                        mR[f][c] += m1[f][x] * m2[x][c];
                    }
                }
            }
            imprimirMatriz(mR);
        } else {
            System.out.println("Las matrices no pueden ser multiplicadas");
        }
    }

    /**
     * Recorre el vector o la lista, buscando el elemento mas pequeño y
     * colocandolo en la posición 0 del vector, y así sucesivamente n-1 veces,
     *
     * @param a
     */
    private static void ordenacionSeleccion(int[] a) {
        int men;
        int posMen;
        for (int i = 0; i < a.length - 1; i++) {
            //Buscamos el elemento mas pequeño
            men = a[i];
            posMen = i;
            for (int x = i + 1; x < a.length; x++) {
                if (a[x] < men) {
                    men = a[x];
                    posMen = x;
                }
            }
            //Hacemos el cambio
            a[posMen] = a[i];
            a[i] = men;
        }
    }

    /**
     * Funciona revisando cada elemento de la lista que va a ser ordenada con el
     * siguiente, intercambiándolos de posición si están en el orden equivocado.
     * Es necesario revisar varias veces toda la lista hasta que no se necesiten
     * más intercambios, lo cual significa que la lista está ordenada
     *
     * GIF Como funciona:
     * https://upload.wikimedia.org/wikipedia/commons/c/c8/Bubble-sort-example-300px.gif
     *
     * @param a
     */
    private static void ordenacionBurbuja(int[] a) {
        int aux;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j + 1] < a[j]) {
                    //Intercambiarlos
                    aux = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = aux;
                }
            }
        }
    }

    /**
     * Imprime un array bidimensional por consola (Matriz)
     *
     * @param m
     */
    private static void imprimirMatriz(int[][] m) {
        System.out.println("\n");
        for (int f = 0; f < m.length; f++) {
            System.out.println();
            for (int c = 0; c < m[f].length; c++) {
                System.out.print(m[f][c] + " ");
            }
        }
    }

    /**
     * Imprime un array unidimensional por consola
     *
     * @param a
     */
    private static void imprimirArray(int a[]) {
        System.out.println("\n");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
