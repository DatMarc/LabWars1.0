package de.datm4rc.labwars.game.utils.team;

import de.datm4rc.labwars.game.utils.player.GamePlayer;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Team {

    private int id;
    private TeamColor teamColor;
    private int maxPlayers;
    private Location spawnpoint;
    private Location bedspawnpoint;
    private ArrayList<Player> teamPlayers;
    private boolean bedAlive;

    public Team(int id, int maxPlayers){
        this.id = id;
        this.maxPlayers = maxPlayers;
        createColor();
        teamPlayers = new ArrayList<>();
        bedAlive = true;
    }

    public void createColor(){
        for(int i = 0; i < TeamColor.values().length; i++){
            if(TeamColor.values()[i].getId() == this.id){
                this.teamColor = TeamColor.values()[i];
                System.out.println(teamColor.getName());
                return;
            }
        }
    }

    public boolean isBedAlive() {
        return bedAlive;
    }

    public void setBedAlive(boolean bedAlive) {
        this.bedAlive = bedAlive;
    }

    public ArrayList<Player> getTeamPlayers() {
        return teamPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }

    public Location getBedspawnpoint() {
        return bedspawnpoint;
    }

    public Location getSpawnpoint() {
        return spawnpoint;
    }

    public void setBedspawnpoint(Location bedspawnpoint) {
        this.bedspawnpoint = bedspawnpoint;
    }

    public void setSpawnpoint(Location spawnpoint) {
        this.spawnpoint = spawnpoint;
    }
}
