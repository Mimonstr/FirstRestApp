package com.javarush.task.task27.task2712.statistic.event;

import com.javarush.task.task27.task2712.ad.Advertisement;

import java.util.Date;
import java.util.List;

public class VideoSelectedEventDataRow implements EventDataRow
{
    private Date currentDate = new Date();
    private List<Advertisement> optimalVideoSet; // - список видео-роликов, отобранных для показа
    private long amount; // - сумма денег в копейках
    private int totalDuration; // - общая продолжительность показа отобранных рекламных роликов

    public VideoSelectedEventDataRow(List<Advertisement> optimalVideoSet, long amount, int totalDuration)
    {
        this.optimalVideoSet = optimalVideoSet;
        this.amount = amount;
        this.totalDuration = totalDuration;
        this.currentDate = new Date();
    }

    @Override
    public EventType getType()
    {
        return EventType.SELECTED_VIDEOS;
    }

    @Override
    public Date getDate()
    {
        return this.currentDate;
    }

    @Override
    public int getTime()
    {
        return totalDuration;
    }

    public long getAmount()
    {
        return amount;
    }
}
