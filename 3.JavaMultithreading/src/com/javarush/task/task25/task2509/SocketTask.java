package com.javarush.task.task25.task2509;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public abstract class SocketTask<T> implements CancellableTask<T>
{
    private Socket socket;

    protected synchronized void setSocket(Socket socket)
    {
        this.socket = socket;
    }

    public synchronized void cancel() throws IOException
    {
        //close all resources here
        try
        {
            if (socket != null && !socket.isClosed())
            {
                socket.close();
            }
        }
        catch (IOException e)
        {
            // Обработка исключения, если не удается закрыть сокет
            e.printStackTrace();
        }
    }

    public RunnableFuture<T> newTask()
    {
        return new FutureTask<T>(this)
        {
            public boolean cancel(boolean mayInterruptIfRunning)
            {
                //close all resources here by using proper SocketTask method
                //call super-class method in finally block
                try
                {
                    // Закрыть ресурсы сокета
                    SocketTask.this.cancel();
                }
                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
                finally
                {

                    super.cancel(mayInterruptIfRunning);
                }
                return true; // Результат отмены
            }
        };
    }
}