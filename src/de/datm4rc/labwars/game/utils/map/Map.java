package de.datm4rc.labwars.game.utils.map;


import de.datm4rc.labwars.game.utils.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Map {


    private String worldname;
    private String name;
    private String author;
    private GameStyle gameStyle;
    private ArrayList<Team> teamArrayList;

    public Map(String worldname, String name, String author, GameStyle gameStyle){
        this.worldname = worldname;
        this.name = name;
        this.author = author;
        this.gameStyle = gameStyle;
        this.teamArrayList = new ArrayList<>();
        System.out.println(worldname + name + author);

    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public GameStyle getGameStyle() {
        return gameStyle;
    }

    public String getWorldname() {
        return worldname;
    }

    public void createTeams(){
        for(int i = 0; i < gameStyle.getMaxPlayers()/gameStyle.getPlayersPerTeam(); i++){
            Team team = new Team(i, gameStyle.getMaxPlayers());
            team.setBedspawnpoint(new Spawn().getLoc(getWorldname(), "bedTeam" + (i+1)));
            team.setSpawnpoint(new Spawn().getLoc(getWorldname(), "spawnTeam" + (i+1)));
            teamArrayList.add(team);
        }
    }
    public void loadMap(){
        Bukkit.createWorld(new WorldCreator(getWorldname()));
    }

    public ArrayList<Team> getTeamArrayList() {
        return teamArrayList;
    }
}
