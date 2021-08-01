package de.datm4rc.labwars.game.utils.countdown;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CountDownOverEvent extends Event
{
    private static final HandlerList handlers = new HandlerList();
    private String countdown;

    public CountDownOverEvent(String count)
    {
        this.countdown = count;
    }

    public String getCountdownName()
    {
        return this.countdown;
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
