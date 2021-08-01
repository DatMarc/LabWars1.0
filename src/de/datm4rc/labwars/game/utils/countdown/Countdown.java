package de.datm4rc.labwars.game.utils.countdown;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class Countdown {
    private int seconds = 2;
    private int sched = 0;
    private boolean running;
    private Plugin plug;
    private String countdownname;

    public Countdown(int seconds, String name, Plugin instance)
    {
        this.seconds = seconds;
        this.countdownname = name;
        this.plug = instance;
        setRunning(false);
    }

    public void start()
    {
        stop();
        setRunning(true);
        this.sched = Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plug, new Runnable()
        {
            public void run()
            {
                Bukkit.getServer().getPluginManager().callEvent(new CountDownTickEvent(Countdown.this.countdownname, Countdown.this.seconds));
                if (Countdown.this.seconds == 0)
                {
                    Countdown.this.stop();
                    Bukkit.getServer().getPluginManager().callEvent(new CountDownOverEvent(Countdown.this.countdownname));
                }
                Countdown.this.seconds -= 1;
            }
        }, 0L, 20L);
    }

    public void stop()
    {
        try
        {
            setRunning(false);
            Bukkit.getScheduler().cancelTask(this.sched);
        }
        catch (Exception localException) {}
        this.sched = 0;
    }

    public int getSched() {
        return sched;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getSeconds() {
        return seconds;
    }

    public String getCountdownname() {
        return countdownname;
    }

    public Plugin getPlug() {
        return plug;
    }
}

