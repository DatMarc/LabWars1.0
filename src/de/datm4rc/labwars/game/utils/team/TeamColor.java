package de.datm4rc.labwars.game.utils.team;

import org.bukkit.Color;
import org.bukkit.DyeColor;

public enum TeamColor {

    RED(0, "Rot", "§c", DyeColor.RED), BLUE(1, "Blau", "§9", DyeColor.BLUE),
    ORANGE(2, "Orange", "§6", DyeColor.ORANGE), PURPLE(3, "Lila", "§5", DyeColor.PURPLE)
    , GREEN(4, "Grün", "§a", DyeColor.GREEN), YELLOW(5, "Gelb","§e", DyeColor.YELLOW)
    , AQUA(6, "Aqua", "§b", DyeColor.CYAN), PINK(7, "Pink", "§d", DyeColor.MAGENTA);

    private int id;
    private String name;
    private String color;
    private DyeColor dyeColor;

    TeamColor(int id, String name, String color, DyeColor dyeColor){
        this.name = name;
        this.id = id;
        this.color = color;
        this.dyeColor = dyeColor;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public DyeColor getDyeColor() {
        return dyeColor;
    }

    public int getId() {
        return id;
    }
}
