package com.javarush.task.task27.task2709;

public class ConsumerTask implements Runnable {
    private TransferObject transferObject;
    protected volatile boolean stopped;

    public ConsumerTask(TransferObject transferObject) {
        this.transferObject = transferObject;
        new Thread(this, "ConsumerTask").start();
    }

    public void run() {
        while (!stopped) {
            try
            {
                transferObject.get();
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    public void stop() {
        stopped = true;
    }
}