package de.datm4rc.labwars.game.utils.map;

public enum GameStyle {
    //PlayerXTeams

    ONEXEIGHT(8, 2, 1),TWOXFOUR(8, 4, 2),FOURXFOUR(16, 8, 4);

    private int maxPlayers;
    private int minPlayers;
    private int playersPerTeam;

    private GameStyle(int maxPlayers, int minPlayers, int playersPerTeam){
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.playersPerTeam = playersPerTeam;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getPlayersPerTeam() {
        return playersPerTeam;
    }
}
