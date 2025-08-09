package org.scienziato1pazzo.it.staffsystem.staffmode.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import static org.scienziato1pazzo.it.staffsystem.staffmode.StaffMode.*;

public class Picchiatore implements Listener {

    @EventHandler
    public static void onPlayerCausedDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            Player attacker = (Player) e.getDamager();
            Player target = (Player) e.getEntity();
            if (attacker.getItemInHand() == null) return;
            if(staffer.isEmpty()) return;
            if (staffer.contains(attacker.getUniqueId())) {
                if (!(attacker.getItemInHand().getType() == Material.BLAZE_ROD)) {
                    e.setCancelled(true);
                }
            }

        }
    }
}