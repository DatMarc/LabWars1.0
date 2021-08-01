package de.datm4rc.labwars.game.utils;

import de.datm4rc.labwars.Main;
import de.datm4rc.labwars.game.utils.player.GamePlayer;
import de.datm4rc.labwars.game.utils.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class InventoryManager {

    private Inventory inventoryTeamauswahl;

    public Inventory getInventoryTeamauswahl() {
        inventoryTeamauswahl = Bukkit.createInventory(null, 27, "Teamauswahl");
        for(int i = 0; i < 9; i++){
            inventoryTeamauswahl.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 15).setName("§c").toItemStack());
        }
        for(int i = 18; i < 27; i++){
            inventoryTeamauswahl.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 15).setName("§c").toItemStack());
        }
        for(Team team : Main.getManager().getChoosenMap().getTeamArrayList()){
            ArrayList<String> playerNameList = new ArrayList<>();
            playerNameList.clear();
            for (Player player : team.getTeamPlayers()){
                playerNameList.add("§7➜ " + player.getName());
            }
            for(int i = playerNameList.size(); i < team.getMaxPlayers(); i++){
                playerNameList.add("§7➜ (×)");
            }

            inventoryTeamauswahl.addItem(new ItemBuilder(Material.WOOL).setLore(playerNameList).setDyeColor(team.getTeamColor().getDyeColor()).setName(team.getTeamColor().getColor() + team.getTeamColor().getName()).toItemStack());
            playerNameList.clear();
        }
        return inventoryTeamauswahl;
    }
}
