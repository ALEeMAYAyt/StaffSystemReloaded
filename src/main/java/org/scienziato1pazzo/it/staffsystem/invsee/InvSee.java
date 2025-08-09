package org.scienziato1pazzo.it.staffsystem.invsee;


import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InvSee implements CommandExecutor {
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!(sender instanceof Player)) return true;
        if(!sender.hasPermission("staff.invsee")) return true;
        final Player player = (Player)sender;
        if (args.length != 1) {
            // sbagliato a scrivere
            return true;
        }
        final Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            // target is null
            return true;
        }

        player.openInventory((Inventory)target.getInventory());


        return false;
    }
}