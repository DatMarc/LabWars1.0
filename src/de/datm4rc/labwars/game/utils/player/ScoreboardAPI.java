package de.datm4rc.labwars.game.utils.player;
import de.datm4rc.labwars.Main;
import de.datm4rc.labwars.game.utils.gamestate.GameState;
import de.datm4rc.labwars.game.utils.team.Team;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ScoreboardAPI {

    public Scoreboard sbpacket = new Scoreboard();
    public List<CodeScoreboardSore> scores = new ArrayList<>();

    public void removeScoreboard(Player player) {
        if(sbpacket.getObjective(player.getName()) != null) {
            sbpacket.unregisterObjective(sbpacket.getObjective(player.getName()));
            scores.clear();
        }
    }

    public void updateScoreboard(Player player) {
        removeScoreboard(player);
        sendPackets(player);
    }
    private void sendPackets(Player player) {
        if(Main.getManager().getGameState() == GameState.LOBBY) {
            ScoreboardObjective obj = sbpacket.registerObjective(player.getName(), IScoreboardCriteria.b);
            obj.setDisplayName("§6§lLabWars");
            PacketPlayOutScoreboardObjective remove = new PacketPlayOutScoreboardObjective(obj, 1);
            sendPacket(player, remove);
            PacketPlayOutScoreboardObjective createPacket = new PacketPlayOutScoreboardObjective(obj, 0);
            PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);
            sendPacket(player, createPacket);
            sendPacket(player, display);
            new CodeScoreboardSore(sbpacket, obj, "§3", 7);
            new CodeScoreboardSore(sbpacket, obj, "§7┃§e Map:", 6);
            new CodeScoreboardSore(sbpacket, obj, "§e§8» §f" + Main.getManager().getChoosenMap().getName(), 5);
            new CodeScoreboardSore(sbpacket, obj, "§a", 4);
            new CodeScoreboardSore(sbpacket, obj, "§7┃§e Builder:", 3);
            new CodeScoreboardSore(sbpacket, obj, "§8»§a §f" + Main.getManager().getChoosenMap().getAuthor(), 2);
            new CodeScoreboardSore(sbpacket, obj, "§d§c", 1);
            PacketPlayOutScoreboardScore score;
            for (CodeScoreboardSore scoreboardSore : scores) {
                score = new PacketPlayOutScoreboardScore(scoreboardSore);
                sendPacket(player, score);
            }
        }
        if(Main.getManager().getGameState() == GameState.INGAME) {
            ScoreboardObjective obj = sbpacket.registerObjective(player.getPlayer().getName(), IScoreboardCriteria.b);
            obj.setDisplayName("§6§lLabWars");
            PacketPlayOutScoreboardObjective remove = new PacketPlayOutScoreboardObjective(obj, 1);
            sendPacket(player.getPlayer(), remove);
            PacketPlayOutScoreboardObjective createPacket = new PacketPlayOutScoreboardObjective(obj, 0);
            PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);
            sendPacket(player.getPlayer(), createPacket);
            sendPacket(player.getPlayer(), display);
            new CodeScoreboardSore(sbpacket, obj, "§3", 16);
            int i = 2;
            for(Team teams : Main.getManager().getChoosenMap().getTeamArrayList()){
                if(teams.isBedAlive()){
                    new CodeScoreboardSore(sbpacket, obj, "§a✔ " + teams.getTeamColor().getColor() + teams.getTeamColor().getName(), i);
                }else {
                    new CodeScoreboardSore(sbpacket, obj, "§4✘ " + teams.getTeamColor().getColor() + teams.getTeamColor().getName(), i);
                }
                i++;
            }
            new CodeScoreboardSore(sbpacket, obj, "§d§c", 1);
            PacketPlayOutScoreboardScore score;
            for (CodeScoreboardSore scoreboardSore : scores) {
                score = new PacketPlayOutScoreboardScore(scoreboardSore);
                sendPacket(player.getPlayer(), score);
            }
        }
    }

    public void sendPacket(Player player, Packet<?> packet) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
    public class CodeScoreboardSore extends ScoreboardScore {
        public CodeScoreboardSore(Scoreboard scoreboard, ScoreboardObjective scoreboardObjective, String s, int score) {
            super(scoreboard, scoreboardObjective, s);
            setScore(score);
            scores.add(this);
        }
    }
    public void setTablist(Player player) {
                 if(player.getScoreboard() == null) {
                   player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
                }
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.getScoreboard() == null) {
                        all.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
                    }
                    for (Team team : Main.getManager().getChoosenMap().getTeamArrayList()) {
                        org.bukkit.scoreboard.Team scoreTeam = all.getScoreboard().getTeam(team.getTeamColor().getName());
                        if (scoreTeam == null) {
                            scoreTeam = all.getScoreboard().registerNewTeam(team.getTeamColor().getName());
                        }
                        scoreTeam.setPrefix(team.getTeamColor().getColor() + team.getTeamColor().getName() + " §7- " + team.getTeamColor().getColor());
                        if (team.getTeamPlayers().contains(player)) {
                            scoreTeam.addEntry(player.getName());
                        }
                        if (team.getTeamPlayers().contains(all)) {
                            scoreTeam.addEntry(all.getName());
                        }
                    }
                }

//        if (CloudAPI.getInstance().getPermissionPool() != null) {
//            if(CloudAPI.getInstance().getPermissionPool().isAvailable()) {
//                CloudServer.getInstance().updateNameTags(player);
//                CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(UUID.fromString(player.getUniqueId().toString()));
//                if(player.getScoreboard() == null) {
//                    player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
//                }
//                for (Player all : Bukkit.getOnlinePlayers()) {
//                    if (all.getScoreboard() == null) {
//                        all.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
//                    }
//                    PermissionGroup permissionGroup;
//                    permissionGroup = cloudPlayer.getPermissionEntity().getHighestPermissionGroup(CloudAPI.getInstance().getPermissionPool());
//                    if (permissionGroup != null) {
//                        Team team = all.getScoreboard().getTeam(permissionGroup.getTagId() + "A");
//                        if (team == null) {
//                            team = all.getScoreboard().registerNewTeam(permissionGroup.getTagId()+ "A");
//                        }
//                        team.setPrefix(ChatColor.translateAlternateColorCodes('&', permissionGroup.getPrefix()));
//                        team.addEntry(player.getName());
//                    }
//                    CloudPlayer cloudPlayerALL = CloudAPI.getInstance().getOnlinePlayer(all.getUniqueId());
//                    if (cloudPlayerALL != null) {
//                        PermissionGroup group = null;
//                        group = cloudPlayerALL.getPermissionEntity().getHighestPermissionGroup(CloudAPI.getInstance().getPermissionPool());
//                        if (group != null) {
//                            Team team = all.getScoreboard().getTeam(group.getTagId()+"A");
//                            if (team == null) {
//                                team = all.getScoreboard().registerNewTeam(group.getTagId()+"A");
//                            }
//                            team.setPrefix(ChatColor.translateAlternateColorCodes('&', group.getPrefix()));
//                            team.addEntry(all.getName());
//                        }
//                    }
//                }
//            }
//
//        }
    }

}
