package org.javabasics.models;

public class Utente {
    
    private int id;
    private String nome;
    private String cognome;
    private String dataNascita;
    private String indirizzo;
    private String documentoId;

    // Costruttori
    public Utente(int id, String nome, String cognome, String dataNascita, String indirizzo, String documentoId) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.indirizzo = indirizzo;
        this.documentoId = documentoId;
    }

    public Utente() {
        this.id = -1;
        this.nome = "";
        this.cognome = "";
        this.dataNascita = "";
        this.indirizzo = "";
        this.documentoId = "";
    }

    // Getter
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
    public String getDataNascita() { return dataNascita; }
    public String getIndirizzo() { return indirizzo; }
    public String getDocumentoId() { return documentoId; }

    // Setter
    public void setNome(String nome) { this.nome = nome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    public void setDataNascita(String dataNascita) { this.dataNascita = dataNascita; }
    public void setIndirizzo(String indirizzo) { this.indirizzo = indirizzo; }
    public void setDocumentoId(String documentoId) { this.documentoId = documentoId; }
    
    // Restituisce true se l'oggetto rappresenta un utente "vuoto",
    // cioè non contiene informazioni significative.
    public boolean isVuoto() {

        return this.id == -1 && 
               this.nome.isEmpty() && 
               this.cognome.isEmpty() && 
               this.dataNascita.isEmpty() && 
               this.indirizzo.isEmpty() &&
               this.documentoId.isEmpty();

    }

    @Override
    public String toString() {

        return this.id + " - " 
        + this.nome + " " 
        + this.cognome + ", (" 
        + this.dataNascita + ", " 
        + this.indirizzo + ", " 
        + this.documentoId + ")";

    }

}
