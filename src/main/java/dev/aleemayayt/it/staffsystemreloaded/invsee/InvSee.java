package dev.aleemayayt.it.staffsystemreloaded.invsee;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InvSee implements CommandExecutor {

    private static final Map<UUID, UUID> openInventories = new HashMap<>();

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!(sender instanceof Player)) return true;
        if (!sender.hasPermission("staff.invsee")) return true;

        final Player player = (Player) sender;

        if (args.length != 1) {
            return true;
        }

        final Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(ChatColor.RED + "Il giocatore non è online!");
            return true;
        }

        String title = ChatColor.AQUA + "Inventario di " + target.getName();
        Inventory viewInventory = Bukkit.createInventory(player, 9, title);

        ItemStack[] contents = target.getInventory().getContents();
        viewInventory.setContents(contents);

        openInventories.put(player.getUniqueId(), target.getUniqueId());

        player.openInventory(viewInventory);

        return true;
    }

    public static Player getTarget(Player viewer) {
        UUID targetId = openInventories.get(viewer.getUniqueId());
        if (targetId != null) {
            return Bukkit.getPlayer(targetId);
        }
        return null;
    }

    public static void closeSession(Player viewer) {
        openInventories.remove(viewer.getUniqueId());
    }
}