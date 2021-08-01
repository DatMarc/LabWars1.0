package de.datm4rc.labwars.game.utils.player;


import de.datm4rc.labwars.game.utils.team.Team;
import org.bukkit.entity.Player;

public class GamePlayer {


    private Player player;
    private Team team;
    private int balance;

    public GamePlayer(Player player, Team team, int balance){
        this.player = player;
        this.balance = balance;
    }

    public Player getPlayer() {
        return player;
    }

    public int getBalance() {
        return balance;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    public void addBalance(int balance){
        this.balance = this.balance + balance;
    }
}
