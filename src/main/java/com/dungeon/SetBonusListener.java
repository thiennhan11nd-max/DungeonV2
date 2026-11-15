package com.dungeon;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SetBonusListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (!DungeonPlugin.isDungeonWorld(p.getWorld().getName())) return;
        String set = getFullSet(p);
        if (set == null) return;
        if (set.equals("lua")) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 60, 0, false, false));
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 0, false, false));
            p.setFireTicks(0);
        }
        if (set.equals("bang")) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 1, false, false));
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 1, false, false));
        }
        if (set.equals("huvo")) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 60, 0, false, false));
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 0, false, false));
        }
        if (set.equals("loi")) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 2, false, false));
            p.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 60, 1, false, false));
        }
    }
    public static String getFullSet(Player p) {
        ItemStack h = p.getInventory().getHelmet();
        ItemStack c = p.getInventory().getChestplate();
        ItemStack l = p.getInventory().getLeggings();
        ItemStack b = p.getInventory().getBoots();
        if (h == null || c == null || l == null || b == null) return null;
        String s1 = getSet(h); String s2 = getSet(c); String s3 = getSet(l); String s4 = getSet(b);
        if (s1 != null && s1.equals(s2) && s2.equals(s3) && s3.equals(s4)) return s1;
        return null;
    }
    private static String getSet(ItemStack it) {
        if (it.getItemMeta() == null || it.getItemMeta().getLore() == null) return null;
        for (String line : it.getItemMeta().getLore()) if (line.contains("Set: ")) return line.replace("§8Set: ", "").trim();
        return null;
    }
}
