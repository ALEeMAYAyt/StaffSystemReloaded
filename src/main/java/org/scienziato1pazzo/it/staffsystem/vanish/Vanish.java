package org.scienziato1pazzo.it.staffsystem.vanish;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.scienziato1pazzo.it.staffsystem.config.config;

import java.util.*;

public class Vanish implements CommandExecutor, Listener {

    public static List<UUID> staffModePlayers = new ArrayList<>();


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        if(!sender.hasPermission("staff.vanish")) return true;
        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("vanish")) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "Uso: /vanish <on|off>");
                return true;
            }

            if (args[0].equalsIgnoreCase("on")) {
                enableVanish(player);
                for (UUID uuid : staffModePlayers) {
                    if(uuid != player.getUniqueId()){
                        Bukkit.getPlayer(uuid).showPlayer(player);
                        player.showPlayer(Bukkit.getPlayer(uuid));
                    }
                }

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getC().getString("messages.vanishon")));
            } else if (args[0].equalsIgnoreCase("off")) {
                disableVanish(player);
                if(staffModePlayers.isEmpty()){
                }else {
                    for (UUID uuid : staffModePlayers) {
                        if(uuid != player.getUniqueId()){
                            Bukkit.getPlayer(uuid).showPlayer(player);
                            player.hidePlayer(Bukkit.getPlayer(uuid));
                        }
                    }
                }
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getC().getString("messages.vanishoff")));

            }
            return true;
        }

        return false;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        for (UUID staffModePlayer : staffModePlayers) {
            Player staffPlayer = Bukkit.getPlayer(staffModePlayer);
            if (staffPlayer != null) {
                staffPlayer.hidePlayer(player);
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (staffModePlayers.contains(player.getUniqueId())) {
            staffModePlayers.remove(player.getUniqueId());
        }
    }

    private static void enableVanish(Player player) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (!staffModePlayers.contains(onlinePlayer.getUniqueId())) {
                onlinePlayer.hidePlayer(player);
            }
        }
        staffModePlayers.add(player.getUniqueId());

    }

    private static void disableVanish(Player player) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.showPlayer(player);
        }
        staffModePlayers.remove(player.getUniqueId());
    }




}
