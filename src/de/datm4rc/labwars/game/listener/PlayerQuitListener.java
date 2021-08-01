package de.datm4rc.labwars.game.listener;

import de.datm4rc.labwars.Main;
import de.datm4rc.labwars.game.utils.gamestate.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        event.setQuitMessage(null);
        Player player = event.getPlayer();
        if(Main.getManager().getGameState() == GameState.LOBBY){
            Bukkit.broadcastMessage(Main.getManager().getPrefix() + player.getName() + " hat das Spiel verlassen,");
        }
        checkStop();
    }

    private void checkStop(){
        if(Main.getManager().getGameState() == GameState.LOBBY) {
            if (Main.getManager().getCountdownLobby().isRunning()) {
                if (Bukkit.getOnlinePlayers().size() < 2) {
                    Main.getManager().getCountdownLobby().stop();
                } else {
                }
            }
        }else if(Main.getManager().getGameState() == GameState.INGAME){
            if (Bukkit.getOnlinePlayers().size() < 2) {

            }
        }
    }
}
