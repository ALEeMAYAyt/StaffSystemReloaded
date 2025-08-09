package org.scienziato1pazzo.it.staffsystem.commandg;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.scienziato1pazzo.it.staffsystem.config.config;

public class gms implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender p, Command command, String s, String[] strings) {
        if(!(p instanceof Player)) return true;
        if(!p.hasPermission("staff.gamemode")) return true;
        ((Player) p).setGameMode(GameMode.SURVIVAL);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getC().getString("messages.gms")));
        return false;
    }
}
