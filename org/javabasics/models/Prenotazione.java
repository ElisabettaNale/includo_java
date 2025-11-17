package org.javabasics.models;

public class Prenotazione {

    private int id;
    private Corso corso;
    private Utente utente;
    private String dataInizio;
    private String dataFine;

    // Costruttori
    public Prenotazione(int id, Corso corso, Utente utente, String dataInizio, String dataFine) {
        this.id = id;
        this.corso = corso;
        this.utente = utente;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public Prenotazione() {
        this.id = -1;
        this.corso = new Corso();
        this.utente = new Utente();
        this.dataInizio = "";
        this.dataFine = "";
    }

    // Getter
    public int getId() { return id; }
    public Corso getCorso() { return corso; }
    public Utente getUtente() { return utente; }
    public String getDataInizio() { return dataInizio; }
    public String getDataFine() { return dataFine; }

    // Setter
    public void setCorso(Corso corso) { this.corso = corso; }
    public void setUtente(Utente utente) { this.utente = utente; }
    public void setDataInizio(String dataInizio) { this.dataInizio = dataInizio; }
    public void setDataFine(String dataFine) { this.dataFine = dataFine; }

    @Override
    public String toString() {
        return this.id + " - " 
        + this.corso.getNome() + ", " 
        + this.utente.getNome() + " " + this.utente.getCognome() + " (" 
        + this.dataInizio + " -> " 
        + this.dataFine + ")";
    }

    // Restituisce true se l'oggetto rappresenta una prenotazione "vuota",
    // cioè non contiene informazioni significative.
    public boolean isVuoto() {

        return this.id == -1 && 
               this.corso.isVuoto() && 
               this.utente.isVuoto() && 
               this.dataInizio.isEmpty() && 
               this.dataFine.isEmpty();
               
    }

}
