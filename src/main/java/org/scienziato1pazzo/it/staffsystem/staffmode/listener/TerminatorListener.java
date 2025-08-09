package org.scienziato1pazzo.it.staffsystem.staffmode.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class TerminatorListener implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity target = event.getEntity();

        if (!(damager instanceof Player) || !(target instanceof Player)) return;
        Player attacker = (Player) damager;
        Player victim = (Player) target;

        ItemStack weapon = attacker.getItemInHand();
        if (weapon != null && weapon.hasItemMeta() && weapon.getItemMeta().hasDisplayName()) {
            if (weapon.getItemMeta().getDisplayName().equals("§d§lTerminator")) {
                Location loc = victim.getLocation();
                loc.getWorld().strikeLightningEffect(loc);
                victim.setHealth(0.0); // one shot
                attacker.sendMessage("§cHai TERMINATO §b" + victim.getName());

                event.setCancelled(true);
            }
        }
    }

    public static void register(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(new TerminatorListener(), plugin);
    }
}
