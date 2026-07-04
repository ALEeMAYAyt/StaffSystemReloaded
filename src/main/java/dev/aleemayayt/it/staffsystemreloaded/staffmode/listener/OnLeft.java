package dev.aleemayayt.it.staffsystemreloaded.staffmode.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import dev.aleemayayt.it.staffsystemreloaded.staffmode.StaffMode;

public class OnLeft implements Listener {
    @EventHandler
    public static void OnLeftAnPlayer(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (StaffMode.staffer.contains(player)) {
            StaffMode.cleanupPlayer(player);
        }
    }
}