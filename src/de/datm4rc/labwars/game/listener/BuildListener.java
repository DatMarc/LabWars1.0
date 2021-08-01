package de.datm4rc.labwars.game.listener;

import de.datm4rc.labwars.Main;
import de.datm4rc.labwars.game.utils.gamestate.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BuildListener implements Listener {

    @EventHandler
    public void onBuild(BlockPlaceEvent event){
        if(Main.getManager().getGameState() == GameState.LOBBY){
            event.setCancelled(true);
        }
        if(Main.getManager().getGameState() == GameState.END){
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onBreak(BlockBreakEvent event){
        if(Main.getManager().getGameState() == GameState.LOBBY){
            event.setCancelled(true);
        }
        if(Main.getManager().getGameState() == GameState.END){
            event.setCancelled(true);
        }
    }

}
