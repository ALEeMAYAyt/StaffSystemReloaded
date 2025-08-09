package org.scienziato1pazzo.it.staffsystem.staffmode;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffModCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender p, Command command, String s, String[] strings) {
        if(!(p instanceof Player)) return true;
        if(!p.hasPermission("staff.staff")) {
            p.sendMessage("§cNon hai il permesso per usare questo comando!");
            return true;
        }
        StaffMode.setStaffMode((Player) p);
        return false;
    }
}
