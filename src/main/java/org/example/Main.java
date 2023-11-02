package org.example;

import java.util.concurrent.Semaphore;

public class Main {

    //varaible global con el numero de filosofos
    private static final int NUM_FILOSOFOS=5;

    public static void main(String[] args) {
        // Un semáforo para cada tenedor
        Semaphore[] tenedores = new Semaphore[NUM_FILOSOFOS];
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            tenedores[i] = new Semaphore(1);
        }

        // Crear filósofos y comenzarlos
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            new Filosofo(i, tenedores).start();
        }

    }
}