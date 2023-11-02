package org.example;

import java.util.Random;
import java.util.concurrent.Semaphore;



public class Filosofo extends Thread{
    private static final int NUM_FILOSOFOS =5 ;
    private final int id;
    private final Semaphore[] tenedores;
    private final Random rand=new Random();

    public Filosofo(int id, Semaphore[] tenedores) {
        this.id = id;
        this.tenedores = tenedores;
    }

    @Override
    public void run(){
        try{
            while (true){
                pensar();
                tomarTenedores();
                comer();
                soltarTenedores();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private  void pensar()throws InterruptedException{
        System.out.println("El filósofo " + id + " está pensando");
        Thread.sleep(rand.nextInt(4000)+1000);
    }

    private void tomarTenedores()throws InterruptedException{
        //intentamos obtener el tenedor de la izquierda
        tenedores[id].acquire();

        tenedores[(id+1)%NUM_FILOSOFOS].release();
    }

    private void comer()throws InterruptedException{
        System.out.println("Filósofo " + id + " comiendo");
        Thread.sleep(rand.nextInt(4000) + 1000);
    }

    private  void soltarTenedores(){
        //soltar el tenedor de la izquierda
        tenedores[id].release();
        //soltar el tenedor de la derechar
        tenedores[(id + 1) % NUM_FILOSOFOS].release();
    }




}
