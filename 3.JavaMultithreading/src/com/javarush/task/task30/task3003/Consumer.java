package com.javarush.task.task30.task3003;

import java.util.concurrent.TransferQueue;

public class Consumer implements Runnable
{
    private TransferQueue<ShareItem> queue;

    public Consumer(TransferQueue<ShareItem> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(450);
        }
        catch (InterruptedException e)
        {
            //throw new RuntimeException(e);
        }
        while (true)
        {
            ShareItem item = null;
            try
            {
                item = queue.take();
            }
            catch (InterruptedException e)
            {
                //throw new RuntimeException(e);
            }
            if(item!= null)
            System.out.format("Processing %s\n", item.toString());
        }

    }
}
