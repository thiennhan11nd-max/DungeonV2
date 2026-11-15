package com.dungeon;

import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class DungeonSpawner {
    private static final int MAX_MOBS = 20;
    public static void startSpawner(JavaPlugin plugin, String type) {
        new BukkitRunnable() {
            @Override public void run() {
                World w = DungeonPlugin.getWorld(type);
                Location c = DungeonPlugin.getCenter(type);
                if (w == null || c == null) return;
                int count = 0;
                for (Entity e : w.getNearbyEntities(c, 40, 20, 40)) {
                    if (e instanceof Monster && !e.getPersistentDataContainer().has(BossManager.BOSS_KEY)) count++;
                }
                if (count >= MAX_MOBS) return;
                int toSpawn = Math.min(2, MAX_MOBS - count);
                for (int i = 0; i < toSpawn; i++) {
                    Location loc = c.clone().add(Math.random()*60-30, 1, Math.random()*60-30);
                    EntityType[] types = {EntityType.ZOMBIE, EntityType.SKELETON, EntityType.SPIDER, EntityType.CREEPER};
                    w.spawnEntity(loc, types[(int)(Math.random()*types.length)]);
                }
            }
        }.runTaskTimer(plugin, 0L, 80L);
    }
}
