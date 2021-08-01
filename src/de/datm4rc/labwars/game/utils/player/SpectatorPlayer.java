package de.datm4rc.labwars.game.utils.player;

import org.bukkit.entity.Player;

public class SpectatorPlayer {

    private Player player;

    public SpectatorPlayer(Player player, int balance){
        this.player = player;

    }

    public Player getPlayer() {
        return player;
    }

    public void hide(){

    }
}
