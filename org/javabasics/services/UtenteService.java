package org.javabasics.services;
import java.util.List;

import org.javabasics.models.Utente;

public class UtenteService {

    // Legge un file CSV e converte ogni riga in un oggetto Utente
    public static List<Utente> readUtenti(String path) {
    
        return CSVReader.readCSV(path, riga -> {
            try {
                return new Utente(
                    Integer.parseInt(riga.get(0).trim()),
                    riga.get(1).trim(),
                    riga.get(2).trim(),
                    riga.get(3).trim(),
                    riga.get(4).trim(),
                    riga.get(5).trim()
                );
            } catch (Exception e) {
                System.err.println("Errore nel parsing: " + riga);
                return null;
            }
        });

    }
    
    // Aggiunge un nuovo utente alla lista generando un ID incrementale.
    // Restituisce l'utente appena creato.
    public static Utente aggiungiUtente(List<Utente> utenti, String nome, String cognome,
                             String dataNascita, String indirizzo, String documento) {

        int nuovoId = utenti.stream()
            .mapToInt(Utente::getId)
            .max().orElse(0) + 1;

        Utente nuovoUtente = new Utente(nuovoId, nome, cognome, dataNascita, indirizzo, documento);
        utenti.add(nuovoUtente);

        System.out.println("\nL'utente " + nuovoUtente.getNome() + " " + nuovoUtente.getCognome() + " è stato registrato con successo!");

        return nuovoUtente;
    }

    // Stampa formattata della lista utenti.
    public static void stampaUtenti(List<Utente> utenti) {
        System.out.println("\nID - Nome Cognome (Data di nascita, Indirizzo, Documento):");
        System.out.println("=".repeat(100));
        System.out.println();
        for (Utente utente : utenti) {
            System.out.println(utente); 
            System.out.println();
        }
    }

    // Cerca un utente per ID all'interno di una lista.
    // Restituisce l'utente trovato oppure un Utente vuoto se non esiste.
    public static Utente cercaUtenteById(List<Utente> utenti, Integer utenteId) {
        for (Utente u : utenti) {
            if (u.getId() == utenteId) {
                return u;
            }
        }
        System.out.println("\n!! L'utente con ID " + utenteId + " non è stato trovato nel nostro sistema.\n");
        // System.out.println("Gli utenti presenti sono: \n");
        // stampaUtenti(utenti);
        System.out.println("Riprova con un altro utente.");
        return new Utente();
    }

    // Cerca un utente per nome e cognome all'interno di una lista.
    // Restituisce l'utente trovato oppure un Utente vuoto se non esiste.
    public static Utente cercaUtenteByNomeCognome(List<Utente> utenti, String nome, String cognome) {
        for (Utente u : utenti) {
            if (u.getNome().equalsIgnoreCase(nome) && u.getCognome().equalsIgnoreCase(cognome)) {
                return u;
            }
        }
        System.out.println("\n!! L'utente " + nome + " " + cognome + " non è stato trovato nel nostro sistema.\n");
        System.out.println("Gli utenti presenti sono: \n");
        stampaUtenti(utenti);
        System.out.println("Riprova con un altro utente.");
        return new Utente();
    }

}

