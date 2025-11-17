package org.javabasics.services;

import java.util.List;
import java.util.Scanner;

import org.javabasics.models.Utente;
import org.javabasics.models.Corso;
import org.javabasics.models.Prenotazione;

public class OperazioniHandler {

    private List<Utente> utenti;
    private List<Corso> corsi;
    private List<Prenotazione> prenotazioni;
    private Scanner scanner;

    public OperazioniHandler(List<Utente> utenti, 
                             List<Corso> corsi,
                             List<Prenotazione> prenotazioni, 
                             Scanner scanner) {

        this.utenti = utenti;
        this.corsi = corsi;
        this.prenotazioni = prenotazioni;
        this.scanner = scanner;

    }

    public void gestisciComando(int comando) {
        
        separatore();

        switch (comando) {
            case 0 -> operazioneZero();
            case 1 -> operazioneUno();
            case 2 -> operazioneDue();
            case 3 -> operazioneTre();
            case 4 -> operazioneQuattro();
            case 5 -> operazioneCinque();
        }

        separatore();

    }

    private void separatore() {
        System.out.println("\n" + "#".repeat(150) + "\n");
    }

    private void operazioneZero() {
        System.out.println("Il programma è terminato. A presto!");
    }

    private void operazioneUno() {

        System.out.println("Hai selezionato il comando N.1: visualizzare tutti i corsi all'interno del sistema.");
        
        CorsoService.stampaCorsi(corsi);

    }

    private void operazioneDue() {

        System.out.println("Hai selezionato il comando N.2: prenotare un corso esistente.\n");
        
        System.out.println("Scegli il corso che vorresti prenotare.");

        System.out.println("\nEcco la lista dei corsi ancora disponibili:");
        List<Corso> corsiDisponibili = CorsoService.getCorsiDisponibili(corsi);
        if (corsiDisponibili.isEmpty()) {
            System.out.println("\nNon ci sono corsi disponibili al momento.\n");
            return;
        } else {
            CorsoService.stampaCorsi(corsiDisponibili);
        }

        int corsoId = -1;
        Corso corsoTrovato = new Corso();
        while(true) {
            try {
                System.out.print("Inserisci il codice del corso a cui vuoi iscriverti: ");
                corsoId = Integer.parseInt(this.scanner.nextLine().trim());
                corsoTrovato = CorsoService.trovaCorsoPerId(corsiDisponibili, corsoId);
                if (!corsoTrovato.isVuoto()) {
                    break;
                }
                System.out.println("\n!! Scelta non valida. Inserisci uno dei corsi presenti nel sistema.\n");
            } catch (NumberFormatException e) {
                System.out.println("\n!! Formato non valido del numero del corso.");
            }
        }

        System.out.print("\nOra scegli un utente. Ecco la lista dei nostri utenti:\n");
        UtenteService.stampaUtenti(utenti);
        System.out.print("\nInserisci l'ID dell'utente: ");
        int utenteId = Integer.parseInt(this.scanner.nextLine().trim());

        Utente utenteTrovato = UtenteService.cercaUtenteById(utenti, utenteId);
        if (utenteTrovato.isVuoto()) {
            return;
        }

        corsoTrovato.setDisponibile("NO");

        PrenotazioneService.aggiungiPrenotazione(prenotazioni, corsoTrovato, utenteTrovato);

    }

    private void operazioneTre() {

        System.out.println("Hai selezionato il comando N.3: disdire la prenotazione di un corso.\n");
        
        System.out.println("Per disdire la prenotazione:");

        System.out.print("Inserisci il nome dell'utente: ");
        String nome = scanner.nextLine().trim();

        System.out.print("Inserisci il cognome dell'utente: ");
        String cognome = scanner.nextLine().trim();

        Utente utenteTrovato = UtenteService.cercaUtenteByNomeCognome(utenti, nome, cognome);
        if (utenteTrovato.isVuoto()) {
            return;
        }

        System.out.print("\nLe prenotazioni di " + utenteTrovato.getNome() + " " + utenteTrovato.getCognome() + " sono: \n");
        List<Prenotazione> prenotazioniUtente = PrenotazioneService.getPrenotazioniUtente(prenotazioni, utenteTrovato);
        if (prenotazioniUtente.isEmpty()) {
            return;
        }

        for (int i = 0; i < prenotazioniUtente.size(); i++) {
            Prenotazione p = prenotazioniUtente.get(i);
            System.out.println((i + 1) + ") " + p.getCorso().getNome());
        }
        
        int scelta = -1;
        while(true) {
            System.out.print("\nInserisci il numero della prenotazione da disdire: ");
            String input = this.scanner.nextLine().trim();
            try {
                scelta = Integer.parseInt(input);
                if (scelta >= 1 && scelta <= prenotazioniUtente.size()) {
                    break;
                }
                System.out.println("\n!! Scelta non valida. Inserisci un numero compreso tra 1 e " + prenotazioniUtente.size() + ".");
            } catch (NumberFormatException e) {
                System.out.println("\n!! Formato non valido del numero della prenotazione.");
            }
        }

        Prenotazione daRimuovere = prenotazioniUtente.get(scelta - 1);

        daRimuovere.getCorso().setDisponibile("SI");

        PrenotazioneService.rimuoviPrenotazione(prenotazioni, daRimuovere);

    }

    private void operazioneQuattro() {

        System.out.println("Hai selezionato il comando N.4: aggiungere un nuovo utente.\n");
        
        System.out.println("Per registrare il nuovo utente:");
        
        System.out.print("Inserisci il nome: ");
        String nome = scanner.nextLine().trim();

        System.out.print("Inserisci il cognome: ");
        String cognome = scanner.nextLine().trim();

        String dataNascita;
        while(true) {
            System.out.print("Inserisci la data di nascita (gg/mm/aaaa): ");
            dataNascita = this.scanner.nextLine().trim();
            if (dataNascita.matches("\\d{2}/\\d{2}/\\d{4}")) {
                break;
            }
            System.out.println("\n!! Formato non valido. Usa gg/mm/aaaa (es: 15/11/1995)\n");
        }

        System.out.print("Inserisci l'indirizzo (es: Via Roma 12, Milano): ");
        String indirizzo = this.scanner.nextLine().trim();

        String documento;
        while(true) {
            System.out.print("Inserisci il documento (LL NNNNNN N): ");
            documento = this.scanner.nextLine().trim().toUpperCase();
            if (documento.matches("[A-Z]{2} \\d{6} [A-Z0-9]")) {
                break;   
            }
            System.out.println("\n!! Formato documento non valido. Esempio: AB 123456 C\n");
        }

        UtenteService.aggiungiUtente(utenti, nome, cognome, dataNascita, indirizzo, documento);
    
    }


    private void operazioneCinque() {

        System.out.println("Hai selezionato il comando N.5: esportare un file CSV con i corsi ancora disponibili.\n");
        
        System.out.println("Esportazione in corso... \n");
        
        List<Corso> disponibili = CorsoService.getCorsiDisponibili(corsi);
        
        CorsoService.writeCorsi(disponibili);
        
    }

}
