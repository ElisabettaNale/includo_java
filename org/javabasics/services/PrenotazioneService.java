package org.javabasics.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.javabasics.models.Corso;
import org.javabasics.models.Prenotazione;
import org.javabasics.models.Utente;

public class PrenotazioneService {

    // Legge un file CSV e converte ogni riga in un oggetto Prenotazione
    public static List<Prenotazione> readPrenotazioni(
            String pathPrenotazioni,
            List<Corso> corsi,
            List<Utente> utenti
    ) {

        // Crea mappe per ricerca veloce per ID
        Map<Integer, Corso> corsoMap = corsi.stream()
                .collect(Collectors.toMap(Corso::getId, c -> c));
        Map<Integer, Utente> utenteMap = utenti.stream()
                .collect(Collectors.toMap(Utente::getId, u -> u));
        
        return CSVReader.readCSV(pathPrenotazioni, riga -> {
            try {
                int id = Integer.parseInt(riga.get(0).trim());
                int idCorso = Integer.parseInt(riga.get(1).trim());
                int idUtente = Integer.parseInt(riga.get(2).trim());
                String dataInizio = riga.get(3).trim();
                String dataFine = riga.get(4).trim();
                
                Corso corso = corsoMap.get(idCorso);
                Utente utente = utenteMap.get(idUtente);
                
                if (corso == null || utente == null) {
                    System.err.printf("Corso o utente non trovato per prenotazione ID %d%n", id);
                    return null;
                }
                
                return new Prenotazione(id, corso, utente, dataInizio, dataFine);

            } catch (Exception e) {
                System.err.println("Errore nel parsing: " + riga);
                return null;
            }
        });
    }
    
    // Restituisce tutte le prenotazioni associate a un determinato utente.
    //Se non ne esistono, ritorna un ArrayList vuoto.
    public static List<Prenotazione> getPrenotazioniUtente(List<Prenotazione> prenotazioni, Utente utente) {
        
        List<Prenotazione> prenotazioniUtente = new ArrayList<>();
        for (Prenotazione p: prenotazioni) {
            if (p.getUtente().equals(utente)) {
                prenotazioniUtente.add(p);
            }
        }
        if (prenotazioniUtente.isEmpty()) {
            System.out.println("\nNessuna prenotazione è stata trovata per questo utente nel nostro sistema.");
            return prenotazioniUtente;
        } else {
            return prenotazioniUtente;
        }

    }

    // Restituisce l'ID più alto tra le prenotazioni esistenti.
    private static int getIdUltimaPrenotazione(List<Prenotazione> prenotazioni) {
        
        int maxId = -1;

        for (Prenotazione p : prenotazioni) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }

        return maxId;
    }

    // Aggiunge una nuova prenotazione al sistema
    public static Prenotazione aggiungiPrenotazione(List<Prenotazione> prenotazioni, Corso corso, Utente utente) {

        String dataInizio = corso.getData();
        int durata = corso.getDurataOre();
        String dataFine = Corso.calcolaDataFineCorso(dataInizio, durata);
        int prenotazioneId = PrenotazioneService.getIdUltimaPrenotazione(prenotazioni);

        Prenotazione p = new Prenotazione(prenotazioneId, corso, utente, dataInizio, dataFine);
        prenotazioni.add(p);

        System.out.println("\nLa prenotazione al corso - " + corso.getNome() + " - è stata effettuata con successo!");
        
        return p;
    }

    // Rimuove una prenotazione dal sistema
    public static void rimuoviPrenotazione(List<Prenotazione> prenotazioni, Prenotazione daRimuovere) {

        prenotazioni.remove(daRimuovere);

        System.out.println("\nLa prenotazione al corso - " + daRimuovere.getCorso().getNome() + " - è stata rimossa con successo!");

    }

    // Stampa formattata della lista delle prenotazioni
    public static void stampaPrenotazioni(List<Prenotazione> prenotazioni) {
        System.out.println("ID - Corso, Utente (Data inizio -> Data fine):");
        System.out.println("=".repeat(100));
        System.out.println();
        
        for (Prenotazione p : prenotazioni) {
            System.out.println(p); 
            System.out.println();
        }
    }

}
