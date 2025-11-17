package org.javabasics.services;
import java.util.Scanner;

public class Comandi {

    public static void mostraComandi() {

        System.out.println("In questo programma puoi:");
        System.out.println("1 - Visualizzare tutti i corsi all'interno del sistema;");
        System.out.println("2 - Prenotare un corso esistente;");
        System.out.println("3 - Disdire la prenotazione di un corso;");
        System.out.println("4 - Aggiungere un nuovo utente;");
        System.out.println("5 - Esportare un file CSV con i corsi ancora disponibili;");
        System.out.println("0 - Uscire dal programma.\n");

    }

    public static int leggiComando(Scanner scanner) {
        
        int comando = -1;
        boolean comandoValido = false;
        
        while (!comandoValido) {
            
            Comandi.mostraComandi();
            System.out.println("\nInserisci il numero corrispondente all'azione che desideri eseguire: "); 

            try {
                comando = Integer.parseInt(scanner.nextLine().trim()); 
                if (comando >= 0 && comando <= 5) {
                    comandoValido = true; 
                } else {
                    System.out.println("""
                    \n!! Il comando inserito non è valido.
                    !! Inserisci un numero tra 0 e 5 per proseguire.
                    """);
                }
            } catch (NumberFormatException e) {
                System.out.println("""
                \n!! Il formato inserito non è valido.
                !! Inserisci un numero tra 0 e 5 per proseguire.
                """);  
            }
        
        }

        return comando;
        
    }

}

