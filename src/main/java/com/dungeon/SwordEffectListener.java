package com.dungeon;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Particle;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SwordEffectListener implements Listener {
    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player p)) return;
        if (!(e.getEntity() instanceof LivingEntity target)) return;
        ItemStack sword = p.getInventory().getItemInMainHand();
        if (sword == null || sword.getItemMeta() == null) return;
        if (!sword.getItemMeta().hasCustomModelData()) return;
        int cmd = sword.getItemMeta().getCustomModelData();
        if (cmd == 1) {
            target.setFireTicks(100);
            target.getWorld().spawnParticle(Particle.FLAME, target.getLocation().add(0,1,0), 20);
            target.getWorld().spawnParticle(Particle.LAVA, target.getLocation(), 5);
        }
        if (cmd == 2) {
            target.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 3));
            target.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 100, 1));
            target.getWorld().spawnParticle(Particle.SNOWFLAKE, target.getLocation().add(0,1,0), 30);
            target.setFreezeTicks(100);
        }
        if (cmd == 3) {
            target.teleport(target.getLocation().add(Math.random()*4-2, 0, Math.random()*4-2));
            p.heal(2);
            target.getWorld().spawnParticle(Particle.PORTAL, target.getLocation().add(0,1,0), 40);
            target.getWorld().spawnParticle(Particle.REVERSE_PORTAL, target.getLocation(), 20);
        }
        if (cmd == 4) {
            target.getWorld().strikeLightning(target.getLocation());
            target.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, target.getLocation().add(0,1,0), 30);
        }
    }
}
