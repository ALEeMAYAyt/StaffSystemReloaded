package dev.aleemayayt.it.staffsystemreloaded.invsee;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class InvSeeListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;

        Player viewer = (Player) event.getWhoClicked();
        Inventory clickedInventory = event.getInventory();
        Player target = InvSee.getTarget(viewer);

        if (target == null) return;

        String title = clickedInventory.getTitle();
        if (!title.startsWith("Inventario di ")) return;

        event.setCancelled(true);

        int rawSlot = event.getRawSlot();
        if (rawSlot < 0 || rawSlot >= 54) return;

        ItemStack[] contents = clickedInventory.getContents();
        target.getInventory().setContents(contents);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player)) return;

        Player viewer = (Player) event.getPlayer();
        InvSee.closeSession(viewer);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player target = event.getPlayer();
        UUID targetId = target.getUniqueId();

        Set<Player> viewers = Bukkit.getOnlinePlayers().stream()
                .filter(viewer -> InvSee.getTarget(viewer) != null)
                .filter(viewer -> InvSee.getTarget(viewer).getUniqueId().equals(targetId))
                .collect(Collectors.toSet());

        for (Player viewer : viewers) {
            viewer.closeInventory();
            InvSee.closeSession(viewer);
        }
    }
}