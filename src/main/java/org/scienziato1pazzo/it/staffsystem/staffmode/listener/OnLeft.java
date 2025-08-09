package org.scienziato1pazzo.it.staffsystem.staffmode.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.scienziato1pazzo.it.staffsystem.staffmode.StaffModCommand;
import org.scienziato1pazzo.it.staffsystem.staffmode.StaffMode;

public class OnLeft implements Listener {
    @EventHandler
    public static void OnLeftAnPlayer(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(StaffMode.staffer.contains(player)){
            StaffMode.setStaffMode(player);
        }
    }
}
