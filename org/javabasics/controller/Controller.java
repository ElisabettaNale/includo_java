package org.javabasics.controller;

import java.util.Scanner;
import java.util.List;

import org.javabasics.models.Corso;
import org.javabasics.models.Prenotazione;
import org.javabasics.models.Utente;
import org.javabasics.services.CorsoService;
import org.javabasics.services.UtenteService;
import org.javabasics.services.PrenotazioneService;
import org.javabasics.services.App;
import org.javabasics.services.Comandi;
import org.javabasics.services.OperazioniHandler;

public class Controller {
    public static void controller(String[] args) {
        
        App.salutoBenvenuto();
        Scanner scanner = App.avviaInputStream();

        List<Utente> utenti = UtenteService.readUtenti("org/javabasics/resources/utenti.csv");
        // UtenteService.stampaUtenti(utenti);
        
        List<Corso> corsi = CorsoService.readCorsi("org/javabasics/resources/corsi.csv");
        // CorsoService.stampaCorsi(corsi);

        List<Prenotazione> prenotazioni = PrenotazioneService.readPrenotazioni("org/javabasics/resources/prenotazioni.csv", corsi, utenti);
        // PrenotazioneService.stampaPrenotazioni(prenotazioni);

        int comando = -1;
        OperazioniHandler operazioniHandler = new OperazioniHandler(utenti, corsi, prenotazioni, scanner);

        do {
            comando = Comandi.leggiComando(scanner);
            operazioniHandler.gestisciComando(comando);
        } while (comando != 0);
        
        App.chiudiInputStream(scanner);
        
    }

}