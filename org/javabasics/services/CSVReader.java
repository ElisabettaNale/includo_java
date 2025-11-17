package org.javabasics.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;


public class CSVReader {

    // Metodo generico
    public static <T> List<T> readCSV(String path, Function<List<String>, T> mapper) {
        
        List<T> result = new ArrayList<>();

        try (BufferedReader bufferedReader =
                new BufferedReader(new java.io.InputStreamReader(
                        new java.io.FileInputStream(path), StandardCharsets.UTF_8))) {

            String line;
            boolean firstLine = true;

            while ((line = bufferedReader.readLine()) != null) {
                
                line = rimuoviBOM(line);
                if (isRigaVuota(line)) continue;

                List<String> valori = Arrays.asList(line.split(";"));
                if (tuttiValoriVuoti(valori)) continue;

                // Salta intestazione (prima riga)
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                // Usa la funzione "mapper" per costruire l'oggetto
                T oggetto = mapper.apply(valori);
                if (oggetto != null) result.add(oggetto);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static boolean isRigaVuota(String line) {
        return line == null || line.trim().isEmpty();
    }

    private static boolean tuttiValoriVuoti(List<String> valori) {
        return valori.stream().allMatch(v -> v.trim().isEmpty());
    }

    private static String rimuoviBOM(String line) {
        if (line != null && line.startsWith("\uFEFF")) {
            return line.substring(1);
        }
        return line;
    }
    
}

