package platzi_play.Util;
import platzi_play.contenido.Genero;
import platzi_play.contenido.Lenguage;

import java.util.Scanner;



public class ScannerUtils {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static String capturarTexto(String mensaje) {
        System.out.println(mensaje + ":");
        return SCANNER.nextLine();
    }


    public static int capturarNumero(String mensaje) {

        System.out.println(mensaje + ":");
        while (!SCANNER.hasNextInt()) {
            System.out.println("Dato invalido " + mensaje + ":");
            SCANNER.next();
        }
        int dato = SCANNER.nextInt();
        SCANNER.nextLine();
        return dato;
    }

    public static double capturarDecimal(String mensaje) {

        System.out.println(mensaje + ":");
        while (!SCANNER.hasNextDouble()) {
            System.out.println("Dato invalido " + mensaje + ":");
            SCANNER.next();
        }

        double dato = SCANNER.nextDouble();
        SCANNER.nextLine();
        return dato;
    }

    public static Genero capturarGenero(String mensaje) {
        while (true) {
            System.out.println(mensaje + " ...opciones");
            for (Genero genero : Genero.values()) {
                System.out.println("-" + genero.name());
            }
            System.out.println("Cual genero deseas?");
            String entrada = SCANNER.nextLine();
            try {
                return Genero.valueOf(entrada.toUpperCase());
            } catch (Exception e) {
                System.out.println("Genero no aceptado ");
            }
        }
    }


    public static Lenguage capturaIdioma(String mensaje) {
        while (true) {
            System.out.println(mensaje + " ...opciones");
            for (Lenguage lenguage : Lenguage.values()) {
                System.out.println("-" + lenguage.name());
            }
            System.out.println("Cual idioma deseas?");
            String entrada = SCANNER.nextLine();
            try {
                return Lenguage.valueOf(entrada.toUpperCase());
            } catch (Exception e) {
                System.out.println("Genero no aceptado ");
            }
        }
    }
}

