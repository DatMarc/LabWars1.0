package de.datm4rc.labwars.game.listener;

import de.datm4rc.labwars.Main;
import de.datm4rc.labwars.game.utils.gamestate.GameState;
import de.datm4rc.labwars.game.utils.team.Team;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;


public class PlayerListener implements Listener {

    @EventHandler
    public void onFoodLevel(FoodLevelChangeEvent event){
        if(Main.getManager().getGameState() == GameState.LOBBY){
            event.setCancelled(true);
        }
        if(Main.getManager().getGameState() == GameState.END){
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onDamage(EntityDamageEvent entityDamageEvent){
        if(entityDamageEvent.getEntity().getType() == EntityType.VILLAGER){
            entityDamageEvent.setCancelled(true);
        }
        if(Main.getManager().getGameState() == GameState.LOBBY || Main.getManager().getGameState() == GameState.END){
            entityDamageEvent.setCancelled(true);
        }
    }
    @EventHandler
    public void onSpawn(PlayerSpawnLocationEvent event){
        if(Main.getManager().getGameState() == GameState.LOBBY){
            event.setSpawnLocation(Main.getManager().getLobbySpawn());
        }
        if(Main.getManager().getGameState() == GameState.INGAME){
            for (Team team : Main.getManager().getChoosenMap().getTeamArrayList()) {
                if(team.getTeamPlayers().contains(event.getPlayer())){
                    event.setSpawnLocation(team.getSpawnpoint());
                }else{

                }
            }

        }
    }
    @EventHandler
    public void onReSpawn(PlayerRespawnEvent event){
        if(Main.getManager().getGameState() == GameState.LOBBY){
            event.setRespawnLocation(Main.getManager().getLobbySpawn());
        }
        if(Main.getManager().getGameState() == GameState.INGAME){
            for (Team team : Main.getManager().getChoosenMap().getTeamArrayList()) {
                if(team.getTeamPlayers().contains(event.getPlayer())){
                    event.setRespawnLocation(team.getSpawnpoint());
                }else{

                }
            }

        }
    }
}
