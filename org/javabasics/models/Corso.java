package org.javabasics.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Corso {

    private int id;
    private String nome;
    private String descrizione;
    private String data;       
    private int durataOre;
    private String luogo;
    private String disponibile;

    // Costruttori
    public Corso(
        int id, String nome, String descrizione, String data, 
        int durataOre, String luogo, String disponibile
    ) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.data = data;
        this.durataOre = durataOre;
        this.luogo = luogo;
        this.disponibile = disponibile;
    }

    public Corso() {

        this.id = -1;
        this.nome = "";
        this.descrizione = "";
        this.data = "";
        this.durataOre = -1;
        this.luogo = "";
        this.disponibile = "";

    }

    // Getter 
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getDescrizione() { return descrizione; }
    public String getData() { return data; }
    public int getDurataOre() { return durataOre; }
    public String getLuogo() { return luogo; }
    public String getDisponibile() { return disponibile; }

    // Setter
    public void setNome(String nome) { this.nome = nome; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
    public void setData(String data) { this.data = data; }
    public void setDurataOre(int durataOre) { this.durataOre = durataOre; }
    public void setLuogo(String luogo) { this.luogo = luogo; }
    public void setDisponibile(String disponibile) { this.disponibile = disponibile; }
    
    @Override
    public String toString() {
        return this.id + " - " 
        + this.nome.toUpperCase() + " (" 
        + this.luogo + ", " 
        + this.data + ", " 
        + this.durataOre + " ore, " 
        + this.disponibile + "):\n" 
        + this.descrizione;
    }

    // Restituisce true se l'oggetto rappresenta un corso "vuoto",
    // cioè non contiene informazioni significative.
    public boolean isVuoto() {

        return this.id == -1 && 
               this.nome.isEmpty() && 
               this.descrizione.isEmpty() && 
               this.data.isEmpty() && 
               this.durataOre == -1 && 
               this.luogo.isEmpty() &&
               this.disponibile.isEmpty();

    }

    // Calcola la data di fine di un corso partendo dalla data di inizio
    // (formato dd/MM/yyyy) e dalla durata espressa in ore.
    // La data di fine non tiene conto di orari specifici: 
    // parte dalle 00:00 del giorno indicato.
    public static String calcolaDataFineCorso(String dataInizioStr, int durataOre) { // dataInizioStr -> dd/MM/yyyy

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInizio = LocalDate.parse(dataInizioStr, formatter);

        LocalDateTime dataInizioConOra = dataInizio.atStartOfDay();
        LocalDateTime dataFineConOra = dataInizioConOra.plusHours(durataOre);
        LocalDate dataFine = dataFineConOra.toLocalDate();
        
        return dataFine.format(formatter);

    }

}
