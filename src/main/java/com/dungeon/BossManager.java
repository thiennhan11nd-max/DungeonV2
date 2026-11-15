package com.dungeon;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.*;

public class BossManager {
    public static NamespacedKey BOSS_KEY = new NamespacedKey("dungeonv2", "boss_type");

    public static LivingEntity spawnBoss(JavaPlugin plugin, String type, Location loc) {
        EntityType et = EntityType.BLAZE;
        String name = "§c§lHOA VUONG"; double hp = 600;
        if (type.equals("bang")) { et = EntityType.STRAY; name = "§b§lBANG HAU"; hp = 550; }
        if (type.equals("huvo")) { et = EntityType.ENDERMAN; name = "§5§lHU VO CHUA"; hp = 700; }
        if (type.equals("loi")) { et = EntityType.WITHER_SKELETON; name = "§e§lLOI THAN"; hp = 650; }
        LivingEntity boss = (LivingEntity) loc.getWorld().spawnEntity(loc, et);
        boss.setCustomName(name);
        boss.setCustomNameVisible(true);
        boss.getAttribute(Attribute.MAX_HEALTH).setBaseValue(hp);
        boss.setHealth(hp);
        boss.getPersistentDataContainer().set(BOSS_KEY, PersistentDataType.STRING, type);
        boss.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
        boss.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, Integer.MAX_VALUE, 1));
        new BukkitRunnable() {
            @Override public void run() {
                if (boss.isDead() || !boss.isValid()) { cancel(); return; }
                Player near = null;
                for (Entity e : boss.getNearbyEntities(30, 30, 30)) if (e instanceof Player) { near = (Player) e; break; }
                if (near == null) return;
                if (type.equals("lua")) {
                    boss.getWorld().spawnEntity(boss.getLocation().add(0,1,0), EntityType.FIREBALL);
                    boss.getWorld().spawnParticle(Particle.FLAME, boss.getLocation(), 30);
                    near.setFireTicks(100);
                }
                if (type.equals("bang")) {
                    near.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 3));
                    near.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 100, 1));
                    boss.getWorld().spawnParticle(Particle.SNOWFLAKE, boss.getLocation(), 30);
                }
                if (type.equals("huvo")) {
                    near.teleport(boss.getLocation().add(2,0,2));
                    near.damage(8);
                    boss.getWorld().spawnParticle(Particle.PORTAL, boss.getLocation(), 40);
                }
                if (type.equals("loi")) {
                    boss.getWorld().strikeLightning(near.getLocation());
                    near.damage(10);
                    boss.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, boss.getLocation(), 30);
                }
            }
        }.runTaskTimer(plugin, 0L, 100L);
        return boss;
    }

    public static void handleBossDeath(LivingEntity boss, Player killer) {
        String type = boss.getPersistentDataContainer().get(BOSS_KEY, PersistentDataType.STRING);
        if (type == null) return;
        Random r = new Random();
        killer.sendMessage("§6§lDA GIET " + boss.getCustomName() + "!");
        int essenceAmount = 2 + r.nextInt(3);
        for (int i = 0; i < essenceAmount; i++) killer.getInventory().addItem(BossGear.makeEssence(type));
        killer.sendMessage("§a+ " + essenceAmount + " Tinh Hoa " + type.toUpperCase() + " (de che do)!");
        if (r.nextDouble() < 0.08) killer.getInventory().addItem(BossGear.makeArmor(type, "helmet"));
        if (r.nextDouble() < 0.08) killer.getInventory().addItem(BossGear.makeArmor(type, "chest"));
        if (r.nextDouble() < 0.08) killer.getInventory().addItem(BossGear.makeArmor(type, "legs"));
        if (r.nextDouble() < 0.08) killer.getInventory().addItem(BossGear.makeArmor(type, "boots"));
        if (r.nextDouble() < 0.05) {
            killer.getInventory().addItem(BossGear.makeSword(type));
            killer.sendMessage("§e§lCUC HIEM! Rot kiem " + type);
        }
        if (r.nextDouble() < 0.20) killer.getInventory().addItem(new ItemStack(Material.NETHERITE_INGOT, 2));
        killer.giveExp(500);
    }
}
