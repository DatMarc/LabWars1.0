package de.datm4rc.labwars;

import de.datm4rc.labwars.game.Manager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Manager manager;

    @Override
    public void onEnable() {
        manager = new Manager(this);
    }

    @Override
    public void onDisable() {

    }

    public static Manager getManager() {
        return manager;
    }
}
