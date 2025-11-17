package org.javabasics.services;
import java.util.Scanner;

public class App {

    public static Scanner avviaInputStream() {
        return new Scanner(System.in);
    }

    public static void chiudiInputStream(Scanner scanner) {
        scanner.close();
    } 

    public static void salutoBenvenuto() {

        String saluto = """
        ####################################################################
        ###################### BENVENUTO IN IncluDO ########################
        ####################################################################

        Un programma per preservare e trasmettere le competenze
        dei mestieri tradizionali, rendendole accessibili anche a migranti
        e a persone in percorsi di riabilitazione sociale.

        ####################################################################
        ####################################################################
        ####################################################################
        """;
        System.out.println(saluto);
        
    }

}

