package org.scienziato1pazzo.it.staffsystem.staffmode.listener;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.scienziato1pazzo.it.staffsystem.config.config;
import org.scienziato1pazzo.it.staffsystem.staffmode.StaffMode;
import org.scienziato1pazzo.it.staffsystem.vanish.Vanish;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StaffModeListener implements Listener {

    @EventHandler
    public void onPlayerInteractToInventory(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity clickedEntity = event.getRightClicked();
        ItemStack itemInHand = player.getItemInHand();

        if (itemInHand == null || !itemInHand.hasItemMeta() || !itemInHand.getItemMeta().hasDisplayName()) return;

        String displayName = itemInHand.getItemMeta().getDisplayName();

        if (displayName.equalsIgnoreCase("§aInventario") && clickedEntity instanceof Player) {
            Player clickedPlayer = (Player) clickedEntity;
            Bukkit.dispatchCommand(player, "inv " + clickedPlayer.getName());
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getItemInHand();

        if (itemInHand == null || !itemInHand.hasItemMeta() || !itemInHand.getItemMeta().hasDisplayName()) return;

        String displayName = itemInHand.getItemMeta().getDisplayName();

        // VANISH
        if (displayName.equalsIgnoreCase("§cVanish OFF") || displayName.equalsIgnoreCase("§aVanish ON")) {
            if (Vanish.staffModePlayers.contains(player.getUniqueId())) {
                Bukkit.dispatchCommand(player, "vanish off");
            } else {
                Bukkit.dispatchCommand(player, "vanish on");
            }

            itemInHand.setType(StaffMode.getvanish(player).getType());
            itemInHand.setItemMeta(StaffMode.getvanish(player).getItemMeta());
            return;
        }

        // RANDOM TP
        if (displayName.equalsIgnoreCase("§e§lRandom TP")) {
            Player playertp = getRandomOnlinePlayer(player);
            if (playertp == null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getC().getString("messages.randomtpnull")));
                return;
            }
            player.teleport(playertp);
        }
    }

    private Player getRandomOnlinePlayer(Player excludePlayer) {
        List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
        onlinePlayers.remove(excludePlayer);

        if (onlinePlayers.isEmpty()) {
            return null;
        }

        Random random = new Random();
        return onlinePlayers.get(random.nextInt(onlinePlayers.size()));
    }
}
