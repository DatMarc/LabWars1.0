package de.datm4rc.labwars.game.listener;

import de.datm4rc.labwars.Main;
import de.datm4rc.labwars.game.Manager;
import de.datm4rc.labwars.game.utils.ItemBuilder;
import de.datm4rc.labwars.game.utils.gamestate.GameState;
import de.datm4rc.labwars.game.utils.player.ScoreboardAPI;
import de.datm4rc.labwars.game.utils.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        if(Main.getManager().getGameState() == GameState.LOBBY){
            Bukkit.broadcastMessage(player.getName() + " hat das Spiel betreten,");
            player.getInventory().setItem(0, new ItemBuilder(Material.BED).setName("Teamauswahl").toItemStack());
            checkStart();
            new ScoreboardAPI().updateScoreboard(player);
        }
        if(Main.getManager().getGameState() == GameState.INGAME){
            //Spectator
        }
    }


    private void checkStart(){
        if(!Main.getManager().getCountdownLobby().isRunning()){
            if(Bukkit.getOnlinePlayers().size() >= 1) {
                Bukkit.broadcastMessage(Main.getManager().getPrefix() + "Das Spiel startet in §e60 §7Sekunden.");
                Main.getManager().getCountdownLobby().start();
                for(Player player : Bukkit.getOnlinePlayers()){
                    player.playNote(player.getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.A));
                }
        }else{
               Bukkit.broadcastMessage("Es fehlen noch - Spieler");
           }
        }
    }


}
