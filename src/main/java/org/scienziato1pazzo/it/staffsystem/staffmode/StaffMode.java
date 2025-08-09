package org.scienziato1pazzo.it.staffsystem.staffmode;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.scienziato1pazzo.it.staffsystem.config.config;
import org.scienziato1pazzo.it.staffsystem.vanish.Vanish;

import java.util.*;

public class StaffMode {

    public static HashMap<UUID, ItemStack[]> savedInventories = new HashMap<>();
    public static List<UUID> staffer = new ArrayList<>();

    public static void setStaffMode(Player player) {
        UUID playerId = player.getUniqueId();
        if (savedInventories.containsKey(playerId)) {
            staffer.remove(playerId);
            ItemStack[] inventoryContents = savedInventories.get(playerId);
            player.getInventory().setContents(inventoryContents);
            savedInventories.remove(playerId);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getC().getString("messages.staffoff")));
        } else {
            staffer.add(playerId);
            ItemStack[] inventoryContents = player.getInventory().getContents().clone();
            savedInventories.put(playerId, inventoryContents);
            player.getInventory().clear();

            // assegna gli item della modalità staff
            player.getInventory().setItem(0, getvanish(player));
            player.getInventory().setItem(1, getRandomTP());
            player.getInventory().setItem(3, getKnockbackStick());
            player.getInventory().setItem(5, getTerminator());
            player.getInventory().setItem(7, getPicchatore());
            player.getInventory().setItem(8, getOpenInventory());

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getC().getString("messages.staffon")));
        }
    }

    public static void addSavedInventory(UUID playerId, ItemStack[] inventoryContents) {
        savedInventories.put(playerId, inventoryContents);
    }

    public static void removeSavedInventory(UUID playerId) {
        savedInventories.remove(playerId);
    }

    public static ItemStack getOpenInventory() {
        ItemStack itemStack = new ItemStack(Material.BOOK);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("§a§lInventario");
        meta.setLore(Arrays.asList(
                "",
                "§aClicca una persona per avere",
                "§ail suo §eInventario!",
                ""
        ));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack getPicchatore() {
        ItemStack itemStack = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("§b§lPicchiatore");
        meta.setLore(Arrays.asList(
                "",
                "§c§lSolo con questo oggetto potrai",
                "§c§lColpire un giocatore!",
                ""
        ));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack getRandomTP() {
        ItemStack itemStack = new ItemStack(Material.COMPASS);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("§e§lRandom TP");
        meta.setLore(Arrays.asList(
                "",
                "§aClicca per tipparsi a caso dalla gente!",
                ""
        ));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack getKnockbackStick() {
        ItemStack stick = new ItemStack(Material.STICK);
        ItemMeta meta = stick.getItemMeta();
        meta.setDisplayName("§6§lKnockback Stick");
        meta.setLore(Arrays.asList(
                "",
                "§eColpisci i player per",
                "§efarli volare lontano!",
                ""
        ));
        meta.addEnchant(org.bukkit.enchantments.Enchantment.KNOCKBACK, 255, true);
        stick.setItemMeta(meta);
        return stick;
    }

    public static ItemStack getTerminator() {
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = sword.getItemMeta();
        meta.setDisplayName("§d§lTerminator");
        meta.setLore(Arrays.asList(
                "",
                "§eColpisci i player per",
                "§efarli morire instant!",
                ""
        ));
        meta.addEnchant(Enchantment.DAMAGE_ALL, 255, true); // Sharpness 255
        sword.setItemMeta(meta);
        return sword;
    }


    public static ItemStack getvanish(Player player) {
        if (Vanish.staffModePlayers.contains(player.getUniqueId())) {
            ItemStack itemStack = new ItemStack(Material.INK_SACK, 1, (short) 1);
            ItemMeta meta = itemStack.getItemMeta();
            meta.setDisplayName("§aVanish ON");
            meta.setLore(Arrays.asList(
                    "",
                    "§aClicca per toglierti la vanish!",
                    ""
            ));
            itemStack.setItemMeta(meta);
            return itemStack;
        }

        ItemStack itemStack = new ItemStack(Material.INK_SACK, 1, (short) 1);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("§cVanish OFF");
        meta.setLore(Arrays.asList(
                "",
                "§aClicca per metterti in vanish!",
                ""
        ));
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}