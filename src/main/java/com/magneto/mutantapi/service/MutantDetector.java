package com.magneto.mutantapi.service;

public class MutantDetector {

    private static final int secuencia_objetivo = 4;
    private static final int secuencia_requerida = 2;

    private boolean charValidation(String[] adn) {
        for (String fila : adn) {
            for (char c : fila.toCharArray()) {
                if (c != 'A' && c != 'C' && c != 'T' && c != 'G') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean tieneSecuencia(String[] adn, int fila, int col, int dFila, int dCol) {
        char base = adn[fila].charAt(col);
        int n = adn.length;

        for (int i = 1; i < secuencia_objetivo; i++) {
            int nuevaFila = fila + i * dFila;
            int nuevaCol = col + i * dCol;

            if (nuevaFila < 0 || nuevaFila >= n || nuevaCol < 0 || nuevaCol >= adn[nuevaFila].length()) {
                return false;
            }

            if (adn[nuevaFila].charAt(nuevaCol) != base) {
                return false;
            }
        }

        return true;
    }

    public boolean isMutant(String[] adn) {
        if (!charValidation(adn)) {
            throw new IllegalArgumentException("Secuencia de ADN inválida. Solo se permite A, C, G y T.");
        }

        int n = adn.length;
        int secuencias = 0;
        int[][] direcciones = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};

        for (int fila = 0; fila < n; fila++) {
            for (int col = 0; col < adn[fila].length(); col++) {
                for (int[] dir : direcciones) {
                    if (tieneSecuencia(adn, fila, col, dir[0], dir[1])) {
                        secuencias++;
                        if (secuencias >= secuencia_requerida) return true;
                    }
                }
            }
        }

        return false;
    }
}