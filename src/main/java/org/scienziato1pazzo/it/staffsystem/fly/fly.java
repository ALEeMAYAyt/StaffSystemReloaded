package org.scienziato1pazzo.it.staffsystem.fly;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.scienziato1pazzo.it.staffsystem.config.config;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class fly implements CommandExecutor {

    private static List<UUID> flyplayer = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender p, Command command, String s, String[] strings) {
        if(!(p instanceof Player)) return true;
        if(!p.hasPermission("staff.fly")) return true;
        if(flyplayer.contains(((Player) p).getUniqueId())) {
            ((Player) p).setAllowFlight(false); // Disable flight
            ((Player) p).setFlying(false);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getC().getString("messages.flyoff")));
            flyplayer.remove(((Player) p).getUniqueId());
        }else {
            ((Player) p).setAllowFlight(true);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getC().getString("messages.flyon")));
            flyplayer.add(((Player) p).getUniqueId());
        }

        return false;
    }

}
