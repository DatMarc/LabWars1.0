package de.datm4rc.labwars.game.utils.map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Spawn {

    public Location getLoc(String mapname, String spawnname)
    {
        File file = new File("plugins/Spawns/" + mapname + "/" +spawnname + ".yml");
        System.out.println(mapname + " " + spawnname);
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        double x = cfg.getDouble("X");
        double y = cfg.getDouble("Y");
        double z = cfg.getDouble("Z");
        double yaw = cfg.getDouble("YAW");
        double pitch = cfg.getDouble("PITCH");

        World w = Bukkit.getWorld(mapname);
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
        return new Location(w, x, y, z, (float)yaw, (float)pitch);
    }

}


