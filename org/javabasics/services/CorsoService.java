package org.javabasics.services;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.javabasics.models.Corso;

public class CorsoService {

    // Legge un file CSV e converte ogni riga in un oggetto Corso
    public static List<Corso> readCorsi(String path) {
    
        return CSVReader.readCSV(path, riga -> {
            try {
                return new Corso(
                        Integer.parseInt(riga.get(0).trim()),
                        riga.get(1).trim(),
                        riga.get(2).trim(),
                        riga.get(3).trim(),
                        Integer.parseInt(riga.get(4).trim()),
                        riga.get(5).trim(),
                        riga.get(6).trim()
                );
            } catch (Exception e) {
                System.err.println("Errore nel parsing: " + riga);
                return null;
            }
        });

    }

    // Restituisce solo i corsi contrassegnati come disponibili.
    public static List<Corso> getCorsiDisponibili(List<Corso> corsi) {
        return corsi.stream()
                .filter(c -> c.getDisponibile().equalsIgnoreCase("SI"))
                .collect(Collectors.toList());
    }

    // Cerca un corso per ID nella lista dei corsi.
    // Se non lo trova, restituisce un nuovo Corso vuoto.
    public static Corso trovaCorsoPerId(List<Corso> corsi, int corsoId) {
        Corso corsoTrovato = new Corso();
        for (Corso c: corsi) {
            if (c.getId() == corsoId) {
                corsoTrovato = c;
                break;
            }
        }
        return corsoTrovato;
    }

    // Stampa la lista dei corsi in formato leggibile.
    public static void stampaCorsi(List<Corso> corsi) {
        System.out.println("\nID - Nome (Luogo, Data, Durata, Disponibilità): descrizione");
        System.out.println("=".repeat(100));
        System.out.println();
        for (Corso c: corsi) {
            System.out.println(c); 
            System.out.println();
        }
    }

    // Salva una lista di corsi in un nuovo CSV.
    // Il file generato contiene solo alcuni campi e viene salvato con nome:
    // prenotazioni_dd_MM_yyyy.csv all’interno della directory di lavoro.
    public static void writeCorsi(List<Corso> corsi) {

        // Crea nome file di output
        LocalDate oggi = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy");
        String dataFormattata = oggi.format(formatter);
        String path = "prenotazioni_" + dataFormattata + ".csv";

        try (FileWriter fw = new FileWriter(path)) {
            for (Corso c : corsi) {
                fw.write(c.getId() + ";" +
                         c.getNome() + ";" +
                         c.getData() + ";" +
                         c.getDurataOre() + "\n");
            }
            System.out.println("Il file CSV " + path + " è stato creato con successo!\nLo troverai nella lista dei tuoi file.");
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio dei corsi: " + e.getMessage());
            
        }

    }

}

