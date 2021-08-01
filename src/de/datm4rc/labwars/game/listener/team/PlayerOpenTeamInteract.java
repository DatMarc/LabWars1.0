package de.datm4rc.labwars.game.listener.team;

import de.datm4rc.labwars.Main;
import de.datm4rc.labwars.game.utils.gamestate.GameState;
import de.datm4rc.labwars.game.utils.player.ScoreboardAPI;
import de.datm4rc.labwars.game.utils.team.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerOpenTeamInteract implements Listener {

    @EventHandler
    public void onInteractTeamItem(PlayerInteractEvent event){
        try{
          //  if(Main.getManager().getGameState() == GameState.LOBBY){
                Player player = event.getPlayer();
                if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Teamauswahl")){
                    player.openInventory(Main.getManager().getInventoryManager().getInventoryTeamauswahl());
                }
            //}
        }catch (Exception e){

        }

    }
    @EventHandler
    public void onClickTeamInvEvent(InventoryClickEvent event){
        try{
            if(Main.getManager().getGameState() == GameState.LOBBY){
                if(event.getClickedInventory().getTitle().equalsIgnoreCase("Teamauswahl")) {
                    for (Team team : Main.getManager().getChoosenMap().getTeamArrayList()) {
                        if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(team.getTeamColor().getColor() + team.getTeamColor().getName())) {
                            if (team.getTeamPlayers().size() != team.getMaxPlayers()) {
                                if(!team.getTeamPlayers().contains((Player) event.getWhoClicked())) {
                                    for (Team teamRemove : Main.getManager().getChoosenMap().getTeamArrayList()) {
                                        teamRemove.getTeamPlayers().remove(event.getWhoClicked());
                                    }
                                    team.getTeamPlayers().add((Player) event.getWhoClicked());
                                    event.getWhoClicked().sendMessage(Main.getManager().getPrefix() + "Du bist nun in Team " + team.getTeamColor().getColor() + team.getTeamColor().getName());
                                    event.getWhoClicked().closeInventory();
                                    new ScoreboardAPI().setTablist((Player) event.getWhoClicked());
                                    return;
                                }else{
                                    event.getWhoClicked().sendMessage(Main.getManager().getPrefix() + "§cDu bist bereits in diesem Team.");
                                }
                            }else{
                                event.getWhoClicked().sendMessage(Main.getManager().getPrefix() + "§cDas Team ist voll.");
                            }
                        }
                    }
                }
                event.setCancelled(true);
            }
        }catch (Exception e){

        }
    }

}
