package org.scienziato1pazzo.it.staffsystem.vanish;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.scienziato1pazzo.it.staffsystem.config.config;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Freeze implements CommandExecutor, Listener {

    public static List<UUID> freezed = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        if(!sender.hasPermission("staff.freeze")) return true;
        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Uso: /freeze <player>");
            return true;
        }

        if(args.length == 1){
            Player target = Bukkit.getServer().getPlayer(args[0]);

            if(freezed.contains(target.getUniqueId())){
                freezed.remove(target.getUniqueId());
                target.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getC().getString("messages.unfreeze-playermsg")));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getC().getString("messages.unfreeze-staffmsg")));
            }else{
                freezed.add(target.getUniqueId());
                target.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getC().getString("messages.freeze-playermsg")));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getC().getString("messages.freeze-staffmsg")));
            }


        }


        return false;
    }

    @EventHandler
    public void onFreeze(PlayerMoveEvent e){
        for (UUID uuid : freezed) {
            Player player = Bukkit.getPlayer(uuid);
            if(e.getPlayer() == player){
                e.setCancelled(true);
            }
        }
    }

}
