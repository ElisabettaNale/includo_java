<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#il-progetto">Il progetto: IncluDO</a></li>
    <li><a href="#funzionalità">Funzionalità</a></li>
    <li><a href="#struttura-del-codice">Struttura del codice</a></li>
    <li><a href="#utilizzo">Utilizzo</a></li>
    <li><a href="#contribuire">Contribuire</a></li>
    <li><a href="#contatti">Contatti</a></li>
  </ol>
</details>



<!-- IL PROGETTO -->
## Il progetto

Questo progetto è un'applicazione sviluppata in Java che simula una piattaforma per preservare e trasmettere
le competenze dei mestieri tradizionali rendendole accessibili anche a migranti e a persone coinvolte in 
percorsi di riabilitazione sociale.<br>

L'interfaccia che l'utente ha a disposizione è solamente quella da riga di 
comando.

Gli utenti possono registrarsi alla piattaforma, visualizzare i corsi professionalizzanti disponibili e 
prenotarsi a un corso di interesse. 


<!-- FUNZIONALITà -->
## Funzionalità

- Visualizzare tutti i corsi all'interno del sistema
- Prenotare un corso esistente
- Disdire la prenotazione di un corso
- Aggiungere un nuovo utente
- Esportare un file con i corsi disponibili


<!-- Struttura -->
## Struttura del progetto

L’applicazione è organizzata seguendo il seguente schema:

```text
📂 org/javabasics/
├── 📁 controller/ (Avvio, settaggio e terminazione della piattaforma)
│   ├── Controller.java
│   
├── 📁 models/ (Classi corrispondenti ai file utenti, corsi e prenotazioni)
│   ├── Corso.java 
│   ├── Prenotazione.java 
│   ├── Utente.java
│
├── 📁 resources/ (File importati all'avvio)
│   ├── corsi.csv
│   ├── prenotazioni.csv
│   ├── utenti.csv
│   
├── 📁 services/ (Logica dell'applicativo)
│   ├── App.java (Messaggi di benvenuto e avvio input stream)
│   ├── CSVReader.java (Classe comune per la lettura dei file csv)
│   ├── Comandi.java (Lettura comandi da tastiera)
│   ├── CorsoService.java (Operazioni sulla variabile corsi)
│   ├── PrenotazioniService.java (Operazioni sulla variabile prenotazioni)
│   ├── UtenteService.java (Operazioni sulla variabile utenti)
│   ├── OperazioniHandler.java (Gestione corrispondenza fra input utente e operazione da eseguire)
│
└── 📜 Main.java
```


<!-- UTILIZZO -->
## Utilizzo

1. **Clona il repository:**
    ```bash
    git clone https://github.com/ElisabettaNale/includo_java.git
    ```
2. **Posizionati all'interno della directory:**
    ```bash
    cd includo_java
    ```

3. **Crea il file jar:**
    ```bash
    jar cfe includo.jar org.javabasics.Main org/javabasics/Main.class org/javabasics/**/*.class
    ```

4. **Esegui il file jar:**
    ```bash
    java -jar includo.jar
    ```

<!-- CONTRIBUIRE -->
## Contribuire

Se desideri contribuire al progetto, segui questi passaggi: 

1. **Forka il repository su GitHub.**

2. **Crea un nuovo branch per le tue modifiche.**

3. **Invia una request per l'integrazione delle tue modifiche nel repository principale.**


<!-- CONTATTI -->
## Contatti

Per qualsiasi domanda o suggerimenti, puoi contattarmi tramite il mio **profilo LinkedIn:** [Elisabetta Nale](https://www.linkedin.com/in/elisabetta-nale/)
e puoi anche dare un'occhiata al mio **sito web professionale:** [Home](https://elisabettanale.github.io/index.html).