package de.datm4rc.labwars.game;

import de.datm4rc.labwars.Main;
import de.datm4rc.labwars.game.listener.*;
import de.datm4rc.labwars.game.listener.team.PlayerOpenTeamInteract;
import de.datm4rc.labwars.game.utils.InventoryManager;
import de.datm4rc.labwars.game.utils.countdown.Countdown;
import de.datm4rc.labwars.game.utils.gamestate.GameState;
import de.datm4rc.labwars.game.utils.map.GameStyle;
import de.datm4rc.labwars.game.utils.map.Map;
import de.datm4rc.labwars.game.utils.player.GamePlayer;
import de.datm4rc.labwars.game.utils.player.SpectatorPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Manager {

    private Countdown countdownLobby;
    private Countdown countdownIngame;
    private Countdown countdownRestart;

    private List<GamePlayer> gamePlayerList;
    private List<SpectatorPlayer> spectatorPlayerList;
    private List<Map> mapList;

    private GameState gameState;
    private InventoryManager inventoryManager;
    private String prefix = "§8» §6§lLabWars §8| §7";

    private Plugin main;

    private Map choosenMap;

    public Manager(Plugin main) {
        this.main = main;

        countdownLobby = new Countdown(60, "lobby", main);
        countdownIngame = new Countdown(60 * 60, "ingame", main);
        countdownRestart = new Countdown(15, "restart", main);
        gameState = GameState.LOBBY;

        inventoryManager = new InventoryManager();
        gamePlayerList = new ArrayList<GamePlayer>();
        spectatorPlayerList = new ArrayList<SpectatorPlayer>();
        mapList = new ArrayList<>();
        mapList.add(new Map("MixBedwars", "Gran Premio d'Italia", "SilentPain", GameStyle.FOURXFOUR));

        choosenMap = chooseMap();

        getChoosenMap().loadMap();
        getChoosenMap().createTeams();

        registerListener();
    }


    public Plugin getMain() {
        return main;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public void registerListener(){
        main.getServer().getPluginManager().registerEvents(new PlayerListener(), main);
        main.getServer().getPluginManager().registerEvents(new PlayerOpenTeamInteract(), main);
        main.getServer().getPluginManager().registerEvents(new CountdownListener(), main);
        main.getServer().getPluginManager().registerEvents(new BuildListener(), main);
        main.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), main);
        main.getServer().getPluginManager().registerEvents(new PlayerQuitListener(), main);
    }

    public Map getChoosenMap() {
        return choosenMap;
    }

    public Countdown getCountdownLobby() {
        return countdownLobby;
    }

    public Countdown getCountdownIngame() {
        return countdownIngame;
    }

    public Countdown getCountdownRestart() {
        return countdownRestart;
    }

    public GameState getGameState() {
        return gameState;
    }

    public List<GamePlayer> getGamePlayerList() {
        return gamePlayerList;
    }

    public List<SpectatorPlayer> getSpectatorPlayerList() {
        return spectatorPlayerList;
    }

    public String getPrefix() {
        return prefix;
    }
    public Map chooseMap(){
        return getMapList().get(0);
    }

    public Location getLobbySpawn(){
        return new Location(Bukkit.getWorld("world"), 243,4.5,-53, 90, 0);
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<Map> getMapList() {
        return mapList;
    }
}
