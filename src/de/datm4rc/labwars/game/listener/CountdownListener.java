package de.datm4rc.labwars.game.listener;

import de.datm4rc.labwars.Main;
import de.datm4rc.labwars.game.utils.countdown.CountDownOverEvent;
import de.datm4rc.labwars.game.utils.countdown.CountDownTickEvent;
import de.datm4rc.labwars.game.utils.gamestate.GameState;
import de.datm4rc.labwars.game.utils.player.GamePlayer;
import de.datm4rc.labwars.game.utils.player.ScoreboardAPI;
import de.datm4rc.labwars.game.utils.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CountdownListener implements Listener {

    @EventHandler
    public void onCountdownTick(CountDownTickEvent event){
        int seconds = event.getSeconds();
        String name = event.getCountDownName();

        if(name.equalsIgnoreCase("ingame")){
            for(Player player : Bukkit.getOnlinePlayers()) {
                new ScoreboardAPI().updateScoreboard(player);
            }
        }

        if(name.equalsIgnoreCase("lobby")){
            for(Player player : Bukkit.getOnlinePlayers()){
                player.setLevel(seconds);
                player.setExp(seconds/100);
            }
            if(seconds == 30 || seconds == 15 || seconds == 10 || seconds == 5 || seconds == 4 || seconds == 3 ||seconds == 2){
                Bukkit.broadcastMessage(Main.getManager().getPrefix() + "Das Spiel startet in §e" + seconds + "§7 Sekunden.");
                for(Player player : Bukkit.getOnlinePlayers()){
                    player.playNote(player.getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.A));
                }
            }
            if(seconds == 1){
                Bukkit.broadcastMessage(Main.getManager().getPrefix() + "Das Spiel startet in §e" + seconds + "§7 Sekunde.");
                for(Player player : Bukkit.getOnlinePlayers()){
                    player.playNote(player.getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.A));
                }
            }
        }
    }
    @EventHandler
    public void onCountdownEnd(CountDownOverEvent event){
        String name = event.getCountdownName();

        if(name.equalsIgnoreCase("lobby")){
            for(Player player : Bukkit.getOnlinePlayers()){
                player.setExp(0);
                player.setLevel(0);
            }
            freePlayersTooTeam();
            for(Team team : Main.getManager().getChoosenMap().getTeamArrayList()){
                for(Player player : team.getTeamPlayers()){
                    Main.getManager().getGamePlayerList().add(new GamePlayer(player, team, 25));
                    player.teleport(team.getSpawnpoint());
                    System.out.println(team.getSpawnpoint().getX());
                    player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                }
            }

            Main.getManager().setGameState(GameState.INGAME);
        }
        if(name.equalsIgnoreCase("ingame")){
            for(GamePlayer gamePlayer : Main.getManager().getGamePlayerList()){
                gamePlayer.getPlayer().getInventory().clear();
                gamePlayer.getPlayer().setHealth(20);
                Main.getManager().getGamePlayerList().remove(gamePlayer);
            }
            Main.getManager().setGameState(GameState.END);
        }
        if(name.equalsIgnoreCase("restart")){
            Bukkit.shutdown();
        }
    }
    private void freePlayersTooTeam(){
        for(Player player : Bukkit.getOnlinePlayers()){
            for(Team team : Main.getManager().getChoosenMap().getTeamArrayList()){
                if(team.getTeamPlayers().contains(player)){
                    return;
                }
            }
            Main.getManager().getChoosenMap().getTeamArrayList().sort((team1, team2) -> {
                return team1.getTeamPlayers().size() > team2.getTeamPlayers().size() ? 1
                        : (team1.getTeamPlayers().size() <= team2.getTeamPlayers().size() ? 0 : -1);
            });
            Main.getManager().getChoosenMap().getTeamArrayList().get(0).getTeamPlayers().add(player);
            new ScoreboardAPI().setTablist(player);
        }
    }

}
