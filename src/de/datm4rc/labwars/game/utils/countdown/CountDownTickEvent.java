package de.datm4rc.labwars.game.utils.countdown;


import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CountDownTickEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private String countdown;
    private int seconds;

    public CountDownTickEvent(String count, int ticks)
    {
        this.countdown = count;
        this.seconds = ticks;
    }

    public String getCountDownName()
    {
        return this.countdown;
    }

    public int getSeconds()
    {
        return this.seconds;
    }

    public HandlerList getHandlers()
    {
        return handlers;
    }

    public static HandlerList getHandlerList()
    {
        return handlers;
    }
}

