package com.javarush.task.task27.task2712.ad;

public class Advertisement
{
    private Object content; //- видео
    private String name; // - имя/название
    private long initialAmount; // - начальная сумма, стоимость рекламы в копейках. Используем long, чтобы избежать проблем с округлением
    private int hits; // - количество оплаченных показов
    private int duration; // - продолжительность в секундах

    private long amountPerOneDisplaying;//стоимости одного показа рекламного объявления в копейках

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration)
    {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        if(this.hits!=0) this.amountPerOneDisplaying = this.initialAmount/this.hits;
    }

    public String getName()
    {
        return name;
    }

    public int getHits()
    {
        return hits;
    }

    public int getDuration()
    {
        return duration;
    }

    public long getAmountPerOneDisplaying()
    {
        return amountPerOneDisplaying;
    }

    public void revalidate()
    {
        if(hits <= 0) throw new UnsupportedOperationException();
        hits--;
    }
    public boolean isActive()
    {
        return hits > 0;
    }
}
